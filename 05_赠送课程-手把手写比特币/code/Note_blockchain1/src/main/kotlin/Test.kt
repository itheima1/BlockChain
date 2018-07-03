
import com.sun.org.apache.xml.internal.security.utils.Base64
import java.security.KeyFactory
import java.security.spec.X509EncodedKeySpec

/**
 * ClassName:Test
 * Description:
 */
fun main(args: Array<String>) {
    verify()
}
fun verify(){
    var publicStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAn0nlLcOd1d+45w+C7sV9mchZDkpI2S0x\n" +
            "fh0uQbp1ug7JSIG0D55NXJ7bFNno0NTNrzOFW4bDx6UvXS3UDfo+Iw/DJ+Mgg7ROk0VZ7OLADl/l\n" +
            "l2deu6Sp//ElHprcpxBX+dFKaf1wuhyIPFMB727MxkENbClWXOzVs36i+YiEp8QdGQYBoZ9I6yAo\n" +
            "hzUbKZzYOt63gTtqrmK58BuLLpcK364VlwUhSrUheLvQGbUL36ejXBJ0P4oLgSqqTrKQ2+ujZ6Ki\n" +
            "/HhBpj/ks2sdtmbVRJmJ0O7j2FJdy+oem2JXI8Pql3Si+FPil8nTD1G4EANdKvAgsULnRAcO9K+w\n" +
            "95QIwwIDAQAB"
    var sign = "543f1a161f55bb798d4bc8f2abcb9e163af6356824b1d9a91bf84012307a91dd006af05152bd351c79f7c4c6ec0d476886d4194624599fd9d1ea71d58e649e5fe77b697c18c2e399825c490d1b8874bb33a8b54af43da311d5d7cd84459028a714dead74595f6098841a747f67f43204ef33e4bd9c54eb301cc018d50c854ef4bfd1ade37b3ca2cbbbf0bab2e008ed3b13411058554d3f12a88fbd4461bd4b57d5efe98deb9f5076ef15da71b8dea100ab31abf8b81c84a9102246f8d876c619b19c137e880bc1ee77113e23ee627c5290f0e5a952f3b719c0a06682f76ca692affb5ff46bc2377236f3c5d39af04c42f95283adae58a889ff7f941260e4b334"
    val keyf = KeyFactory.getInstance("RSA")
    val publicKey = keyf.generatePublic(X509EncodedKeySpec(Base64.decode(publicStr)))
    val signet = java.security.Signature.getInstance("SHA256withRSA")
    signet.initVerify(publicKey)
    signet.update("张三给王五转账5毛钱".toByteArray())
    val result =  signet.verify(toBytes(sign))
    println(result)//true  正确的
}
fun toBytes(str: String?): ByteArray {
    if (str == null || str.trim { it <= ' ' } == "") {
        return ByteArray(0)
    }

    val bytes = ByteArray(str.length / 2)
    for (i in 0 until str.length / 2) {
        val subStr = str.substring(i * 2, i * 2 + 2)
        bytes[i] = Integer.parseInt(subStr, 16).toByte()
    }
    return bytes
}


