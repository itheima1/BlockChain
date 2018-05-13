
//distrZip  编译  class  jar  普处理文件
task("opendoor"){

}
//放入大象依赖于打开冰箱门
task("putelephant"){

}.dependsOn("opendoor")
//关闭冰箱门依赖于放入大象
task("closedoor"){

}.dependsOn("putelephant")