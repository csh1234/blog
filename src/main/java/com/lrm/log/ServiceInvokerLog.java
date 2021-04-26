package com.lrm.log;

import com.alibaba.fastjson.JSON;
import com.lrm.anation.ServiceInvoker;
import com.lrm.bean.ServiceLog;
import com.lrm.util.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname ServiceInvokerLog
 * @Description TODO
 * @Date 2021/4/26 22:17
 * @Created by Administrator
 */
@Component
@Slf4j
@Aspect
public class ServiceInvokerLog {
    /**
     * 环绕增强，相当于MethodInterceptor
     */
    @Around(value = "@annotation(com.lrm.anation.ServiceInvoker)")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        long time = System.currentTimeMillis();
        try {
            result = joinPoint.proceed();
            time = System.currentTimeMillis() - time;
            return result;
        } finally {
            try {
                //方法执行完成后增加日志
                addOperationLog(joinPoint, result, time);
            } catch (Exception e) {
                throw e;
            }
        }
    }

    private void addOperationLog(JoinPoint joinPoint, Object res, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        ServiceLog operationLog = new ServiceLog();
        operationLog.setRunTime(time);
        operationLog.setResponse(JSON.toJSONString(res));
        operationLog.setParams(JSON.toJSONString(joinPoint.getArgs()));
        operationLog.setCreateTime(DateTimeUtils.dateToString(new Date()));
        operationLog.setMethod(signature.getDeclaringTypeName() + "." + signature.getName());
        ServiceInvoker annotation = signature.getMethod().getAnnotation(ServiceInvoker.class);
        if (annotation != null) {
            operationLog.setDescription(getDetail(((MethodSignature) joinPoint.getSignature()).getParameterNames(), joinPoint.getArgs(), annotation));
        }
        //TODO 这里保存日志
        log.warn("【" + operationLog + "】");
    }

    /**
     * 对当前登录用户和占位符处理
     *
     * @param argNames   方法参数名称数组
     * @param args       方法参数数组
     * @param annotation 注解信息
     * @return 返回处理后的描述
     */
    private String getDetail(String[] argNames, Object[] args, ServiceInvoker annotation) {

        Map<Object, Object> map = new HashMap<>(4);
        for (int i = 0; i < argNames.length; i++) {
            map.put(argNames[i], args[i]);
        }

        String detail = annotation.service();
        try {
            detail = "'" + "MethodName" + "'===>" + annotation.service();
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                Object k = entry.getKey();
                Object v = entry.getValue();
                detail = detail.replace("{{" + k + "}}", JSON.toJSONString(v));
            }
        } catch (Exception e) {
            throw e;
        }
        return detail;
    }
}
