package com.gby.video.service.serviceImpl;

import com.gby.video.bean.Page;
import com.gby.video.bean.User;
import com.gby.video.mapper.UserMapper;
import com.gby.video.service.UserService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper mapper;

    @Override
    public User getUserByName(String userName) {
        return mapper.selectUserByName(userName);
    }

    @Override
    public Boolean security(User user, String clearPassword) {
        return encryption(clearPassword).equals(user.getPassword());
    }

    @Override
    public String encryption(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void userRegister(User user) {
        user.setPassword(encryption(user.getPassword()));
        mapper.userInsert(user);
    }

    @Override
    public Page<User> select(Page page, User user) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<User> users = mapper.select(user);
        PageInfo<User> info = new PageInfo<>(users);
        page.setTotal(info.getTotal());
        page.setRows(users);
        return page;
    }

    @Override
    public int userUpdate(User user) {
        return mapper.userUpdate(user);
    }

    @Override
    public int userBan(User user) {
        if (user.getActivate() == 1) {
            return mapper.userBan(user);
        } else {
            return mapper.userAct(user);
        }
    }

    @Override
    public int updatePasswd(User user, String newPasswd) {
        user.setPassword(encryption(newPasswd));
        return mapper.userUpdate(user);
    }
}
