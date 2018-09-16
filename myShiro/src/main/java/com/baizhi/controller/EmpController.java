package com.baizhi.controller;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/9/13 0013.
 */
@Controller
@RequestMapping("/user")
public class EmpController {
    @RequestMapping("/shiro")
    public String login(String name,String password){
        /*用户认证 拿到主体*/
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token=new UsernamePasswordToken(name,password);
        try {
            subject.login(token);
            return "index";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "redirect:/user/login.jsp";
        }

    }
}
