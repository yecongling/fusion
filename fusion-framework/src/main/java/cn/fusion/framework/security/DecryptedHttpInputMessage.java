package cn.fusion.framework.security;

import cn.fusion.framework.constant.Encrypt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

/**
 * @ClassName DecryptedHttpInputMessage
 * @Description 对输入流进行解密操作
 * @Author ycl
 * @Date 2024/11/4 10:32
 * @Version 1.0
 */
public class DecryptedHttpInputMessage implements HttpInputMessage {

    // 请求头
    private final HttpHeaders headers;
    // 请求体
    private final InputStream body;

    /**
     * 解密请求体
     *
     * @param inputMessage 输入流
     * @throws Exception exception
     */
    public DecryptedHttpInputMessage(HttpInputMessage inputMessage) throws Exception {
        this.headers = inputMessage.getHeaders();
        String key = headers.getFirst("X-Encrypted-Key");
        if (StringUtils.isBlank(key)) {
            throw new Exception("加密秘钥缺失！");
        }
        // 解密AES秘钥
        String privateKeyStr = Encrypt.RASPrivateKey;
        PrivateKey privateKey = getPrivateKey(privateKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(key));
        String aesKey = new String(bytes, StandardCharsets.UTF_8);

        // 获取请求体并解密
        String stream = inputStreamToString(inputMessage.getBody());
        String decrypt = decrypt(stream, aesKey);
        this.body = new ByteArrayInputStream(decrypt.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 秘钥构建
     *
     * @param privateKeyStr 私钥
     * @return 私钥
     * @throws Exception exception
     */
    private PrivateKey getPrivateKey(String privateKeyStr) throws Exception {
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyStr
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", ""));
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 解密数据
     *
     * @param data   待解密的数据
     * @param aesKey 秘钥
     * @return 结果
     * @throws Exception exception
     */
    private String decrypt(String data, String aesKey) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(aesKey.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher aesCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        aesCipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(data);
        byte[] decryptedBytes = aesCipher.doFinal(decodedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    /**
     * 输入流转字符串（读取流）
     *
     * @param inputStream 输入流
     * @return 读取输入流的结果
     * @throws IOException exception
     */
    private String inputStreamToString(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        char[] buffer = new char[1024];
        try (Reader reader = new InputStreamReader(inputStream)) {
            int length;
            while ((length = reader.read(buffer)) != -1) {
                sb.append(buffer, 0, length);
            }
        }
        return sb.toString();
    }

    @Override
    public InputStream getBody() throws IOException {
        return body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }
}
