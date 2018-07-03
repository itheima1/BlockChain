
import com.sun.org.apache.xml.internal.security.utils.Base64
import java.io.ByteArrayOutputStream
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.spec.PKCS8EncodedKeySpec
import javax.crypto.Cipher

/**
 * ClassName:DecryptJsResult
 * Description:
 */
val content = "ddMxg/JTENmTlSt6L9hWIMGoZYlfwivY2l80apvgER7N3HDjeUkUvkc9kjohjEH48XDIr3k+pYgnIRpSTR3k33Pz7XaMwyVHWEGUDG1OQ/NRabZ9SXYHW9Yniqvf0lnejYMP12KC47m8RUFnCGCvIwQaPCaVcOOYWQ67wUMYg2VtbJqUDAoJP6gQOgWdSzzy5I1fBcf+Vf9SwqL5gb2I10BxomHeORCME+zKgnaUukN0XV3LwLF3LNly6QJfruioGC8irhvS0BRvxzC5WumoqW05R+i9+/oqVVJIU0dooz/vrWa4Nt4Em0KBmE86zIwtkolT2uphKyC2wBHMKZ0LBw=="

fun main(args: Array<String>) {
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

    val factory = KeyFactory.getInstance("RSA")
    val privateKey = factory.generatePrivate(PKCS8EncodedKeySpec(Base64.decode(translate.privateStr.toByteArray())))
    val decodeResult = decrypt(content,privateKey)
    println(decodeResult)
}

fun decrypt(content:String,privateKey: PrivateKey):String{
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