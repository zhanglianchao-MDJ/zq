package com.alibaba.csp.sentinel.dashboard.rule.nacos.config;

/**
 * 配置文件后缀指定类
 */
public class ConfigFilePostFix {
    /**
     * nacos限流规则配置文件名后缀 配置文件名为 appName-flow-rule
     */
    public static final String FLUE_POST_FIX= "-flow-rule";

    /**
     * nacos降级规则配置文件名后缀 配置文件名为 appName-degrade-rule
     */
    public static final String DEGRADE_POST_FIX = "-degrade-rule";

    /**
     * nacos授权规则配置文件名后缀 配置文件名为 appName-authority-rule
     */
    public static final String AUTHORITY_POST_FIX = "-authority-rule";

    /**
     * nacos热点规则配置文件后缀 配置文件名为 appName-param-rule
     */
    public static final String PARAM_POST_FIX = "-param-rule";

    /**
     * nacos系统规则配置文件后缀 配置文件名为 appName-system-rule
     */
    public static final String SYSTEM_POST_FIX = "-system-rule";
}
