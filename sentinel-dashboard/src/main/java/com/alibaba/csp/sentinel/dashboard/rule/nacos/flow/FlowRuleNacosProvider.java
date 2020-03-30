package com.alibaba.csp.sentinel.dashboard.rule.nacos.flow;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.config.ConfigFilePostFix;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.config.NacosConfigProperties;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.nacos.api.config.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("flowRuleNacosProvider")
public class FlowRuleNacosProvider implements DynamicRuleProvider<List<FlowRuleEntity>> {

    private static Logger logger = LoggerFactory.getLogger(FlowRuleNacosProvider.class);

    @Autowired
    private NacosConfigProperties nacosConfigProperties;
    @Autowired
    private ConfigService configService;
    @Autowired
    private Converter<String, List<FlowRuleEntity>> converter;

    @Override
    public List<FlowRuleEntity> getRules(String appName) throws Exception {
        //获取规则的配置文件并设置超时时间
        String rules = configService.getConfig(appName+ ConfigFilePostFix.FLUE_POST_FIX, nacosConfigProperties.getGroupId(), 3000);
        logger.info("从Nacos中拉取到限流规则信息:{}",rules);
        if(StringUtil.isEmpty(rules)){
            return new ArrayList<>();
        }
        return converter.convert(rules);
    }
}
