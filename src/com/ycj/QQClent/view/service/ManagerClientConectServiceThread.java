package com.ycj.QQClent.view.service;

import java.util.HashMap;

//管理客户端连接服务器的线程service
public class ManagerClientConectServiceThread {
    //将多个线程放入集合中管理  key  用户id
    private  static HashMap<String,ClientContentServerThread> hm = new HashMap<>();
    //添加线程
    public static void addClientContentServerThread(String userId,ClientContentServerThread clientContentServerThread)
    {//将线程放入集合
        hm.put(userId,clientContentServerThread);
    }
    //通过方法获取线程  返回线程
    public static ClientContentServerThread getClientContentServerThread(String userId)
    {
        //通过用户id获取线程
        return hm.get(userId);
    }
}
