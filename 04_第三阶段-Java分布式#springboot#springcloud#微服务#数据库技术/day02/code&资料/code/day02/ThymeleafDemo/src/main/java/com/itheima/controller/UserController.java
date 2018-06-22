package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *  @项目名：  day02 
 *  @包名：    com.itheima.controller
 *  @文件名:   UserController
 *  @创建者:   xiaomi
 *  @创建时间:  2018/6/1 14:47
 *  @描述：    TODO
 */
@Controller
public class UserController {
    private static final String TAG = "UserController";


    //1. 在浏览器访问这个方法。

    //2. 方法执行完毕，要跳转到一个页面。 在方法的内部存数据

    //3. 在页面上显示我要显示的数据
    @RequestMapping("user_login")
    public String login(String username , String password){

        System.out.println("save invoke~!~");

        System.out.println(username + " : " + password);

        //先把昵称给存起来。

        return "index.html";
    }


    @RequestMapping("user_login02")
    public String login02(String username , String password , Model model){

        System.out.println("save invoke~!~");

        System.out.println(username + " : " + password);

        //先把昵称给存起来。
        model.addAttribute("nickname",username);


        //templates
        return "index";
    }


    @RequestMapping("aa")
    public String aa(){

        return "index.html";
    }


    @RequestMapping("bb")
    public String bb(){

        return "index";
    }
}
