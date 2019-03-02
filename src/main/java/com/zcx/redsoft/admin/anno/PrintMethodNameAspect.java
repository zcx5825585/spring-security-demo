package com.zcx.redsoft.admin.anno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 类说明
 *
 * @author zcx
 * @version 创建时间：2019/1/3  13:53
 */
@Aspect
@Component
public class PrintMethodNameAspect {
    @Around(value = "@annotation(com.zcx.redsoft.admin.anno.PrintMethodName)")
    public String print(ProceedingJoinPoint pjp) {
        try {
            Signature signature = pjp.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            PrintMethodName ann = method.getAnnotation(PrintMethodName.class);
            System.out.println("annotation value:" + ann.value());
            System.out.println("method name:" + method.getName());
            return pjp.proceed().toString();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return throwable.getMessage();
        }
    }
}
