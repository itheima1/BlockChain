package com.itcat.day02;

/**
 * ClassName:SwitchDemo
 * Description:
 */
class SwitchDemo {
    public static void main(String[] args) {

    }
    //java 的switch语句支持什么数据类型? char int  byte short  String(高版本) 枚举
    public static String level(char grade) {
        switch (grade) {
            case 'A':
                System.out.println("优秀");
                return "优秀";
            case 'B':
                System.out.println("良好");
                return "优秀";
            default:
                System.out.println("其他");
                return "优秀";
        }
    }
}
