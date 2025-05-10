package cn.fusion.framework.annotation;

import java.lang.annotation.*;

/**
 * @ClassName CurrentUser
 * @Description 当前用户注解
 * @Author ycl
 * @Date 2025/1/22 11:03
 * @Version 1.0
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
}
