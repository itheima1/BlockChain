package com.itcat.day02.comm


/**
 * 只有尾递归才能优化
 * 第一步:需要将递归转换为尾递归
 * 第二步:加上tailrec
 *
 * 尾递归优化的原理:将递归转换为迭代
 */
fun main(args: Array<String>) {
    val result = sum2(100000)
    println(result)
}

//fun sum2(n:Int):Int{
//    if(n==1){
//        return 1
//    }else{
//        return n+ sum2(n-1)//调用自身之后做了其他操作
//    }
//}


//尾递归
//
tailrec fun sum2(n:Int,result:Int=0):Int{
    if(n==1){
        return result+1
    }else{
        return sum2(n-1,result+n)//调用自身之后做了其他操作
    }
}
