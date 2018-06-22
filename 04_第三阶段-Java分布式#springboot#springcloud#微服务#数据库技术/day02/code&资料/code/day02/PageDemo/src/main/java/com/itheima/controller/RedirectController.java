package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 *  @项目名：  day02 
 *  @包名：    com.itheima.controller
 *  @文件名:   RedirectController
 *  @创建者:   xiaomi
 *  @创建时间:  2018/6/1 12:00
 *  @描述：    该类演练的是重定向跳转页面
 */
@Controller
public class RedirectController {
    private static final String TAG = "RedirectController";


    //最简单的重定向写法
    @RequestMapping("redirectPage1")
    public String redirectPage1(){

        System.out.println("redirectPage1");

        //return "redirect:result.html";

        return "redirect:http://www.itheima.com";
    }



    //最原始
    @RequestMapping("redirectPage2")
    public void redirectPage2(HttpServletResponse response){

        try {
            System.out.println("redirectPage2");

            response.sendRedirect("result.html");

            //  return "redirect:result.html";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //请求转发的备用方法
    @RequestMapping("redirectPage3")
    public String redirectPage3(){

        System.out.println("redirectPage3");

        return "result.html";
    }
}
