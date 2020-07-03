package com.example.myboot.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zhouwei
 * @Description:
 * @Date: 2020/7/3 上午11:25
 * @Version: 1.0
 **/
@ServerEndpoint(value = "/websocket/{nickname}")
@Component
public class MyWebSocket {

    //昵称
    private String nickname;
    //客户端会话
    private Session session;
    //用来存放每个客户端对应的MyWebSocket对象
    private static Map<String, Session> map = new ConcurrentHashMap<>();

    /**
     * 连接建立成功调用的方法
     *
     * @param session
     * @param nickname
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("nickname") String nickname) {
        this.session = session;
        this.nickname = nickname;

        map.put(session.getId(), session);

        System.out.println("有新连接加入！当前在线人数为" + map.size());
        this.session.getAsyncRemote().sendText("恭喜" + nickname + "成功连接上WebSocket(其频道号：" + session.getId() + ")-->当前在线人数为：" + map.size());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        map.remove(this.session.getId());
        System.out.println("有一人连接关闭！当前在线人数为" + map.size());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     * @param session
     * @param nickname
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("nickname") String nickname) {
        System.out.println("来自客户端的消息-->" + nickname + ": " + message);

        ObjectMapper objectMapper = new ObjectMapper();
        SocketMsg socketMsg;

        try {
            socketMsg = objectMapper.readValue(message, SocketMsg.class);
            if (socketMsg.getType() == 1) {
                //单聊.需要找到发送者和接受者.
                socketMsg.setFromUser(session.getId());//发送者.
                Session fromSession = map.get(socketMsg.getFromUser());
                Session toSession = map.get(socketMsg.getToUser());
                //发送给接受者.
                if (toSession != null) {
                    //发送给发送者.
                    fromSession.getAsyncRemote().sendText(nickname + "：" + socketMsg.getMsg());
                    toSession.getAsyncRemote().sendText(nickname + "：" + socketMsg.getMsg());
                } else {
                    //发送给发送者.
                    fromSession.getAsyncRemote().sendText("系统消息：对方不在线或者您输入的频道号不对");
                }
            } else {
                //群发消息
                broadcast(nickname + ": " + socketMsg.getMsg());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 群发消息
     *
     * @param message
     */
    private void broadcast(String message) {
        for (String sessionId : map.keySet()) {
            map.get(sessionId).getAsyncRemote().sendText(message);
        }
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

}
