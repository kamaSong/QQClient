package com.ycj.QQClent.view.service;

import com.ycj.QQcommon.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

//线程持有Socket对象，用于和服务器保持通信
public class ClientContentServerThread extends Thread {
    private Socket socket;
    //构造方法 接受一个Socket对象
    public ClientContentServerThread(Socket socket) {
        this.socket = socket;
    }
    //线程启动后执行的内容
    @Override
    public void run() {
        //一直运行和服务器保持通信
        while (true) {
            //读取服务器发送的数据

            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) objectInputStream.readObject();//服务器没有发生过则会阻塞
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
