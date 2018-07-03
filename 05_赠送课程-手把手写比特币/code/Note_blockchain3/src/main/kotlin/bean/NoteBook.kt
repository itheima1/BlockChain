package bean

import HashUtil
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.TypeReference
import java.io.File

/**
 * ClassName:bean.NoteBook
 * Description:笔记本对象
 */
class NoteBook {
    //本地文件地址
    val notePath = "note.txt"
    //保存消息
    val list = ArrayList<Block>()
    /**
     * 添加首页(创世区块)
     */
    fun addGenesis(genesis:String){
        //如果list集合不为空  不能再添加了
        require(list.size==0,{"首页已经添加了"})
        //preHash
        val preHash = "0000000000000000000000000000000000000000000000000000000000000000"
        //工作量证明noce
        val noce = mine("${genesis}${preHash}")
        //计算内容的sha256的值
        val hash = HashUtil.getSHA256StrJava("${noce}${genesis}${preHash}")

        list.add(Block(list.size + 1, genesis, hash, noce, preHash))
        //重新保存到磁盘中
        saveToDisk()
    }

    /**
     * 添加笔记内容
     */
    fun addNote(content:String){
        require(list.size>0,{"必须要添加首页之后才能添加笔记内容"})
        //preHash
        val preHash = list.last().hash
        //工作量证明noce
        val noce = mine("${content}${preHash}")
        //计算内容的sha256的值
        val hash = HashUtil.getSHA256StrJava("${noce}${content}${preHash}")
        list.add(Block(list.size + 1, content, hash, noce, preHash))
        //重新保存到磁盘中
        saveToDisk()
    }

    /**
     * 列举所有的笔记内容和首页内容
     */
    fun listAll():String{
        return JSON.toJSONString(list)
    }

    /**
     * 将笔记内容持久化到硬盘中
     */
    private fun saveToDisk(){
        //将list集合转换为json字符串  xml json
        val str = listAll()
        val file = File(notePath)
        file.writeText(str)
    }

    /**
     * 从磁盘中获取数据
     */
    fun loadFromDisk(){
        val file = File(notePath)
        //如果文件不存在 就返回
        if(!file.exists())return
        //读取内容  json字符串
        val str = file.readText()
        //长度为0
        if(str.isEmpty())return
        //将读取的字符串转换为list集合 添加到集合中
        val result = JSON.parseObject(str,object:TypeReference<ArrayList<Block>>(){})
        //清空集合
        list.clear()
        //添加到list集合中
        list.addAll(result)
    }

    /**
     * 挖矿  生成的hash值必须以000000开头
     */
    fun mine(content:String):Int{
        for (i in 0..Int.MAX_VALUE) {
            val hash = HashUtil.getSHA256StrJava("${i}${content}")
            if(hash.startsWith("000")){
                return i
            }
        }
        throw Exception("挖矿失败")
    }

    /**
     * 更新本地集合
     */
    fun updateList(list:ArrayList<Block>){
        this.list.clear()
        this.list.addAll(list)
        //保存到本地
        saveToDisk()
    }
}
//sha256
class Block(val id:Int,
            val content:String,
            val hash:String,
            val noce:Int,
            val preHash:String)