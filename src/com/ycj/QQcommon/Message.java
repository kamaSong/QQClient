package com.ycj.QQcommon;
//服务器读取客户端发送的消息用户类消息
import java.io.Serializable;
//注：消息类和用户类为服务端和客户端共有类，所以都需要创建，方便序列化和反序列化
//消息类，用于在客户端和服务器端之间传递消息，需要序列化
public class Message implements Serializable {
    //序列化版本号，兼容
    private static final long serialVersionUID = 1L;
    //消息的发送者
    private String sender;
    //消息的接收者
    private String receiver;
    //消息内容
    private String content;
    //发送时间
    private String sendTime;


    //消息类型，通过接口进行定义
    private String messageType;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
