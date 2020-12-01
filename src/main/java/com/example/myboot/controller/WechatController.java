package com.example.myboot.controller;

import cn.hutool.crypto.SecureUtil;
import com.sun.deploy.net.URLEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author : 周威
 * @version : 1.0
 * @description : 微信公众号相关
 * @date : 2020/8/11 13:56
 **/
@RestController
@RequestMapping("/wechat")
public class WechatController {

    //测试账号信息
    private static final String TOKEN = "wxtoken";
    private static final String APP_ID = "wxd2feff8a66e901de";
    private static final String APP_SECURITY = "9442c3805ad4d4807ee0ce2bda03bb7f";
    //内网穿透地址
    private static final String REDIRECT_URI = "http://fxdhdp.natappfree.cc/";


    /**
     * 验证消息的确来自微信服务器
     * @param request
     * @return
     */
    @GetMapping("/code/check")
    public String check(HttpServletRequest request) {

        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        //1.将token、timestamp、nonce三个参数进行字典序排序
        ArrayList<String> strList = new ArrayList<>();
        strList.add(nonce);
        strList.add(timestamp);
        strList.add(TOKEN);
        Collections.sort(strList);

        //2. 将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder stringBuilder = new StringBuilder();
        strList.forEach(stringBuilder::append);
        String s = SecureUtil.sha1(stringBuilder.toString());

        //3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        if (signature.equals(s)) {
            return echostr;
        }
        throw new RuntimeException("校验失败！");
    }

    @RequestMapping("/transfer")
    public void transfer(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入");
        try {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");
            //这里要将你的授权回调地址处理一下，否则微信识别不了
            String redirect_uri= URLEncoder.encode(REDIRECT_URI + "wechat/code", "UTF-8");//回调地址，获取code

            //简单获取openid的话参数response_type与scope与state参数固定写死即可
            String url = "https://open.weixin.qq.com/connect/oauth2/authorize?redirect_uri=" + redirect_uri +
                    "&appid=" + APP_ID + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
            response.sendRedirect(url);//这里请不要使用get请求单纯的将页面跳转到该url即可
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("完成");
    }

    @GetMapping("/code")
    public String redirect(HttpServletRequest request) {
        String code = request.getParameter("code");
        return "code:" + code;
    }

    /**
     * 获取openid
     * @param code
     * @param response
     * @throws IOException
     */
    @GetMapping("/openid/{code}")
    public void getOpenid(@PathVariable("code") String code, HttpServletResponse response) throws IOException {
        StringBuffer stringBuffer = new StringBuffer("https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APP_ID+"&secret="+APP_SECURITY+"&code="+code+"&grant_type=authorization_code");
        response.sendRedirect(stringBuffer.toString());
    }


}
