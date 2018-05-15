package com.itcast.ay05


/**
 * ClassName:`18.过滤`
 * Description:
 */
fun main(args: Array<String>) {
    val list = listOf("张三","李四","王五","赵六","张四","李五","李六")
    val list2 = listOf("周芷若","张无忌","张五","李善长","林青霞","李寻欢")

//    找到第一个姓张的
    val list1 = list.find {
        it.startsWith("张")
    }
//    println(list1)
//    把第一个集合中所有姓张的找出来
    val list3 = list.filter { it.startsWith("张") }
//    println(list3)
//    把两个集合中所有姓张的找到并存放在同一个集合中
    val mulList = mutableListOf<String>()
    list.filterTo(mulList){
        it.startsWith("张")
    }
    list2.filterTo(mulList){
        it.startsWith("张")
    }
//    println(mulList)

//    把第一个集合中角标为偶数的元素找出来
    val list5 = list.filterIndexed { index, s ->
        index%2 == 0
    }

    println(list5)
}