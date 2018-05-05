package com.itcat.day02


/**
 * ClassName:`03.标签处返回`
 * Description:
 */
fun main(args: Array<String>) {
    val str1 = "abc"
    val str2 = "123"
    //abc和123所有的组合
    tag@for (c1 in str1) {
        tag3@for (c2 in str2) {
            // b 2  后面的元素不需要再打印了
            println("$c1 $c2")
            if(c1=='b'&&c2=='2'){
                break@tag3
//                continue
//                return
            }
        }
    }

    println("最终执行的代码")
}