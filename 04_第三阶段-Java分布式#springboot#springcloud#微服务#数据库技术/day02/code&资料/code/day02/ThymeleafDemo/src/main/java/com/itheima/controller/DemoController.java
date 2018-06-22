package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
 *  @项目名：  day02 
 *  @包名：    com.itheima.controller
 *  @文件名:   DemoController
 *  @创建者:   xiaomi
 *  @创建时间:  2018/6/1 15:12
 *  @描述：    直接访问Controller，然后跳转到模板页面，显示数据
 */
@Controller
public class DemoController {
    private static final String TAG = "DemoController";


    @RequestMapping("test")
    public String test(Model model){

        model.addAttribute("address","深圳");

        return "testpage";
    }



    //现在的方法和以前不一样。必须存数据  &  跳转页面

    @RequestMapping("test2")
    public ModelAndView test2(){


        ModelAndView modelAndView = new ModelAndView();

        //1. 存数据
        modelAndView.addObject("address","北京");

        //2. 指定视图
        modelAndView.setViewName("testPage");


        return modelAndView;
    }
}
