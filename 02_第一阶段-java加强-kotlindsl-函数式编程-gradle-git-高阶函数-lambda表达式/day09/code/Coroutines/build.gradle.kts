group = "com.itcast.coroutines"
version = "1.0-SNAPSHOT"

plugins {
    //添加支持java开发的插件
    java
    //支持开发kotlin的插件
    kotlin("jvm")
}
//仓库
repositories {
    mavenCentral()
    //协程jar包的仓库
    jcenter()
}
//依赖
dependencies {
    compile(kotlin("stdlib"))
    //配置协程jar包依赖
    compile("org.jetbrains.kotlinx:kotlinx-coroutines-core:0.22.5")
}