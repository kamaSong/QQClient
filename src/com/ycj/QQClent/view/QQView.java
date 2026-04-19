package com.ycj.QQClent.view;

import com.ycj.QQClent.Utils.Utility;

import com.ycj.QQClent.view.service.FileClientServer;
import com.ycj.QQClent.view.service.UserClientService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
//用户相关方法

public class QQView {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new QQView().showMenu();
        System.out.println("程序退出");//主线程退出  与服务器的线程还在运行
    }
    //全局循环变量
    private boolean loop = true;
    private String key =null;
    //创建对象,进行登录服务器管理服务
    private UserClientService userClientService = new UserClientService();
    //文件 服务的路径
    private FileClientServer fileClientServer = new FileClientServer();
    //方法显示菜单

    public void showMenu() throws IOException, ClassNotFoundException {
        while (loop) {
            System.out.println("=========欢迎登录网络通讯系统=========");
            System.out.println("\t\t1. 登录系统");
            System.out.println("\t\t9. 退出系统");

            System.out.print("请选择：");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    System.out.println("请输入用户名：");
                    String userId = Utility.readString(6);
                    System.out.println("请输入密  码：");
                    String userPassword = Utility.readString(8);
                    //输入后到服务端验证
                    //思路：先将用户作为对象发送到服务端，服务端返回一个Message对象，包含登录结果，如果成功则进入二级菜单
                    //服务验证

                    if(userClientService.checkUser(userId, userPassword))
                    {
                       System.out.println("=========欢迎用户"+userId+"========= ");
                       while (loop)
                       {
                           System.out.println("=========网络通讯二级菜单用户("+userId+")=========");
                           System.out.println("\t\t1. 显示在线用户列表");
                           System.out.println("\t\t2. 群发消息");
                           System.out.println("\t\t3. 私聊消息");
                           System.out.println("\t\t4. 发送文件");
                           System.out.println("\t\t9. 退出系统");
                           System.out.print("请选择：");
                            key = Utility.readString(1);
                           switch (key) {
                               case "1":
                                   System.out.println("当前在线列表");
                                   userClientService.onlineFriendList();
                                   break;
                               case "2":
                                   System.out.println("请输入想群聊的内容:");
                                   String groupContent = Utility.readString(100);
                                   userClientService.sendMessageToGroup(groupContent,userId);
                                   break;
                               case "3":
                                   System.out.println("请输入聊天用户名（在线）:");
                                   String friendId = Utility.readString(6);
                                   System.out.println("请输入要发送的内容:");
                                   String content = Utility.readString(100);
                                   //将id即content发送给服务端
                                   userClientService.sendMessageToOther(content,userId,friendId);
                                   break;
                               case "4":
                                   //打通数据通道，发送文件测试成功
                                   System.out.println("请输入要发送的文件的用户:");
                                   String getterId = Utility.readString(6);
                                   System.out.println("请输入要发送的文件路径:");
                                   String src = Utility.readString(100);
                                   System.out.println("请输入要发送到用户路径:");
                                   String dest = Utility.readString(100);
                                   fileClientServer.sendFile(src,dest,userId,getterId);
                                   break;
                               case "9":
                                   //退出系统
                                    userClientService.exitSystem();
                                   loop = false;//进推出主线程
                                   break;
                           }

                       }
                    }
                    else
                    {
                       System.out.println("用户名或密码错误，请重新登录");
                    }

                    break;
                case "9":
                    //退出系统
                    loop = false;
                    break;

            }



        }

    }

}
