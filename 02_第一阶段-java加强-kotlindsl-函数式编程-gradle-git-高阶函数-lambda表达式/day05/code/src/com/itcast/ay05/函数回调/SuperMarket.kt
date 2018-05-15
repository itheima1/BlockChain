package com.itcast.ay05.函数回调


/**
 * ClassName:SuperMarket
 * Description:超市对象
 */
class SuperMarket {
    //高阶函数
    fun buySoy(block:((Soy)->Unit)?) {
        Thread {
            Thread.sleep(5000L)
            //创建Soy对象
            val soy = Soy("海天")
            //返回Soy对象
//            block(soy)
            block?.invoke(soy)

        }.start()
    }
}