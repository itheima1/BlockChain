package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *  @项目名：  day02 
 *  @包名：    com.itheima.controller
 *  @文件名:   UserController
 *  @创建者:   xiaomi
 *  @创建时间:  2018/6/1 15:41
 *  @描述：    TODO
 */
@Controller
public class UserController {
    private static final String TAG = "UserController";


    @RequestMapping("user_register")
    public String register(String username , String password , Model model){

        System.out.println("register~!~!");

        if("admin".equals(username)){

            //注册失败
            model.addAttribute("result","用户名不可用!");

            System.out.println("注册失败~！~");
            return "register";
        }


        model.addAttribute("result",username);

        return "register_success";
    }


    @RequestMapping("aa")
    public String aa(){
        System.out.println("aaa");
        return "";
    }


    //模板页面是无法直接跳转的，必须由代码里面跳转过去。
    @RequestMapping("register.html")
    public String toRegisterHtml(){
        System.out.println("要去往register.html了~！");
        return "register";
    }
}
