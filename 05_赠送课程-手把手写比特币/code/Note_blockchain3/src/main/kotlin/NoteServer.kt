
import bean.BroadBean
import bean.NewTransaction
import bean.NoteBook
import com.alibaba.fastjson.JSON
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.header
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.security.KeyFactory

/**
 * ClassName:NoteServer
 * Description:
 */

val note = NoteBook()
//开启websocket服务
val server = MyServer(90)

//保存其他两个节点的websocket地址信息
val list: List<MyClient> = listOf(MyClient("ws://localhost:70"), MyClient("ws://localhost:80"))

fun main(args: Array<String>) {
    //从本地磁盘数据加载
    note.loadFromDisk()

    server.setConnectionLostTimeout(0);

    server.start()

    Thread {
        //延时10秒钟连接服务
        Thread.sleep(10000)
        list.forEach { it.connect() }
    }.start()

    embeddedServer(Netty, 91) {
        routing {
            get("/addGenesis") {
                val str = call.request.queryParameters.get("content")
                if (str == null) {
                    //不需要存储  直接返回添加失败
                    call.respondText("添加首页失败,不能为空")
                    return@get
                }
                try {
                    note.addGenesis(str)
                    call.respondText("添加首页成功")
                } catch (e: IllegalArgumentException) {
                    call.respondText(e.message!!)
                }
            }

            get("/addNote") {
                val str = call.request.queryParameters.get("content")
                if (str == null) {
                    //不需要存储  直接返回添加失败
                    call.respondText("添加内容失败,不能为空")
                    return@get
                }
                try {
                    note.addNote(str)
                    call.respondText("添加内容成功")
                } catch (e: IllegalArgumentException) {
                    call.respondText(e.message!!)
                }
            }

            get("/listAll") {
                val result = note.listAll()
                call.respondText(result)
            }


            get("/loadFromDisk") {
                note.loadFromDisk()
                call.respondText("加载成功")
            }

            get("/check") {
                //检查每一条消息有没有被修改过
                val list = note.list

                val sb = StringBuilder()

                list.forEachIndexed { index, block ->
                    if (index == 0) {
                        //第一条数据  直接验证hash
                        //内容的sha256值和保存的hash值是否相等
                        if (HashUtil.getSHA256StrJava("${block.noce}${block.content}${block.preHash}") == block.hash) {
                            //没有修改
                            sb.append("${index}条数据是正确的\n")
                        } else {
                            //修改了
                            sb.append("${index}条数据被修改了\n")
                        }
                    } else {
                        //后面的数据  preHash和上一条hash是否一致  计算hash值和保存的hash值是否一致
                        if (block.preHash == list[index - 1].hash && HashUtil.getSHA256StrJava("${block.noce}${block.content}${block.preHash}") == block.hash) {
                            //没有修改
                            sb.append("${index}条数据是正确的\n")
                        } else {
                            //修改了
                            sb.append("${index}条数据被修改了\n")
                        }
                    }
                }
                //返回结果
                call.respondText(sb.toString())
            }

            /**
             * 1.广播任务给所有的其他的节点
             * 2.自己也要挖矿
             * 3.每一个节点挖矿完成之后  要广播给其他节点
             * 广播的类型 1.任务广播  2.通知广播(当前节点的集合广播出去)
             *
             */
            post("/addTransaction") {
                val receiveMsg = call.receive<String>()

                //1.将接收的数据转换为对象类型
                val newTransaction = JSON.parseObject(receiveMsg, NewTransaction::class.java)

                //需要广播通知给所有的节点
                val broadBean = BroadBean(1, 1, newTransaction, null)
                //对象变成字符串
                val str = JSON.toJSONString(broadBean)
                server.broadcast(str)


                //2.加密的字符串和明文
                var sign = newTransaction.encrypted
                val content = newTransaction.content
                val publicStr = newTransaction.publicKey
                val publicKey = RSAUtil.createPublicKey(publicStr)


                val keyf = KeyFactory.getInstance("RSA")
                val signet = java.security.Signature.getInstance("SHA256withRSA")
                signet.initVerify(publicKey)
                signet.update(content.toByteArray())
                val result =  signet.verify(toBytes(sign))


                //允许跨域访问
                call.response.header("Access-Control-Allow-Origin", "*")
                if (result) {
                    //添加到NoteBook中
                    note.addNote(content)
                    //广播给其他节点
                    val notifyBean = BroadBean(2, 1, null, note.list)
                    val str = JSON.toJSONString(notifyBean)
                    server.broadcast(str)
                } else {
                    //提示错误
                    call.respondText("转账失败")
                }

            }
        }
    }.start(wait = true)

}