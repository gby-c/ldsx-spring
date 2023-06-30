package com.gby.video.controller;

import com.gby.video.bean.Page;
import com.gby.video.bean.ResultVO;
import com.gby.video.bean.User;
import com.gby.video.service.serviceImpl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController  // 等价于1. @Controller 作用:等价于@Service; 2. @ResponseBody  设置返回数据格式为json
@RequestMapping(value = "/user", produces = "application/json")
public class UserController {
    @Resource
    private UserServiceImpl userService;

    @GetMapping(value = "/login")
    public ResultVO userLogin(User user) {
        User vo = userService.getUserByName(user.getUserName());
        if (vo == null) {
            return ResultVO.error("账号不存在");
        } else {
            if (userService.security(vo, user.getPassword())) {
                vo.setPassword("");
                return ResultVO.success("登陆成功").setData(vo);
            } else {
                return ResultVO.error("密码错误");
            }
        }
    }

    @PostMapping(value = "/register")
    public ResultVO userRegister(@RequestBody User user) {
        User vo = userService.getUserByName(user.getUserName());
        if (vo == null) {
            userService.userRegister(user);
            return ResultVO.success("注册成功").setData(user);
        } else {
            return ResultVO.error("账号已存在");
        }
    }

    @GetMapping("/select")
    public ResultVO userSelect(Page page, User user) {
        return ResultVO.success("查询成功").setData(userService.select(page, user));
    }

    @PutMapping("/update/user")
    public ResultVO userUpdate(@RequestBody User user) {
        try {
            return ResultVO.success("修改" + userService.userUpdate(user) + "条数据");
        } catch (Exception e) {
            return ResultVO.error("请稍后重试");
        }
    }

    @PutMapping("/ban")
    public ResultVO userBan(@RequestBody User user) {
        return ResultVO.success("修改" + userService.userBan(user) + "条数据");
    }

    @PutMapping("/update/passwd")
    public ResultVO passwordPasswd(@RequestBody User user) {
        if (userService.security(userService.getUserByName(user.getUserName()), user.getPassword())) {
            if(userService.updatePasswd(user,user.getNewPasswd()) == 1){
                return ResultVO.success("修改成功");
            } else {
                return ResultVO.success("修改失败，请稍后重试");
            }
        } else {
            return ResultVO.error("原密码不正确");
        }
    }
}
