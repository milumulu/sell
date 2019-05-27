package com.example.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @program: sell
 * @description: WechatAccountConfig
 * @author: 马建鹏
 * @create: 2019-05-22 10:19
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    private String mpAppId;

    private String mpAppSecret;

    private String openAppId;

    private String openAppSecret;

    private String mchId;

    private String mchKey;

    private String keyPath;

    private Map<String, String> templateId;
}
