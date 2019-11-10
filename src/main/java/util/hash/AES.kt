package util.hash

import java.io.UnsupportedEncodingException
import java.util.Base64

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object AES {

    private var secretKey: SecretKeySpec? = null
    private val key: ByteArray? = null

    fun setKey(myKey: String) {
        try {
            secretKey = SecretKeySpec(myKey.toByteArray(charset("UTF-8")), "AES")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

    }

    fun encrypt(strToEncrypt: String, secret: String): String? {
        try {
            setKey(secret)
            val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, secretKey)
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.toByteArray(charset("UTF-8"))))
        } catch (e: Exception) {
            println("Error while encrypting: $e")
        }

        return null
    }

    fun decrypt(strToDecrypt: String, secret: String): String? {
        try {
            setKey(secret)
            val cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING")
            cipher.init(Cipher.DECRYPT_MODE, secretKey)
            return String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)))
        } catch (e: Exception) {
            println("Error while decrypting: $e")
        }

        return null
    }
}