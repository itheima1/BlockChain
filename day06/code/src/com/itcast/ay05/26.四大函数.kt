package com.itcast.ay05


/**
 * ClassName:`26.四大函数`
 * Description:除了with之外其他都是扩展函数
 */
fun main(args: Array<String>) {
    val list:ArrayList<String> = arrayListOf("林青霞","范冰冰","柳岩")

//    list?.add("刘诗诗")
//    list?.add("张三")
//    list?.add("李四")
    /*---------------------------- apply ----------------------------*/
    /**
     * 任意类型都有apply函数扩展
     * apply参数是一个函数  T.() -> Unit 带接收者的函数字面值
     * lambda表达式里this代表调用的对象
     * 在lambda表达式里可以访问对象的方法
     * apply函数返回值就是调用者本身
     */
    list?.apply {
        add("张三")
        add("张三")
        add("李四")
    }

    set {
        name
    }


    /*---------------------------- let ----------------------------*/
    /**
     * 任意对象都有let扩展函数
     * let函数参数也是一个函数
     * 函数参数它的参数是调用者本身
     * let函数返回值是函数参数的返回值 就是lambda表达式的返回值
     */
    list?.let {
        it.add("张三")
        it.add("张三")
        it.add("张三")
        "哈哈"
        10
    }
    /*---------------------------- with ----------------------------*/
    /**
     * with是独立的函数  可以在任意地方调用
     * with函数需要接收两个参数
     * 第一个参数可以接收任意类型
     * 第二个参数是函数参数,并且这个函数参数是带接收者的函数字面值 接收者就是第一个参数
     * with函数返回值是第二个函数参数的返回值
     * 相当于apply和let的结合
     */
    with(list){
        this.add("")
        this.add("")
        add("")
        "哈哈"
        10
    }

    /*---------------------------- run ----------------------------*/
    /**
     * 任意类型都有run扩展函数
     * run函数参数是待接收者的函数 接收者是调用者本身
     * run函数返回值就是函数参数的返回值
     */
    list.run {
        this.add("")
        "哈哈"
    }.length
}

/**
 * T.()->Unit
 * lambda相当于定义在T里面的函数  访问对象里面的字段或者方法
 * 调用的时候两种:1.Data().block()  2.block(Data())
 */
fun set(block:Data.()->Unit){
    block(Data())
    Data().block()
}
class Data{
    var name = "张三"
    fun haha(){

    }
    fun sayHello(){
        this.haha()
        haha()
        name
    }
}