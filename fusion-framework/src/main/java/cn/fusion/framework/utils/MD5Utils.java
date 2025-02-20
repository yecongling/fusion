package cn.fusion.framework.utils;

import java.security.MessageDigest;

/**
 * @ClassName MD5Utils
 * @Description MD5加密工具
 * @Author ycl
 * @Date 2024/11/5 11:07
 * @Version 1.0
 */
public class MD5Utils {

    private static final String[] hexDigits = {
            "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    //这里主要是遍历8个byte，转化为16位进制的字符，即0-F
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte value : b) resultSb.append(byteToHexString(value));

        return resultSb.toString();
    }

    // 这里是针对单个byte，256的byte通过16拆分为d1和d2
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 返回大写MD5
     */
    public static String MD5Encode(String origin, String charset) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charset == null || charset.isEmpty())
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charset)));
        } catch (Exception ignored) {
        }
        return resultString.toUpperCase();
    }
}
