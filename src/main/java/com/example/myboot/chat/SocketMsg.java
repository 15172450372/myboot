package com.example.myboot.chat;

import lombok.Data;

/**
 * @Author: zhouwei
 * @Description:
 * @Date: 2020/7/3 上午11:26
 * @Version: 1.0
 **/
@Data
public class SocketMsg {

    private int type;   //聊天类型0：群聊，1：单聊.
    private String fromUser;//发送者.
    private String toUser;//接受者.
    private String msg;//消息

}
