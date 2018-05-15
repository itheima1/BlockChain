//Project就是接口
//gradle构建的时候:首先根据build.gradle配置文件创建一个Project实例  执行project实例
//配置文件中所有的代码都会通过task任务的方式插入到project中
//project实例可以在配置文件中通过project隐式调用

//task任务 每一个操作都可以定义成一个任务  前面学的application插件里面已经打包好了很多任务  可以直接使用
task("编译java文件"){
    println("开始编译java文件")
}