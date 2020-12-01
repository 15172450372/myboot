package com.example.myboot.aspect;

import com.example.myboot.annotation.NoRepeatCommit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 周威
 * @version v1.0.0
 * @description
 * @date 2020/10/10 16:42
 **/
@Aspect
@Component
@Slf4j
public class NoRepeatCommitAspect {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Pointcut("@annotation(com.jtz.manager.service.annotation.NoRepeatCommit)")
    public void pointCut(){};

    @Before(value = "pointCut()&&@annotation(noRepeatCommit)")
    public void before(JoinPoint point, NoRepeatCommit noRepeatCommit) {

        String sessionId = RequestContext.getContext().getSession().getId();

        Signature signature = point.getSignature();
        String className = signature.getDeclaringTypeName().replace(".", ":");
        String methodName = signature.getName();
        String key = String.format("%s:%s:%s", className, methodName, sessionId);

        long timeout = noRepeatCommit.timeout();
        if (timeout < 0){
            timeout = 500L;
        }
        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(key, UUID.randomUUID().toString(), timeout, TimeUnit.MILLISECONDS);
        if (!aBoolean) {
            throw new WebException(JtzResultCode.REPEAT_COMMIT_ERROR);
        }
    }

}