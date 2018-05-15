plugins{
    application
}
//查看你的工作量  把源文件名称写入到一个文件中
task("拷贝工作量"){
    //没有指定输入源和输出源
    inputs.dir("src")
    outputs.file("info.txt")

    doFirst {
        //获取src下的文件树
        val dir = fileTree("src")
        //接收文件info.txt
        val infoTxt = File("info.txt")
        //遍历文件树 找到文件
        dir.forEach {
            if(it.isFile){
                Thread.sleep(1000L)
//                infoTxt.writeText(it.name)
                infoTxt.appendText(it.name)
                infoTxt.appendText("\n")
            }
        }
    }
}



