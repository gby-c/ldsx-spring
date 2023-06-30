package com.gby.video.bean;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String userName;
    private String password;
    private String newPasswd;
    private String userPic;
    private String userCover;
    private String sex;
    private String content;
    private Integer age;
    private String openid;
    private Integer role;
    private Integer activate;
}
