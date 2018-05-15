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
    // https://mvnrepository.com/artifact/junit/junit
//    testCompile group: 'junit', name: 'junit', version: '4.12'
    testCompile("junit","junit","4.12")
}
