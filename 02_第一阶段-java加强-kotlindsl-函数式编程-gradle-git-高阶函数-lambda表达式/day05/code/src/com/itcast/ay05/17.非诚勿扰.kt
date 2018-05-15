package com.itcast.ay05


/**
 * ClassName:`17.非诚勿扰`
 * Description:
 */
val list =
        listOf(Girl("依儿", 18, 168, "山东"),
                Girl("笑笑", 19, 175, "河南"),
                Girl("小百合", 17, 155, "福建"),
                Girl("michel", 22, 148, "广东"),
                Girl("猫咪", 28, 159, "广西"),
                Girl("玲儿", 23, 169, "广东"),
                Girl("环环", 25, 172, "安徽"),
                Girl("胖嘟嘟", 32, 180, "河北"),
                Girl("乔乔", 35, 180, "广东"),
                Girl("小可爱", 27, 150, "江西"),
                Girl("一生有你", 22, 163, "山东"),
                Girl("敏儿", 28, 155, "黑龙江"),
                Girl("月儿", 25, 178, "吉林"),
                Girl("花儿", 21, 183, "山东"),
                Girl("S小糖", 49, 190, "新疆"),
                Girl("悦悦", 19, 160, "广西"),
                Girl("小可爱", 29, 158, "广东"),
                Girl("紫琪", 49, 149, "新疆"),
                Girl("糖心", 26, 165, "甘肃"),
                Girl("棒棒糖", 23, 172, "浙江"),
                Girl("猪猪侠", 18, 173, "山东"),
                Girl("喵喵", 27, 164, "河南"),
                Girl("安琦", 19, 159, "河北"),
                Girl("叶子", 20, 160, "广东"))


data class Girl(var name: String, var age: Int, var height: Int, var place: String)

fun main(args: Array<String>) {
    //遍历  判断添加  添加到新的集合中
    /*---------------------------- 俺是河南里,俺只找河南的妹子 ----------------------------*/
//    val list1 = ArrayList<Girl>()
//    //java方式
//    list.forEach {
//        if(it.place=="河南") {
//            list1.add(it)
//        }
//    }
//    println(list1)
    /**
     * filter参数是函数类型
     * filter的predicate函数的参数 是Girl类型
     * predicate 返回值 boolean
     * filter函数返回值 List集合
     */
    val list1 = list.filter {
        it.place=="河南"
    }
//    println(list1)
    /*---------------------------- 我只喜欢30岁以下的女生 ----------------------------*/
    val list2 = list.filter {
        it.age<30
    }
//    println(list2)
    /*---------------------------- 我喜欢广东的 ,身高162以上, 25岁以下的妹子 ----------------------------*/
    val list3 = list.filter {
        it.place=="广东"&&it.height>162&&it.age<25
    }
//    println(list3)
    /*---------------------------- 我喜欢广东的, 身高160以上, 35岁以上的妹子 ----------------------------*/
    val list4 = list.filter {
        it.place=="广东"&&it.height>160&&it.age>35
    }
    println(list4)
}