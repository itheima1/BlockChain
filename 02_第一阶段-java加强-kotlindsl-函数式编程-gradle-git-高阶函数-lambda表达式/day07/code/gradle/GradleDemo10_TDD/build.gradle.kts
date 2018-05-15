
plugins {
    application//发布功能
    kotlin("jvm")
}
//指定主类名
application {
    mainClassName = "MainKt"
}


//标准库
repositories {
    mavenCentral()
}
//依赖
dependencies {
    compile(kotlin("stdlib"))
    // https://mvnrepository.com/artifact/junit/junit
//    testCompile group: 'junit', name: 'junit', version: '4.12'
    testCompile("junit:junit:4.12")
}