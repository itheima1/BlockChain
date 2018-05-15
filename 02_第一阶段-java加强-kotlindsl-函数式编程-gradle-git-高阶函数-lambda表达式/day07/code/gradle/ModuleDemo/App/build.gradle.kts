plugins {
    application
}
application {
    mainClassName = "HelloWorldKt"
}

dependencies {
    compile(kotlin("stdlib"))
    compile("log4j","log4j","1.2.17")

    compile(project(":Core"))
}

