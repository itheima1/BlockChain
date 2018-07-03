import bean.NoteBook
import org.junit.Test
import java.io.File

/**
 * ClassName:TestNote
 * Description:
 */
class TestNote {
    @Test
    fun testAddGenesis(){
        val note = NoteBook()
        note.addGenesis("笔记首页")
        note.addGenesis("笔记首页")
    }

    @Test
    fun testaddNote(){
        val note = NoteBook()
//        note.addGenesis("笔记首页")
        note.addNote("张三给王五转了2毛钱")
    }

    @Test
    fun testListAll(){
        val note = NoteBook()
        note.addGenesis("笔记首页")
        note.addNote("张三给王五转了2毛钱")
        note.addNote("李四给王五转了5毛钱")

        val result = note.listAll()
        println(result)
    }
    @Test
    fun testSaveToDisk(){
//        val note = bean.NoteBook()
//        note.addGenesis("笔记首页")
//        note.addNote("张三给王五转了2毛钱")
//        note.addNote("李四给王五转了5毛钱")
//
//        note.saveToDisk()

        val file = File("a.txt")
        if(!file.exists())file.createNewFile()
        file.writeText("hello")
    }
    @Test
    fun testMine(){
        val note = NoteBook()
        val preHash = "0000000000000000000000000000000000000000000000000000000000000000"
        val content = "这是首页内容1"
        val noce = note.mine("${content}${preHash}")
        val hash = HashUtil.getSHA256StrJava("${noce}${content}${preHash}")
        println(noce)
        println(hash)
    }
}