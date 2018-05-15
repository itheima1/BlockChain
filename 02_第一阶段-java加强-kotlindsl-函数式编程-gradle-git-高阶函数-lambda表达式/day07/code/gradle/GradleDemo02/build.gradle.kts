plugins {
    application
    kotlin("jvm")//支持kotlin jvm
}

//指定主类名
application {
    mainClassName = "MainKt"
}
repositories {
    mavenCentral()
}
dependencies {
   compile(kotlin("stdlib"))
}

