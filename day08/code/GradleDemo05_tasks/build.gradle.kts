



tasks{
    "opendoor"{
        //任务组
        group = "大象放冰箱"

        doFirst {
            println("打开冰箱门")
        }
    }
    "putelephant"{
        group = "大象放冰箱"

        doFirst {
            println("放入大象")
        }
    }.dependsOn("opendoor")
    "closedoor"{
        group = "大象放冰箱"

        doFirst {
            println("关闭冰箱门")
        }
    }.dependsOn("putelephant")
}