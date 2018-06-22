package com.itheima.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/*
 *  @项目名：  day02 
 *  @包名：    com.itheima.controller
 *  @文件名:   DemoController
 *  @创建者:   xiaomi
 *  @创建时间:  2018/6/1 10:39
 *  @描述：    TODO
 */
@RestController
public class DemoController {
    private static final String TAG = "DemoController";


    //其实request对象就是对应了httpwatch抓取到的左边数据内容
    @RequestMapping("requestDemo")
    public String requestDemo(HttpServletRequest request ){

        System.out.println("request=" + request);

        //1. 获取参数
        String username =  request.getParameter("username");
        System.out.println("username=" + username);

        //2. 获取头部信息
        String age = request.getHeader("age");
        System.out.println("age=" + age);

        System.out.println("--------------------------------------");

        //获取所有的头信息， 这是一个集合
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
           String headerName =  headerNames.nextElement();
           String headerValue = request.getHeader(headerName);

            System.out.println(headerName + " : " + headerValue);
        }

        //3. 跳转页面

        return "request success~!~";
    }

    //其实reponse对象就是对应了httpwatch抓取到的右边数据内容
    @RequestMapping("responseDemo")
    public void responseDemo(HttpServletResponse response ){


        try {
            //1. 可以写数据给浏览器
            System.out.println("responseDemo");


            //设置中文编码
            response.setContentType("text/html;charset=UTF-8");

            //写数据出去
            response.getWriter().write("text/html;charset=utf-8;成功~6666!~!");


            //2. 另外一个工作： 跳转页面


            //靠返回值，给浏览器带数据。 这一行的背后使用response对象写出去的。

            // return "response success~!~";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
