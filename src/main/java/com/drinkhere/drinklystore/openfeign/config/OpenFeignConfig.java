package com.drinkhere.drinklystore.openfeign.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.drinkhere.drinklystore")
public class OpenFeignConfig {
}
