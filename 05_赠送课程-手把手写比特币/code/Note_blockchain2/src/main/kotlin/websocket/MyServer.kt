import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import java.lang.Exception
import java.net.InetSocketAddress

/**
 * ClassName:MyServer
 * Description:服务端
 */
class MyServer(port:Int):WebSocketServer(InetSocketAddress(port)) {
    override fun onOpen(conn: WebSocket?, handshake: ClientHandshake?) {
        println("服务端连接打开,打开的端口:"+address.port)
        println("连接数:"+connections.size)
    }

    override fun onClose(conn: WebSocket?, code: Int, reason: String?, remote: Boolean) {
        println("服务端连接关闭")
    }

    override fun onMessage(conn: WebSocket?, message: String?) {
        println("服务端收到了消息:"+message)
    }

    override fun onStart() {
        println("服务端服务开启成功")
    }

    override fun onError(conn: WebSocket?, ex: Exception?) {
        println("服务端服务失败:"+ex!!.message)
    }

    /**
     * 开始服务
     */
    fun startServer(){
        Thread(this).start()
    }
}