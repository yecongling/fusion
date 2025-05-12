package cn.fusion.framework.utils;

import java.util.UUID;

/**
 * @ClassName UUIDUtils
 * @Description UUID生成工具类
 * @Author ycl
 * @Date 2024/11/5 11:53
 * @Version 1.0
 */
public class UUIDUtils {

    /**
     * 生成UUID
     *
     * @return 返回UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
