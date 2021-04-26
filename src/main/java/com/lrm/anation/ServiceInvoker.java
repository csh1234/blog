package com.lrm.anation;

import java.lang.annotation.*;

/**
 * @Classname ServiceInvoker
 * @Description TODO
 * @Date 2021/4/26 22:15
 * @Created by Administrator
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceInvoker {
    /**
     * 方法描述,可使用占位符获取参数:{{tel}}
     */
    String service() default "";
}
