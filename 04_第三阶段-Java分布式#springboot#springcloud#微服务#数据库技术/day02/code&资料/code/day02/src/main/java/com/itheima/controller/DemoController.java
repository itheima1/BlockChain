package com.itheima.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *  @项目名：  day02 
 *  @包名：    com.itheima.controller
 *  @文件名:   DemoController
 *  @创建者:   xiaomi
 *  @创建时间:  2018/6/1 9:14
 *  @描述：    TODO
 */
@RestController
public class DemoController {
    private static final String TAG = "DemoController";

    //因为一会要演练get请求和post请求
    @RequestMapping("doGet")
    public String doGet(String username , String password){

        System.out.println(username + " : " + password);

        return "doGet ~!~";
    }

    @RequestMapping("doPost")
    public String doPost(String username , String password){

        System.out.println("post==="+username + " : " + password);

        return "doPost ~!~";
    }
}
