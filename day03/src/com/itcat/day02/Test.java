package com.itcat.day02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * ClassName:Test
 * Description:java的编译时异常必须要处理
 */
class Test {
    public static void main(String[] args){
        File file = new File("a.txt");
//        if(!file.exists())return;//排除了文件未空的情况
        //编译时异常  FileReader创建的时候抛出了异常  需要捕获或者跑出去
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {//文件找不到异常
            e.printStackTrace();
        }
    }
}
