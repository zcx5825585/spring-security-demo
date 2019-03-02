package com.zcx.redsoft.admin.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类说明
 *
 * @author zcx
 * @version 创建时间：2019/1/3  13:50
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PrintMethodName {
    String value() default "zcx";
}
