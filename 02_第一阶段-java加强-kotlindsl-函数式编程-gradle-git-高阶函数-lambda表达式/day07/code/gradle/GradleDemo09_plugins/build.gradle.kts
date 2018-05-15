//插件 定义很多执行的任务

//gradle自带的插件
//application 支持发布应用程序
//java插件没有发布的作用 只有构建java项目
//plugins {
////    application
////    java
////    war
//
//    kotlin("jvm")
//}

//三方的公司或者个人开发了很多的插件  也可以使用 需要到gradle的官方插件市场里面查找

//buildscript {
//    repositories {
//        maven {
//            setUrl("https://plugins.gradle.org/m2/")
//        }
//    }
//    dependencies {
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.2.41")
//    }
//}
//
//apply{
//    plugin("org.jetbrains.kotlin.jvm")
//}


plugins {
    id("org.jetbrains.kotlin.jvm")  version("1.2.41")
}

//gradle的插件使用
/**
 * plugins{
 * }
 */
//插件市场插件
/**
 * 可以通过plugins{}使用  或者 apply{}这种方式
 */