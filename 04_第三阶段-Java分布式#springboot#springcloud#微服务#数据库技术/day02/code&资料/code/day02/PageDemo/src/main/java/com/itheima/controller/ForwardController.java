package com.itheima.controller;

/*
 *  @项目名：  day02 
 *  @包名：    com.itheima.controller
 *  @文件名:   ForwardController
 *  @创建者:   xiaomi
 *  @创建时间:  2018/6/1 11:48
 *  @描述：    演示的是请求转发的跳转逻辑
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ForwardController {
    private static final String TAG = "ForwardController";


    //这是最简单的
    @RequestMapping("forwardPage")
    public String forwardPage(){
        System.out.println("forwardPage~!~");
        return "httP://www.itheima.com";
    }


    //这是最明显的
    @RequestMapping("forwardPage3")
    public String forwardPage3(){
        System.out.println("forwardPage333~!~");
        return "forward:test.html";
    }


    //演示最原始的请求转发跳转 -- 借助request对象。
    @RequestMapping("forwardPage2")
    public void forwardPage2(HttpServletRequest request , HttpServletResponse response){
        try {
            System.out.println("forwardPage2222~!~");

            request.getRequestDispatcher("test.html").forward( request, response);

            // return "test.html";
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //返回字符串
    @RequestMapping("forwardPage4")
    public void forwardPage4( HttpServletResponse response){
        try {
            System.out.println("forwardPage444~!~");

            response.getWriter().write("forward:test.html");
            // return "forward:test.html";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("forwardPage5")
    @ResponseBody
    public String forwardPage5(){
        return "forward:test.html";
    }


}
