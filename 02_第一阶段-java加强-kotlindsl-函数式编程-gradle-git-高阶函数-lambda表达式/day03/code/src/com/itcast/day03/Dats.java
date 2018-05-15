package com.itcast.day03;

import java.util.ArrayList;

/**
 * ClassName:Dats
 * Description:
 */
class Dats {
    public void setFruitList(ArrayList<?> list) {
        this.age = age;
    }

    class Inclass{
        private String name;
        public void sayHello(){
//            Dats.this.name
            //第一种:
//            Class.forName("")
            //第二种
//            Dats.class
            //第三种
//            new Dats().getClass()
        }
    }
    private  String phone;
    private String name;
    private int age;
    public Dats(String name,int age){
        this.name = name;
        this.age = age;
        System.out.println("执行了初始化");
    }
    public Dats(String name,int age,String phone){
        this(name, age);
        System.out.println("还行了次构");
        this.phone = phone;
    }
//    interface Callback{
//        String name = "张三";
//    }
}
