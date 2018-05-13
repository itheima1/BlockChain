//高版本兼容低版本 理论上高版本的logging的jar包应该支持低版本的jar包
plugins {
    application
    kotlin("jvm")//支持kotlin jvm
}
//关闭gradle默认处理方案 遇到版本冲突的时候不要直接选择最高版本  提示冲突
configurations.all {
    resolutionStrategy {
        failOnVersionConflict()
        // https://mvnrepository.com/artifact/commons-logging/commons-logging
//        compile group: 'commons-logging', name: 'commons-logging', version: '1.2'

        force("commons-logging:commons-logging:1.2")
    }
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
    compile("commons-httpclient", "commons-httpclient", "3.1"){
        exclude("commons-httpclient","commons-httpclient")
    }
    //spring
    // https://mvnrepository.com/artifact/org.springframework/spring-core
//    compile group: 'org.springframework', name: 'spring-core', version: '2.5.6'
    compile("org.springframework", "spring-core", "2.5.6")
}

//1.默认情况下 gradle选择依赖最高版本
//2.如果不想让gradle自己解决依赖可以加上
/**
 * configurations.all {
    resolutionStrategy {
    failOnVersionConflict()

}
}
 */
//3.自己解决 可以选择将某一个库依赖的版本去掉
/**
 * compile("commons-httpclient", "commons-httpclient", "3.1"){
    exclude("commons-httpclient","commons-httpclient")
    }
 */
//4.自己解决  强制指定一个版本号
/**
 * configurations.all {
    resolutionStrategy {
    failOnVersionConflict()

    force("commons-logging:commons-logging:1.2")
    }
}
 */

