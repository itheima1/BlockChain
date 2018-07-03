
import com.sun.org.apache.xml.internal.security.utils.Base64
import java.io.ByteArrayOutputStream
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

/**
 * ClassName:RSAUtil
 * Description:RSA加密解密工具类
 */
object RSAUtil {
    val factory = KeyFactory.getInstance("RSA")
    /**
     * 根据字符串生成公钥对象
     */
    fun createPublicKey(publicStr:String):PublicKey{
        return factory.generatePublic(X509EncodedKeySpec(Base64.decode(publicStr.toByteArray())))
    }
    /**
     * 根据字符串生成私钥对象
     */
    fun createPrivateKey(privateStr:String):PrivateKey{
        return factory.generatePrivate(PKCS8EncodedKeySpec(Base64.decode(privateStr.toByteArray())))
    }
    /**
     * 通过私钥进行解密
     */
    fun decryptByPrivateKkey(content:String,privateKey: PrivateKey):String{
        //解密的字节数组
        val byteArray = Base64.decode(content.toByteArray())
//    val byteArray = content.toByteArray()

        val cipher = Cipher.getInstance("RSA")
        cipher.init(Cipher.DECRYPT_MODE, privateKey)

        //单次最大加密的字节数
        val MAX_DECODE_BYTE = 256
        //偏移
        var offset = 0
        //内存输出流
        val baos = ByteArrayOutputStream()

        while (byteArray.size - offset > 0) {
            if (byteArray.size - offset >= MAX_DECODE_BYTE) {
                //普通段解密
                val arr = cipher.doFinal(byteArray,offset,MAX_DECODE_BYTE)
                baos.write(arr)
                offset += MAX_DECODE_BYTE
            } else {
                //最后一段解密
                val arr = cipher.doFinal(byteArray,offset,byteArray.size-offset)
                baos.write(arr)
                offset = byteArray.size
            }
        }

        val resultStr = String(baos.toByteArray())
        return resultStr
    }
}