package com.itcat.day02


/**
 * ClassName:`22.递归和迭代的对比`
 * Description:
 * 常见的需求:通过迭代和递归都可以解决 复杂的问题用递归更容易实现
 * 递归和迭代有什么优缺点呢?
 * StackOverflowError 递归如果递归的层级比较深容易栈内存溢出
 * 递归:优点:逻辑比较简单  容易实现  缺点:容易栈内存溢出(内存开销比较大)
 * 迭代:优点:内存开销小 缺点:抽象出数学模型
 */
fun main(args: Array<String>) {
    //求1到n的数据和  1到10  55  1到100 5050
//    val result = sum1(100000)
//    println(result)
    val result = sum2(100000)
    println(result)
}
//求1到n的和  1到10  1到9的和+10   1到n的和  1到n-1的和 + n
fun sum2(n:Int):Int{
    if(n==1){
        return 1
    }else{
        return n+ sum2(n-1)
    }
}
/**
 * 求1到n的和  通过迭代的方式求和
 */
fun sum1(n: Int): Int {//kotlin里面参数是固定  不能修改
    var result = 0
    var copyN = n
    while (copyN > 0) {
        result += copyN
        copyN--
    }
    return result
}