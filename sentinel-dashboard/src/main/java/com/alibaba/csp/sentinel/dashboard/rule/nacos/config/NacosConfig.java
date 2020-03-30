package com.alibaba.csp.sentinel.dashboard.rule.nacos.config;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.*;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Configuration
public class NacosConfig {

    @Autowired
    private NacosConfigProperties nacosConfigProperties;
    /**
      * 非常关键 这里将FlowRuleEntity转换成FlowRule才会对客户端生效
     * @return FlowRule
     */
     @Bean
     public Converter<List<FlowRuleEntity>, String> flowRuleEntityEncoder() {
         return rules -> JSON.toJSONString(rules.stream().map(FlowRuleEntity::toRule).collect(Collectors.toList()), true);
     }
     @Bean
     public Converter<String, List<FlowRuleEntity>> flowRuleEntityDecoder() {
         return s -> JSON.parseArray(s, FlowRuleEntity.class);
     }

    /**
     * 配置degrade converter 转换格式以使客户端生效
     * @return
     */
    @Bean
    public Converter<List<DegradeRuleEntity>, String> degradeRuleEntityEncoder() {
        return rules -> JSON.toJSONString(rules.stream().map(DegradeRuleEntity::toRule).collect(Collectors.toList()), true);
    }
    @Bean
    public Converter<String, List<DegradeRuleEntity>> degradeRuleEntityDecoder() {
        return s -> JSON.parseArray(s, DegradeRuleEntity.class);
    }

    /**
     * 配置authority converter 转换格式以使客户端生效
     * @return
     */
    @Bean
    public Converter<List<AuthorityRuleEntity>, String> authorityRuleEntityEncoder() {
        return rules -> JSON.toJSONString(rules.stream().map(AuthorityRuleEntity::toRule).collect(Collectors.toList()), true);
    }
    @Bean
    public Converter<String, List<AuthorityRuleEntity>> authorityRuleEntityDecoder() {
        return s -> JSON.parseArray(s, AuthorityRuleEntity.class);
    }
    /**
     * 配置param converter 转换格式以使客户端生效
     * @return
     */
    @Bean
    public Converter<List<ParamFlowRuleEntity>, String> paramFlowRuleEntityEncoder() {
        return rules -> JSON.toJSONString(rules.stream().map(ParamFlowRuleEntity::toRule).collect(Collectors.toList()), true);
    }
    @Bean
    public Converter<String, List<ParamFlowRule>> paramFlowRuleEntityDecoder() {
        return s -> JSON.parseArray(s, ParamFlowRule.class);
    }
    /**
     * 配置system converter 转换格式以使客户端生效
     * @return
     */
    @Bean
    public Converter<List<SystemRuleEntity>, String> systemRuleEntityEncoder() {
        return rules -> JSON.toJSONString(rules.stream().map(SystemRuleEntity::toRule).collect(Collectors.toList()), true);
    }
    @Bean
    public Converter<String, List<SystemRuleEntity>> systemRuleEntityDecoder() {
        return s -> JSON.parseArray(s, SystemRuleEntity.class);
    }

     @Bean
     public ConfigService nacosConfigService() throws Exception {
         Properties properties = new Properties();
         properties.put(PropertyKeyConst.SERVER_ADDR, nacosConfigProperties.getServerAddr());
         properties.put(PropertyKeyConst.NAMESPACE, nacosConfigProperties.getNamespace());
         return ConfigFactory.createConfigService(properties);
     }
}
