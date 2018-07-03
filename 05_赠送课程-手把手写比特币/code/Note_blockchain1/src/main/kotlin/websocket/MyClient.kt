
import bean.BroadBean
import com.alibaba.fastjson.JSON
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.lang.Exception
import java.net.URI
import java.security.KeyFactory

/**
 * ClassName:MyClient
 * Description:客户端
 */
class MyClient(path: String) : WebSocketClient(URI(path)) {
    override fun onOpen(handshakedata: ServerHandshake?) {
        println("客户端连接打开,连接服务器地址:" + uri.path)
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        println("客户端连接关闭:"+reason)
    }

    override fun onMessage(message: String?) {
        if (message == null) return

        val broadBean = JSON.parseObject(message, BroadBean::class.java)
        println("消息类型:${broadBean.type} 消息来源:${broadBean.from}")

        //1.任务  2.广播
        val type = broadBean.type
        if (type == 1) {
            val newTransaction = broadBean.transaction!!

            //验证数据
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

            if (result) {
                println("节点1验证是成功的")
                //存储到本地节点
                note.addNote(newTransaction.content)
                println("挖矿完成")
                //广播给其他节点
                val notifyBean = BroadBean(2,1,null,note.list)
                val str = JSON.toJSONString(notifyBean)
                server.broadcast(str)
            } else {
                println("节点1验证是失败的")
            }
        }else if(type==2){
            println("接收到更新通知")
            //获取节点账本的list
            val list = broadBean.list!!
            //如果广播其它节点长度比当前节点长度长  更新本地节点
            if(list.size>note.list.size){
                //note更新(list)
                note.updateList(list)
            }
        }
    }

    override fun onError(ex: Exception?) {
        println("客户端连接失败")
    }
}