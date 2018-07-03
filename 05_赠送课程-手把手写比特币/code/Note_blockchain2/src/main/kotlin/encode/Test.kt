package encode

import com.sun.org.apache.xml.internal.security.utils.Base64
import java.io.ByteArrayOutputStream
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher


/**
 * ClassName:Test
 * Description:
 */
val privateStr = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDERgoVmM/RDqtCOEdLF/6mvQQ6\n" +
        "XVVdV7JOngjIOhhsSrycF7HXvgiU8mH2DSMT8CRMpu5zHpt9Dk93G1Uni5HBNbsaB/2jhWVct8di\n" +
        "cZnw38SCeqbeDrGYkxzbsMXelgxeu13iRTk07Q/0AxV3tSkIQncooYDCr240kHpsExFTJks89OyR\n" +
        "dXQ1FPwbc05IYJzcTQ2tip3lSvdVZWpr7Ng+Fk4MdfllmwRO0RiiTDKugoMYAM0wFPWLgk0JGhZ2\n" +
        "TcuHBpO0qAQB1HpgHuVERqsgQql255pwh79iw8pGTGgg2GTfJ1beNV54in5DfFjMEmJ9VJDiJG98\n" +
        "XXT77GW7mH1RAgMBAAECggEAVy4dEJa9yEhRvaot0Kcpz3opxJrsbfekFdOQZw+XBZL00AieUAvy\n" +
        "1+YJqTcQm6yRExMa2zt/KT8QAQG9A0G1TjLFlrxx5w47hcGD6LA5Bor+SE6ejAfOzsyZhuOwIDxZ\n" +
        "5kInY3gH/wBFk8dC49cuEKMlNoD2JoOx2dtK5XJawal7meUoMs4FBLILSETYCN5P8zTTRw7iMiLg\n" +
        "iuECnRw4IismsD6FiSjR8JgqL2MQyzNRpag52g5Ih+VZfk3b4Zpyqb/mhHFC1pGhXJf0H1jGCv8K\n" +
        "6h6yZ2VoiGdWlSNGcVwOQgiIAoVnWHIcAl8DCVKDKkv08w3e7GuppOoXoCEPGQKBgQDkooygYYg6\n" +
        "VetgJf2yNuJWnj8pPzI5GHGlYrVr8i+i0XkZOaXbeNZ5ORyqnv3onq+uDR9a3EktuiihR5TbQcaQ\n" +
        "4uD+N9ZiRfu6zQsSh0iiwL3WutqO9yzps+j4yXwFRl7HL0Xz93VxqMB8Y0j3ysl5Lw0jksrt4us2\n" +
        "FDNWA7ZDFwKBgQDbw+1V+/xsji0KBk7cDsOVUwfNbSXZ4KlitkHpj9Kn2fNCVjSnii5qiKB6Qift\n" +
        "YsmOPYlZrvSvpk/3q7do1Zm2Uk2B5Bmpr2zTgBIBWpO3QdZqtMpTwwRV1YztjfbJEmtoaSr53xYX\n" +
        "kg7zOGpZGthFRMn5o63X1UJsETdlALYj1wKBgQCLCw/auXXPhFEQuAoBR79+Td67huNUNblnixAq\n" +
        "Ba9SpXGwFY74WhSUxFbarLZdsaPFhdV7vFwEoyHyc0Xj0aygkFmkQPDv8v+UKv6A1iUmVXEeFaS1\n" +
        "LTzzI6abJm12B8W7/NPd+rBB3w6wlvDZQUolmKe06qDe6xGAYeiWdstxIQKBgQCZoM+rCiqd4I4P\n" +
        "sfUy1IdZPd/4dVK+itUeknZlRgtnF298cjMA5BIqmzREAzdSNXrdGpuHvEyE9eh1JO9kM93unH5e\n" +
        "TQy7mEylVaRAmgclVrsDgTrnby76qfyInHNmgJ/hL5PBdXhGJe6ZjFibza5eTgY7o3aFqSlzzl1B\n" +
        "GEw7pQKBgE2ab9pknuhiDRLvvhYxPM06D+Bi7MT6Xjqj69+ullmYFWsuoiBZ0zXUIypZbfl9Z2cP\n" +
        "OB3/717bLtgviBS6zTFkzBf9a/i9e/ZP3X5oMExmwhHHvxuN+9803dB/x/WPJnNJC0MTD38AMoK5\n" +
        "/nxgN8WSMQtOOgWGGT8vvvRmVDl5"

val publicStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxEYKFZjP0Q6rQjhHSxf+pr0EOl1VXVey\n" +
        "Tp4IyDoYbEq8nBex174IlPJh9g0jE/AkTKbucx6bfQ5PdxtVJ4uRwTW7Ggf9o4VlXLfHYnGZ8N/E\n" +
        "gnqm3g6xmJMc27DF3pYMXrtd4kU5NO0P9AMVd7UpCEJ3KKGAwq9uNJB6bBMRUyZLPPTskXV0NRT8\n" +
        "G3NOSGCc3E0NrYqd5Ur3VWVqa+zYPhZODHX5ZZsETtEYokwyroKDGADNMBT1i4JNCRoWdk3LhwaT\n" +
        "tKgEAdR6YB7lREarIEKpdueacIe/YsPKRkxoINhk3ydW3jVeeIp+Q3xYzBJifVSQ4iRvfF10++xl\n" +
        "u5h9UQIDAQAB"

var publicKey: PublicKey? = null
var privateKey: PrivateKey? = null
val factory = KeyFactory.getInstance("RSA")
fun main(args: Array<String>) {
    val str = "你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好"
    /*---------------------------- 生成公钥和秘钥 ----------------------------*/
//    val generator = KeyPairGenerator.getInstance("RSA")
//    val keyPair = generator.genKeyPair()
//    val publicKey = keyPair.public
//    val privateKey = keyPair.private
//
//    println("publicKey="+Base64.encode(publicKey.encoded))
//    println("privateKey="+Base64.encode(privateKey.encoded))

    /*---------------------------- 生成公钥和秘钥对象 ----------------------------*/
    publicKey = factory.generatePublic(X509EncodedKeySpec(Base64.decode(publicStr.toByteArray())))
    privateKey = factory.generatePrivate(PKCS8EncodedKeySpec(Base64.decode(privateStr.toByteArray())))


    /*---------------------------- 私钥加密 公钥解密 ----------------------------*/
    val encodeStr = encodeByPrivateKey(str, privateKey!!)
    val decodeStr = decodeByPublicKey(encodeStr, publicKey!!)
    println("encodeStr=$encodeStr")
    println("decodeStr=$decodeStr")
    /*---------------------------- 公钥加密 私钥解密 ----------------------------*/
//    val encodeStr = encodeByPublicKey(str, publicKey!!)
//    val decodeStr = decodeByPrivateKey(encodeStr, privateKey!!)
//    println(decodeStr)
}

/**
 * 通过私钥加密
 */
fun encodeByPrivateKey(content: String, privateKey: PrivateKey): String {
    //加密字节数组
    val byteArray = content.toByteArray()

    val cipher = Cipher.getInstance("RSA")
    cipher.init(Cipher.ENCRYPT_MODE, privateKey)

    //单次最大加密的字节数
    val MAX_ENCODE_BYTE = 245
    //偏移
    var offset = 0
    //内存输出流
    val baos = ByteArrayOutputStream()

    //分段加密的操作
    while (byteArray.size - offset > 0) {
        if (byteArray.size - offset >= MAX_ENCODE_BYTE) {
            //前面普通段
            val arr = cipher.doFinal(byteArray, offset, MAX_ENCODE_BYTE)
            //读取到内存输出流中
            baos.write(arr)
            //移动offset
            offset += MAX_ENCODE_BYTE
        } else {
            //最后一段
            val arr = cipher.doFinal(byteArray, offset, byteArray.size - offset)
            //读取到内存输出流中
            baos.write(arr)
            //移动offset
            offset = byteArray.size
        }
    }

    val resultStr = Base64.encode(baos.toByteArray())
    return resultStr
}

/**
 * 通过公钥解密
 */
fun decodeByPublicKey(content: String, publicKey: PublicKey): String {
    //解密的字节数组
    val byteArray = Base64.decode(content.toByteArray())


    val cipher = Cipher.getInstance("RSA")
    cipher.init(Cipher.DECRYPT_MODE, publicKey)

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

/**
 * 通过公钥加密
 */
fun encodeByPublicKey(content: String, publicKey: PublicKey): String {
    val cipher = Cipher.getInstance("RSA")
    cipher.init(Cipher.ENCRYPT_MODE, publicKey)
    val result = cipher.doFinal(content.toByteArray())
    val resultStr = Base64.encode(result)
    return resultStr
}

/**
 * 通过私钥解密
 */
fun decodeByPrivateKey(content: String, privateKey: PrivateKey): String {
    val cipher = Cipher.getInstance("RSA")
    cipher.init(Cipher.DECRYPT_MODE, privateKey)
    val result = cipher.doFinal(Base64.decode(content.toByteArray()))
    val resultStr = String(result)
    return resultStr
}