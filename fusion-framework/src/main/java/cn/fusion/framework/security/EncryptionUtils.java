package cn.fusion.framework.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @ClassName EncryptionUtils
 * @Description 加解密数据库连接信息的工具类
 * @Author ycl
 * @Date 2024/12/16 09:44
 * @Version 1.0
 */
public class EncryptionUtils {
    private static final String ALGORITHM = "AES";

    // 加密
    public static String encrypt(String content, String key) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(Base64.getDecoder().decode(key.getBytes(StandardCharsets.UTF_8)), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] result = cipher.doFinal(content.getBytes());
        return Base64.getEncoder().encodeToString(result);
    }

    // 解密
    public static String decrypt(String encryptedData, String key) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(Base64.getDecoder().decode(key.getBytes(StandardCharsets.UTF_8)), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] result = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(result);
    }

    public static void main(String[] args) throws Exception {
        String key = System.getenv("FUSION_SECRET_KEY");
        System.out.println("key:" + key);
        String encrypt = EncryptionUtils.encrypt("jdbc:postgresql://localhost:5432/fusion", key);
        System.out.println("url:" + encrypt);
        String username = EncryptionUtils.encrypt("postgres", key);
        System.out.println("username:" + username);
    }
}
