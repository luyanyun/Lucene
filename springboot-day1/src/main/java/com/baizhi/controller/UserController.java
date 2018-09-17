package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2018/8/22 0022.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger= LoggerFactory.getLogger(UserController.class);//注意导入的是slf4j规范的jar
    @Autowired
    private UserService userService;
    @RequestMapping("/register.do")
    public String register(User user){
        userService.insert(user);
        return "index";
    }
    @RequestMapping("/update.do")
    public String update(User user){
        userService.update(user);
        return "ok";
    }
    @RequestMapping("/delete.do")
    public  String delete(Integer id){
        userService.delete(id);
        return "ok1";
    }
    @RequestMapping("/queryAll.do")
    public List<User> queryAll(Model model){
        List<User> users = null;
        try {
            users = userService.queryAll();

            logger.debug("运行正常");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("service有误");
        }

        return users;
    }
}
