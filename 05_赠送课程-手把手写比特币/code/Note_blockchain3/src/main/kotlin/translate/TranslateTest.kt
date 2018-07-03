package translate

import HashUtil
import com.sun.org.apache.xml.internal.security.utils.Base64
import java.io.ByteArrayOutputStream
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher


/**
 * ClassName:TranslateTest
 * Description:
 */

val publicStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAn0nlLcOd1d+45w+C7sV9mchZDkpI2S0x\n" +
        "fh0uQbp1ug7JSIG0D55NXJ7bFNno0NTNrzOFW4bDx6UvXS3UDfo+Iw/DJ+Mgg7ROk0VZ7OLADl/l\n" +
        "l2deu6Sp//ElHprcpxBX+dFKaf1wuhyIPFMB727MxkENbClWXOzVs36i+YiEp8QdGQYBoZ9I6yAo\n" +
        "hzUbKZzYOt63gTtqrmK58BuLLpcK364VlwUhSrUheLvQGbUL36ejXBJ0P4oLgSqqTrKQ2+ujZ6Ki\n" +
        "/HhBpj/ks2sdtmbVRJmJ0O7j2FJdy+oem2JXI8Pql3Si+FPil8nTD1G4EANdKvAgsULnRAcO9K+w\n" +
        "95QIwwIDAQAB"
val privateStr = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCfSeUtw53V37jnD4LuxX2ZyFkO\n" +
        "SkjZLTF+HS5BunW6DslIgbQPnk1cntsU2ejQ1M2vM4VbhsPHpS9dLdQN+j4jD8Mn4yCDtE6TRVns\n" +
        "4sAOX+WXZ167pKn/8SUemtynEFf50Upp/XC6HIg8UwHvbszGQQ1sKVZc7NWzfqL5iISnxB0ZBgGh\n" +
        "n0jrICiHNRspnNg63reBO2quYrnwG4sulwrfrhWXBSFKtSF4u9AZtQvfp6NcEnQ/iguBKqpOspDb\n" +
        "66NnoqL8eEGmP+Szax22ZtVEmYnQ7uPYUl3L6h6bYlcjw+qXdKL4U+KXydMPUbgQA10q8CCxQudE\n" +
        "Bw70r7D3lAjDAgMBAAECggEAEd3hBfQWJSI2BDzaK9wdDX3Kd27fovSNw56UTK0UfW0hKK/buZDB\n" +
        "Dq2G11zl316vP4Kg57ZDCYa+vE3ipjq2c4pWEohIYl18Hsq9nnOEpVgeUZA3sQZ0RxAOYNNwApEz\n" +
        "vQK7vQ2/cjC3G0UYR3n/IBG15rbwR/YMtlhzAVhNwN9x2cykGojymOcSRoaNJ/361JYoZwzZ6hnw\n" +
        "xnYQ97tXCEzQkllcib9oNvGD0x3eAJk/4wy/0sEaHhxKZQh2NdXXCwT//JP2p/b82E9Jl09ZgLGC\n" +
        "mPlNHJvXmU/LzyJBoCeJh8dTIFa5B5nGHud34oweHJ9VhmeO1cEeL5QQ+s9n+QKBgQDklXCvkpR7\n" +
        "oNyagRMwk5H1WAgVAH4oBpxw1rWu+P3Qr+BLXqQy14nT39HhGpW3oVDD5NaTsY1l2SUgL6cWbnWB\n" +
        "4EtgwLS4K2bMwlPzWLs9g27OS7LtE0HbVKlZyMv1FBzpg2l00ePMAEW/Rx6mwmavOrMyfcJdJceP\n" +
        "3zlMob3r3QKBgQCyZMfa81HOTiQZ9zLRUaQfkRG7x1sfzAhabZxYwFTWvQ9nsa1Xg4ELRydWpjCH\n" +
        "UNuKPPL+EaD08iZ3cZXmYBY6BAN4FoCqHROAgu8M4gr8Sxvr8ggbMzfa2Mjcin87kKOjG+JnPYg+\n" +
        "fYIjAwiWlCIJd9Tv85hRGTM0iJ7fy39NHwKBgE8QEWXpAUqxnRwjwQtwP2/o/wKkpP4bK0ksOrNG\n" +
        "lCP2cVIgbhLX0mhiZinCFD7roQ/guwdixlGQBfNYCCcOFb7SYcAZZ2i8OlZgcMEe08S3DUB1lnHB\n" +
        "02mVou3XMhSW3Fk4huVNI8mP3pAGfWeYngPF/e1c6jlls9zjjOIgn5xRAoGAB6QHZ1pam6J/K1mK\n" +
        "JKsBg5ScFNXiEkeT6AOf2bFLMPNiEIfsq73nLgt7JNvzYihVbuNTaQxqP3OLsDv+NqLQ8OUxHNih\n" +
        "5CdgqXVyNbQM0meJJ57zN+8Gqmn0mHWaI3v7dkYAeXmKN1r60vMTUBfCKXOnfCuLz5VruwlK/Lzj\n" +
        "k/MCgYBKPotVvu3Lo+pHnHxOqTrGp2I8Gx+Im1Uo51lmDlvUFGSR59NZggadrF9qpcB12blw5rFw\n" +
        "lDHj1NI3Ig0vPwG/lKIdPYYnLengKGGPSeJPYM0yw6nTn9JOoyJ25dVbz3Fhw1HhtYQchSthDVt+\n" +
        "Yyd2imrk32K/xNiVEfCwc1+F5w=="

val toPublicStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg2V+DYbFTGWnL/xrU8dCyENmA/wLO7k8\n" +
        "8e6SaN1VBKHUozhoNet0y+4lvrGe7lV7ParmwhFIqBd0gKvOd3w+PXtMF0p3XsB5PNtcWr4kgHVB\n" +
        "4437XRPD1/SeySc37EM3oQ11qQWMzAdA1i/D+Qb51mqjVXyMEFRnNm5Vv2H2L82KwE1YwjFjtsOr\n" +
        "pUfjvv9DmeRvLCaFqTPG/gPTN3K6N0L5il9soAF1F5J+uhMs5knsAoEOhiLVIrwcFrcRuVJyhMbt\n" +
        "7bKaQSrByFpeJAB2HRZrJjAlMhAxGY46hI+pIKBs4fPJI6fmfbj62c/PTNhATDyI6UB07HhpvwdT\n" +
        "NDXGywIDAQAB"

fun main(args: Array<String>) {
    val str = "张三给王五转5毛钱"
    /*---------------------------- 生成PublicKey以及PrivateKey ----------------------------*/
    val factory = KeyFactory.getInstance("RSA")
    val publicKey = factory.generatePublic(X509EncodedKeySpec(Base64.decode(publicStr.toByteArray())))
    val privateKey = factory.generatePrivate(PKCS8EncodedKeySpec(Base64.decode(privateStr.toByteArray())))

    val wallet = Wallet(publicKey, privateKey)

    /*---------------------------- 对消息进行处理 ----------------------------*/
    val encodeStr = hashAndEncode(str, wallet.privateKey)

    /*---------------------------- 创建传输对象 ----------------------------*/
    val transaction = Transaction(encodeStr,str,wallet.publicKey, toPublicStr)
    translate(transaction)
}
//传输 对数据做正确性验证
fun translate(transaction: Transaction){
    val byteArray = Base64.decode(transaction.encodeStr)
    /*---------------------------- 对密文解密 ----------------------------*/
    val cipher = Cipher.getInstance("RSA")
    cipher.init(Cipher.DECRYPT_MODE, transaction.publicKey)

    //分段解密
    //单次最大加密的字节数
    val MAX_DECODE_BYTE = 256
    //偏移
    var offset = 0
    //内存输出流
    val baos = ByteArrayOutputStream()
    var buffer: ByteArray? = null

    while (byteArray.size-offset>0){
        if(byteArray.size-offset>=MAX_DECODE_BYTE){
            buffer = cipher.doFinal(byteArray,offset,MAX_DECODE_BYTE)
            offset += MAX_DECODE_BYTE
        }else{
            buffer = cipher.doFinal(byteArray,offset,byteArray.size-offset)
            offset = byteArray.size
        }
        baos.write(buffer)
    }

    /*---------------------------- 密文解密之后的sha256值 ----------------------------*/
    val str = String(baos.toByteArray())
    /*---------------------------- 明文求sha256值 ----------------------------*/
    val str1 = HashUtil.getSHA256StrJava(transaction.content)

    if(str==str1){
        println("数据正确")
    }else{
        println("数据失败")
    }

}

//传输对象
class Transaction(var encodeStr: String, val content: String, val publicKey: PublicKey, val toPublicStr: String)

//钱包对象
class Wallet(val publicKey: PublicKey, val privateKey: PrivateKey)

/**
 * 求sha256值  并加密
 */
fun hashAndEncode(content: String, privateKey: PrivateKey): String {
    /*---------------------------- sha256 ----------------------------*/
    val shA256Str = HashUtil.getSHA256StrJava(content)

    val byteArray = shA256Str.toByteArray()
    /*---------------------------- 通过私钥加密 ----------------------------*/
    val cipher = Cipher.getInstance("RSA")
    cipher.init(Cipher.ENCRYPT_MODE, privateKey)

    //分段加密
    //单次最大加密的字节数
    val MAX_ENCODE_BYTE = 245
    //偏移
    var offset = 0
    //内存输出流
    val baos = ByteArrayOutputStream()
    var buffer: ByteArray? = null
    while (byteArray.size - offset > 0) {
        if (byteArray.size - offset >= MAX_ENCODE_BYTE) {
            buffer = cipher.doFinal(byteArray, offset, MAX_ENCODE_BYTE)
            offset += MAX_ENCODE_BYTE
        } else {
            buffer = cipher.doFinal(byteArray, offset, byteArray.size - offset)
            offset = byteArray.size
        }
        baos.write(buffer)
    }

    val str = Base64.encode(baos.toByteArray())
    return str
}