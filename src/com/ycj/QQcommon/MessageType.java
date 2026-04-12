package com.ycj.QQcommon;
//消息类型
public interface MessageType {
    //通过不同的消息类型进行不同的业务处理
    String MESSAGE_LOGIN_SUCCEED = "1"; //登录成功
    String MESSAGE_LOGIN_FAIL = "2"; //登录失败
}
