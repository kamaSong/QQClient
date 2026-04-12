package com.ycj.QQcommon;
//客户端  发送用户信息 ，消息类 服务器
import java.io.Serializable;

//用户类,用户类需要在QQServer和QQClient中使用，通过对象流传输  所以需要实现Serializable接口
public class User implements Serializable {

    //序列化版本号
    private static final long serialVersionUID = 1L;
    //用户名
    private String userId;
    //密码
    private String password;
    //无参构造方法
    public User() {
    }

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
