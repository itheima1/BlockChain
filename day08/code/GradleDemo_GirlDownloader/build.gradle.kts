plugins {
    application
    kotlin("jvm")//支持kotlin jvm
}
//代码仓库
repositories {
    mavenCentral()
    jcenter()
}
//依赖的配置
dependencies {
    compile(kotlin("stdlib"))
    // https://mvnrepository.com/artifact/commons-httpclient/commons-httpclient
//    compile group: 'commons-httpclient', name: 'commons-httpclient', version: '3.1'
   compile("commons-httpclient","commons-httpclient","3.1")
}
