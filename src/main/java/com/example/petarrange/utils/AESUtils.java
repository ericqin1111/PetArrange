package com.example.petarrange.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class AESUtils {

    private static final String KEY = "85ffeb27076249ca81c86f8da2fc4966";
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    private AESUtils() {}

    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey());
            byte[] result = cipher.doFinal(byteContent);// 使用Java原生Base64进行编码
            return Base64.getEncoder().encodeToString(result);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * AES 解密操作
     *
     * @param content 待解密内容
     * @return 解密内容
     */
    public static String decrypt(String content) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey());
            // 使用Java原生Base64进行解码
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(content));
            return new String(result, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 生成加密秘钥
     *
     * @return 加密秘钥
     */
    private static SecretKeySpec getSecretKey() {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(KEY.getBytes());
            kg.init(random);
            SecretKey secretKey = kg.generateKey();
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (Exception e) {
            return null;
        }
    }
}
