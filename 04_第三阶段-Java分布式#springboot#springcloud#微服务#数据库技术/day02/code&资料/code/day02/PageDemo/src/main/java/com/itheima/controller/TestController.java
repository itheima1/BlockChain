package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *  @项目名：  day02 
 *  @包名：    com.itheima.controller
 *  @文件名:   TestController
 *  @创建者:   xiaomi
 *  @创建时间:  2018/6/1 11:39
 *  @描述：    该类演示的是跳转页面的入门代码 ，注意@Controller和方法的返回值即可
 */
//@RestController  : 返回值看成是字符串
//@RestController


@Controller //: 返回值看成是资源的名称  元数据，
public class TestController {
    private static final String TAG = "TestController";

    @RequestMapping("testPage")
    public String testPage(){

        System.out.println("testPage~!");

        return "test.html";
    }
}
