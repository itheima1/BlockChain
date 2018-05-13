//gradle会首先找到每一个任务  执行每一个任务中闭包中的逻辑  这称为扫描生命周期
//如果想让执行不是扫描时生命周期 可以用doFirst  doLast
//放入大象依赖于打开冰箱门
task("putelephant") {
    doFirst {
        println("放入大象")
    }

}.dependsOn("opendoor")

//关闭冰箱门依赖于放入大象
task("closedoor") {
    doFirst {
        println("关闭冰箱门")

    }

}.dependsOn("putelephant")


//distrZip  编译  class  jar  普处理文件
task("opendoor") {
    doLast {
        println("打开冰箱门")
    }
}