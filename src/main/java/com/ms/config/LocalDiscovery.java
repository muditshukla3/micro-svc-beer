package com.ms.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("local")
@EnableDiscoveryClient
@Configuration
public class LocalDiscovery {
}
