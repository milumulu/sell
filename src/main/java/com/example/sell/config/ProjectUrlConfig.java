package com.example.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: sell
 * @description: 项目中用到的url
 * @author: 马建鹏
 * @create: 2019-05-27 15:36
 */
@Data
@ConfigurationProperties(prefix = "project-url")
@Component
public class ProjectUrlConfig {

    /**
     * 微信公众平台授权Url
     */
    public String wechatMpAuthorize;

    /**
     * 微信开放平台授权Url
     */
    public String wecharOpenAuthorize;

    public String sell;
}
