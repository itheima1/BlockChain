package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 *  @项目名：  day02 
 *  @包名：    com.itheima
 *  @文件名:   MainApp
 *  @创建者:   xiaomi
 *  @创建时间:  2018/6/1 9:13
 *  @描述：    TODO
 */
@SpringBootApplication
public class MainApp {
    private static final String TAG = "MainApp";

    public static void main(String [] args){
        SpringApplication.run( MainApp.class,args );
    }
}
