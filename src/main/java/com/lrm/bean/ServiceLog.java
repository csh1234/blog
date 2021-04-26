package com.lrm.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServiceLog implements Serializable {
    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 方法名
     */
    private String method;
    /**
     * 参数
     */
    private String params;

    /**
     * 日志描述
     */
    private String description;

    /**
     * 方法运行时间
     */
    private Long runTime;

    /**
     * 方法返回值
     */
    private String response;
}
