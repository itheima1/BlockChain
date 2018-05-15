package com.itcast.day04


/**
 * ClassName:`14.数据类`
 * Description:
 */
fun main(args: Array<String>) {
    val news = News("标题","简介","路径","内容")
    news.title
    news.desc

    news.component1()//第一个元素
    news.component2()//第二个元素

    //解构
    val (title,desc,imgPath,content) = News("标题","简介","路径","内容")
    println(title)
    println(desc)
}

/**
 * 构造函数
 * get set
 * toString
 * hashcode
 * equeals
 * 参数
 * copy
 */
//数据类型
data class News(var title:String,var desc:String,var imgPath:String,var content:String)