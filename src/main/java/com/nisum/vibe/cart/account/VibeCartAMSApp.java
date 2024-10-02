package com.nisum.vibe.cart.account;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class VibeCartAMSApp {
    public static void main(String[] args) {
        SpringApplication.run(VibeCartAMSApp.class, args);
    }
}