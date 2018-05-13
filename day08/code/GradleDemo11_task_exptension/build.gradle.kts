plugins {
    java
}
//拷贝java文件夹下所有的文件到temp目录中
task("拷贝文件",Copy::class){
    from("src/main/java")
    into("temp")
}
task("删除文件",Delete::class){
    delete("temp")
}