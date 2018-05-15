package com.itcast.ay05


/**
 * ClassName:`16.常见的lambda表达式`
 * Description:
 */
fun main(args: Array<String>) {
    /**
     * foreach是一个扩展函数
     * foreach参数是一个函数
     *
     */
    //foreach
    val str = "张三"
//    str.forEach(::println)
//    str.forEach({char->
//        println(char)
//    })
    //lambda表达式在最后一位  可以括号前移 迁移之后()没有参数 可以省略
//    str.forEach{char->
//        println(char)
//    }
    //lambda只有一个参数 只有一个参数可以使用it
    str.forEach{
        println(it)
    }

    /*---------------------------- indexof ----------------------------*/
    /**
     * indexOfFirst 是Array类的扩展函数
     * indexOfFirst参数是函数类型  函数参数类型时数组每一个元素的类型  函数的返回值是boolean类型
     */
    val array = arrayOf("林青霞","张曼玉")
    val index = array.indexOfFirst{
        it.startsWith("林")
    }

}
fun myPrint(char:Char){
    println(char)
}