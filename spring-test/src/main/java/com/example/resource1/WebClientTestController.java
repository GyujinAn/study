package com.example.resource1;

import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author agj017@gmail.com
 * @since 2021/11/21
 */

@RestController
@RequestMapping("/resource1")
public class WebClientTestController {


    @GetMapping
    public String test() {

        HttpAsyncClientBuilder clientBuilder = HttpAsyncClients.custom();
        clientBuilder.setDefaultRequestConfig(...);
        CloseableHttpAsyncClient client = clientBuilder.build();
        ClientHttpConnector connector = new HttpComponentsClientHttpConnector(client);
        return "helloworld";
    }
}
