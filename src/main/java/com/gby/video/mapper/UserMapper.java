package com.gby.video.mapper;

import com.gby.video.bean.User;
import com.gby.video.bean.Video;

import java.util.List;

public interface UserMapper {
    User selectUserByName(String userName);

    void userInsert(User user);

    List<User> select(User user);

    int userUpdate(User user);

    int userBan(User user);

    int userAct(User user);
}
