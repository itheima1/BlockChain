//获取默认属性
task("打印默认属性"){
    doFirst {
        project.properties.forEach { t, any ->
            println("$t ---- $any")
        }
    }
}