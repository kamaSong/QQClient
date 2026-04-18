package com.ycj.QQClent.view.service;

import com.ycj.QQcommon.Message;
import com.ycj.QQcommon.MessageType;
import com.ycj.QQcommon.User;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

//进行用户信息的管理，用户登陆验证
public class UserClientService   {
    //通用成员属性
    private User user = new User();
    //用于和服务器通信的Socket
    private Socket socket = null;




    //检查用户名密码到服务器是否正确
    public boolean checkUser(String userId,String userPassword) throws IOException, ClassNotFoundException {
        //根据输入的密码创建一个User对象，使用全局方便其他方法使用
        //设置用户及密码
         boolean isLogin = false;
        user.setUserId(userId);
        user.setPassword(userPassword);
        //链接服务器    创建Socket给服务器传送信息等待服务器accept监听
        socket = new Socket(InetAddress.getLocalHost(), 9999);
        
        //先向服务器发送用户信息
        OutputStream outputStream = socket.getOutputStream();
        //因为发送的是一个User对象使用对象流
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        oos.writeObject(user);
        //写完后，接受到服务器的响应
        InputStream inputStream = socket.getInputStream();
        //也需要使用对象流来读取
        ObjectInputStream ois = new ObjectInputStream(inputStream);
        Message message = (Message) ois.readObject();//此时服务器返回 Message对象
        //接收到服务器返回的Message对象后进行判断
        if(message.getMessageType().equals(MessageType.MESSAGE_LOGIN_SUCCEED))
        {
            //启动线程一直与服务端通信

            //创建一个线程，保持和服务器的通信，该线程需要持有socket对象->创建线程类
            ClientContentServerThread clientContentServerThread = new ClientContentServerThread(socket);
            clientContentServerThread.start();//启动线程
            //线程放入集合，方便管理
           ManagerClientConectServiceThread.addClientContentServerThread(userId,clientContentServerThread);
            isLogin = true;//登录成功
        }
        else
        {
             //登录失败，关闭socket
            socket.close();
        }


    return isLogin;
    }
    //向服务器请求在线服务列表
    public void onlineFriendList()
    {//发送message
        Message message = new Message();
        message.setMessageType(MessageType.MESSAGE_GET_ONLINE_FRIEND);
        message.setSender(user.getUserId());

        //发送服务器。先获取socket 因为 socket在线程中 县城又在集合先获取集合,对应线程-》 socket->output
        try {
            OutputStream outputStream = ManagerClientConectServiceThread.getClientContentServerThread(user.getUserId())
                    .getSocket().getOutputStream();
            //获得流以后加入对象
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(message);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
