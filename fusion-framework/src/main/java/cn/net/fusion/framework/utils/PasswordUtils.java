package cn.net.fusion.framework.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @ClassName PasswordUtils
 * @Description 密码工具类
 * @Author ycl
 * @Date 2024/11/4 10:18
 * @Version 1.0
 */
public class PasswordUtils {
    /**
     * 定义使用的算法为:PBEWITHMD5andDES算法
     * 加密算法
     */
    public static final String ALGORITHM = "PBEWithMD5AndDES";

    /**
     * 定义迭代次数为1000次
     */
    private static final int ITERATION_COUNT = 1000;

    /**
     * 加密明文字符串
     *
     * @param plaintext 待加密的明文字符串
     * @param password  生成密钥时所使用的密码
     * @param salt      盐值(通过base64转换过的)
     * @return 加密后的密文字符串
     */
    public static String encrypt(String plaintext, String password, String salt) throws Exception {
        Key key = getPbeKey(password);
        byte[] encipheredData;
        PBEParameterSpec parameterSpec = new PBEParameterSpec(Base64.getDecoder().decode(salt), ITERATION_COUNT);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
        encipheredData = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return bytesToHexString(encipheredData);
    }

    /**
     * 根据PBE密码生成一把密钥
     *
     * @param password 生成密钥时所使用的密码
     * @return Key PBE算法密钥
     */
    private static Key getPbeKey(String password) throws Exception {
        // 实例化使用的算法
        SecretKeyFactory keyFactory;
        SecretKey secretKey = null;
        keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        // 设置PBE密钥参数
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        // 生成密钥
        secretKey = keyFactory.generateSecret(keySpec);
        return secretKey;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param src 字节数组
     * @return 16进制字符串
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length == 0) {
            return null;
        }
        for (byte b : src) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 生成随机密码盐（每次请求不一样）
     * @return 返回密码盐
     */
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[8]; // 通常盐长度为8字节
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt); // 返回 Base64 编码的盐
    }
}
