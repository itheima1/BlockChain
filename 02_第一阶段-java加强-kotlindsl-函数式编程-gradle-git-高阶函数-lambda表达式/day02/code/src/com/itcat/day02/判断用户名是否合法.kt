package com.itcat.day02


/**
 * ClassName:判断用户名是否合法
 * Description:
 */
fun main(args: Array<String>) {
    val name = "hhew2383dW_fkfE"
    println(parse(name))
}

//判断用户名是否合法
fun parse(name: String): Boolean {
    //需要在3到20位
    if (name.length < 3 || name.length > 20) return false

    val upChar = 'A'..'Z'
    val lowChar = 'a'..'z'
    val intRange = '0'..'9'

    var intCount = 0
    var upCount = 0
    var lowCount = 0

    // 必须有2个或以上大写字母
    // 2个或以上小写字母
    // 3个或以上数字

    name.forEach {
        when (it) {
            in upChar -> {
                //大写字母
                upCount++
            }
            in lowChar -> {
                //小写字母
                lowCount++
            }
            in intRange -> {
                //数字
                intCount++
            }
            '_' -> {

            }
        //用户名是数字字母或者_
            else -> {
                return false
            }
        }
    }

    return upCount>=2&&lowCount>=2&&intCount>=3
//    if(upCount>=2&&lowCount>=2&&intCount>=3){
//        return true
//    }else{
//        return false
//    }
}