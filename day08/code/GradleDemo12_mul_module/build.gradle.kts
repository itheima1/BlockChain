//负责整个工程的配置
//给每一个子项目配置

//查找插件 gradle构建的时候需要的配置
buildscript {
    repositories {
        maven {
            setUrl("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.2.41")
    }
}
//subprojects {  }
allprojects {//只要在模块里面可以配置的属性都可以配置
    //支持不太好
//    plugins{
//        application
//        kotlin("jvm")
//    }


   apply{
       plugin("org.jetbrains.kotlin.jvm")
   }
}

