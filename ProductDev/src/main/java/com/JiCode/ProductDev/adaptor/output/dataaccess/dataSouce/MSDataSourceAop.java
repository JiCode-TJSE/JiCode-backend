package com.JiCode.ProductDev.adaptor.output.dataaccess.dataSouce;

/**
 * 采用切面编程 AOP ，使用了代理模式，在对标记进行操作实行了代理
 */

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

// 切面标识
@Aspect
// 标记 spring 的组件，相当于函数的 bean
@Component
@Order(Ordered.HIGHEST_PRECEDENCE) // 在事务开始之前执行
public class MSDataSourceAop {

    // 给这个类关联上 log 对象，输出的 log 会带上这个类的信息，好辨识
    static final Logger log = LoggerFactory.getLogger(MSDataSourceAop.class);

    // 切点定义，下面的方法是提供一个切点的引用
    @Pointcut(value = "@annotation(org.springframework.transaction.annotation.Transactional)")
    public void txMethod() {
    }

    // 引用切点
    @Around("txMethod()")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {

        // 获取当前请求的主从标识
        try {

            // 获取事务方法上的注解
            Transactional transactional = ((MethodSignature) joinPoint.getSignature()).getMethod()
                    .getAnnotation(Transactional.class);

            if (transactional != null && transactional.readOnly()) {
                log.info("标记为从库");
                MSDataSourceMarker.slave(); // 只读，从库
            } else {
                log.info("标记为主库");
                MSDataSourceMarker.master(); // 可写，主库
            }

            // 继续执行业务方法
            Object ret = joinPoint.proceed();

            return ret;

        } catch (Throwable e) {
            throw e;
        } finally {
            MSDataSourceMarker.clean();
        }
    }
}