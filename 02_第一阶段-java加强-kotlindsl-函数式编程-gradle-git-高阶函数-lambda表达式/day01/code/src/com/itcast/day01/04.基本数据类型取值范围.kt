package com.itcast.day01


/**
 * ClassName:`04.基本数据类型取值范围`
 * Description:
m */
fun main(args: Array<String>) {
    //Short类型取值范围
    var maxShort:Short = Short.MAX_VALUE
    var minShort:Short = Short.MIN_VALUE

//    println(maxShort)
////    println(minShort)

    var maxByte:Byte = Byte.MAX_VALUE
    var minByte:Byte = Byte.MIN_VALUE
//    println(maxByte)
//    println(minByte)

    var maxInt:Int = Int.MAX_VALUE
    var minInt:Int = Int.MIN_VALUE
//    println(maxInt)
//    println(minInt)

    var maxLong:Long = Long.MAX_VALUE
    var minLong:Long = Long.MIN_VALUE
//    println(maxLong)
//    println(minLong)

    var maxFloat:Float = Float.MAX_VALUE
    var minFloat:Float = -Float.MAX_VALUE//最小值
    println(maxFloat)
    println(minFloat)

    var maxDouble:Double = Double.MAX_VALUE
    var minDouble:Double = -Double.MAX_VALUE//最小值
    println(maxDouble)
    println(minDouble)
}