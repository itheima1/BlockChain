package com.itcat.day02;

/**
 * ClassName:Operator
 * Description:
 */
class Operator {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        int c = a + b;
//        int d  =a++;//先赋值给d再+1
//        int d  =++a;//把a+1之后赋值给d
//        System.out.println(d);

        /*---------------------------- 对象相加 ----------------------------*/
        Girl girl1 = new Girl("张三",10);
        Girl girl2 = new Girl("李四",20);
//        girl1.age + girl2.age;//对象不能使用运算符

    }
    //妹子类型
    static class Girl{
        String name;
        int age;

        public Girl(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
