//负责当前模块的配置
//plugins{
//    application
//    kotlin("jvm")
//}
////库
repositories {
    mavenCentral()
}
dependencies {
    compile(kotlin("stdlib"))
    //导入Core工程依赖
    compile(project(":Core"))
}


