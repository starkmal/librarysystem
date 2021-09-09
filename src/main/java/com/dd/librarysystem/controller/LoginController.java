package com.dd.librarysystem.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequiresPermissions("user:insert")
public class LoginController {


    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestParam String username, @RequestParam String password) {
        //  System.out.println(details);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        token.setRememberMe(false);
        if (subject.isAuthenticated()) {
            System.out.println("已登录");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        try{
            subject.login(token);
        }catch (UnknownAccountException e){
            System.out.println("用户名不存在");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }catch (AuthenticationException e) {
            System.out.println("认证失败");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        System.out.println("登录成功");
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @GetMapping("/checklogin")
    public ResponseEntity<HttpStatus> checklogin() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            System.out.println("检测已登录");
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }



}
