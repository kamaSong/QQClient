package com.ycj.QQClent.view.service;

import com.ycj.QQcommon.Message;
import com.ycj.QQcommon.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
//线程常用 extends Thread 单继承  implements Runnable 多实现

//线程持有Socket对象，用于和服务器保持通信
public class ClientContentServerThread extends Thread {
    private Socket socket;
    //构造方法 接受一个Socket对象，即持有Socket对象
    public ClientContentServerThread(Socket socket) {
        this.socket = socket;
    }
    //线程启动后执行的内容
    @Override
    public void run() {
        //一直运行和服务器保持通信
        while (true) {
            //等读取服务器发送的mes数据
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) objectInputStream.readObject();//服务器没有发生过则会阻塞

                //判断服务器的返回的类型,符合
                if(message.getMessageType().equals(MessageType.MESSAGE_RET_ONLINE_FRIEND))
                {
                    //取出 信息并显示，取出信息中的用户
                    String[] onlineUser = message.getContent().split(" ");
                    System.out.println("当前用户列表");
                    for (int i = 0; i < onlineUser.length; i++) {
                        System.out.println("用户:"+onlineUser[i]);

                    }


                }
                else{
                    //暂时不处理
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    //方便其他方法使用socket对象
    public Socket getSocket() {
        return socket;
    }


}
