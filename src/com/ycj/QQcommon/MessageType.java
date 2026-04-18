package com.ycj.QQcommon;
//消息类型
public interface MessageType {
    //通过不同的消息类型进行不同的业务处理
    String MESSAGE_LOGIN_SUCCEED = "1"; //登录成功
    String MESSAGE_LOGIN_FAIL = "2"; //登录失败
    //拉取用户
    String  MESSAGE_COMM_MES = "3";//普通信息报
    String  MESSAGE_GET_ONLINE_FRIEND = "4";
    String  MESSAGE_RET_ONLINE_FRIEND = "5";
    String  MESSAGE_CLIENT_EXIT = "6";//客户端退出请求
}
