package com.itcat.day02


/**
 * ClassName:`08.数组的定义`
 * Description:Any 类似 Object
 * 8中基本数据类型的数组可以通过Array创建或者通过自己的数据类型Array创建
 * 字符串数组只能通过Array创建
 */
fun main(args: Array<String>) {
    /*---------------------------- 定义数组并赋值 ----------------------------*/
    //张三  李四  王五
    val arr = arrayOf("张三","李四","王五")
    //10 20 30
    val arr1 = arrayOf(10,20,30)
    //a b c
    val arr2 = arrayOf('a','b','c')
    //"张三"  10  'a'
    val arr3 = arrayOf("张三",10,'a')
    /*---------------------------- 8中基本数组类型数组 ----------------------------*/
    //保存年纪信息
    val arr4 = IntArray(10)//new int[10]
    val arr5 = IntArray(10){//把数组里面每一个元素都初始化为30
        30
    }

//    BooleanArray
//    ByteArray
//    ShortArray
//    CharArray
//    FloatArray
//    DoubleArray
//    LongArray
//    StringArray//不能用
}