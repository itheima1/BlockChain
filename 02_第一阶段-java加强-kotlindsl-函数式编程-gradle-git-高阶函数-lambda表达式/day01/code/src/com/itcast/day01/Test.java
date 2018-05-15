package com.itcast.day01;

import java.util.ArrayList;

/**
 * ClassName:Test
 * Description:
 */
class Test {

    class Data{

    }

    //编译器常量
    private static final int WEEK = 7;
    public static void main(String[] args){
        //int数据类型
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        //自动装箱和自动拆箱

        int a = 10;
        int b = a+10;
        //hashcode()
//        a.hashcode()
        Integer it = Integer.valueOf(a);
        int i = it.hashCode();
        System.out.println(i);

        /*---------------------------- 不同数据类型转换 ----------------------------*/
        int d = 10;
        String s = "10";
        int i1 = Integer.parseInt(s);

        //小的可以赋值给大的  大的不能赋值给小的
        long l = 20;
        l = d;//int赋值给long类型
//        d = l;//long类型赋值给int类型

        /*---------------------------- 可变和不可变 ----------------------------*/
        final int h = 10;
        final int g = 20;
//        h = 20;
//        WEEK = 8;


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
////                h = h+10;
//
//            }
//        }).start();

        /*---------------------------- 字符串比较 ----------------------------*/
        String str1 = "abc";
        String str2 = new String(new char[]{'a','b','c'});

        //equals 比较的是字符串的值true
        System.out.println("str1.equals(str2)="+(str1.equals(str2)));
        //内存地址 false
        System.out.println("str1.== str2="+(str1==str2));
    }
    //数组  集合  对象
    public String getName(){
        return "张三";
    }
    public int getAge(){
        return 10;
    }
}
