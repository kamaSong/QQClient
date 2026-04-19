package com.ycj.QQClent.view.service;

import com.ycj.QQcommon.Message;
import com.ycj.QQcommon.MessageType;

import java.io.*;

//文件客户端服务类，进行文件传输
public class FileClientServer {
    /**
     *
     * @param src ,源路径
     * @param dest ， 目标路径
     * @param sendId ，发送者ID
     * @param getterId ，接收者ID
     */
    public void sendFile(String src,String dest,String sendId,String getterId){
        
        //读取文件
        //1.封装文件信息
        Message message = new Message();
        message.setSrc(src);
        message.setDest(dest);
        message.setSender(sendId);
        message.setReceiver(getterId);
        message.setMessageType(MessageType.MESSAGE_FILE_MES);

        //封装完毕 先读取文件
        FileInputStream fileInputStream = null;
        byte[] filebytes = new byte[(int) new File(src).length()];

        try {
            fileInputStream= new FileInputStream(src);
            fileInputStream.read(filebytes);
            //将文件的字节数组封装到message对象中
            message.setFileBytes(filebytes);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if (fileInputStream != null)
            {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        //提示信息
        System.out.println("发送文件给" + getterId + "文件路径为" + src+"到"+dest);
        //发送文件 ，先获取客户端对象socket
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManagerClientConectServiceThread.
                    getClientContentServerThread(sendId).getSocket().getOutputStream());
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
