package com.gby.video.service;

import com.gby.video.bean.Page;
import com.gby.video.bean.User;

import java.util.List;

public interface UserService {
    User getUserByName(String userName);

    Boolean security(User user, String clearPassword);

    String encryption(String password);

    void userRegister(User user);

    Page<User> select(Page page, User user);

    int userUpdate(User user);

    int userBan(User user);

    int updatePasswd(User user, String newPasswd);
}
