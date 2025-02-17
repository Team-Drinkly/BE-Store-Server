package com.drinkhere.drinklystore.clientgeocoding.webclient.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Getter @Setter
@Configuration
@ConfigurationProperties(prefix = "naver-cloud")
public class GeocodingProperties {
    private String clientId;
    private String clientSecret;
}