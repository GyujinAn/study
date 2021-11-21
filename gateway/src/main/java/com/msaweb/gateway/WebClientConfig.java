package com.msaweb.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.HttpComponentsClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author agj017@gmail.com
 * @since 2021/11/21
 */

@Configuration
@Slf4j
public class WebClientConfig {

//    @Bean
//    public WebClient webClient() {
//
//        ClientHttpConnector connector = new HttpComponentsClientHttpConnector();
//        WebClient webClient = WebClient.builder().clientConnector(connector).build();
//
//
//
//    }

}
