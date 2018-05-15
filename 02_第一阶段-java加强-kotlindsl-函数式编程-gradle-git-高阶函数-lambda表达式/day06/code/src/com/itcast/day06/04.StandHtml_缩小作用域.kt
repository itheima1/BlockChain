package com.itcast.day06.缩小


/**
 * ClassName:`03.StandHtml`
 * Description:
 */
fun main(args: Array<String>) {
//    val html = Html()//"<html></html>"
//    val head = Head()//"<head></head>"
//    val body = Body()//"<body></body>"
//    val div = Div()//"<div></div>"
//
//    //先放入head
//    html.setTag(head)
//    //div放到body中
//    body.setTag(div)
//    //body放到html中
//    html.setTag(body)
//
//    println(html.toString())

    /*
    html{
         head{
             title{
                 ""
             }
         }
         body{
             div{

             }
         }
     }
     */

    val result: Html =
            html {
                head {

                }
                body {
                    div {
//                        body {//可以访问最外面的html的作用域  严格限制作用域
//
//                        }
                    }
                }
            }
    println(result)
}

//div高阶函数
fun Body.div(block: () -> Unit) {
    //创建Div标签
    val div = Div()
    //添加到Body里面
    setTag(div)
}


//body高阶函数
fun Html.body(block: Body.() -> Unit) {
    //Body标签
    val body = Body()
    //执行block函数
    body.block()
    //添加到Html对象中(在html函数中创建的对象)
    setTag(body)
}

//head高阶函数
fun Html.head(block: () -> Unit) {
    //创建Head标签
    val head = Head()
    //添加到html中html对象
    setTag(head)
}

//html
fun html(block: Html.() -> Unit): Html {
    val html = Html()
    //执行block函数
    html.block()

    return html
}


//div标签
class Div : Tag("div")

//body标签
class Body : Tag("body")

//head标签
class Head : Tag("head")

//Html标签类
class Html : Tag("html")

//任何标签的父类
@MYTAG
open class Tag(var name: String) {

    //还应该有容器保存其他的标签
    val list = ArrayList<Tag>()

    //定义方法传递标签
    fun setTag(tag: Tag) {
        list.add(tag)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        //前面标签
        sb.append("<$name>")
        //子标签
        list.forEach {
            //子标签的toString
            sb.append(it.toString())
        }
        //后面标签
        sb.append("</$name>")
        return sb.toString()
    }
}

@DslMarker
annotation class MYTAG