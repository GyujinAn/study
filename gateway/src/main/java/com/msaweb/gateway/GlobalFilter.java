package com.msaweb.gateway;

import com.msaweb.gateway.data.HttpMessageEntity;
import com.msaweb.gateway.data.HttpMessageRepo;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.HttpComponentsClientHttpConnector;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * @author agj017@gmail.com
 * @since 2021/10/02
 */
@Component
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {


    private final HttpMessageRepo httpMessageRepo;

    @Autowired
    public GlobalFilter(HttpMessageRepo httpMessageRepo) {
        super(Config.class);
        this.httpMessageRepo = httpMessageRepo;
    }

    https://github.com/GyujinAn/study.git

    @Override
    public GatewayFilter apply(Config config) {


        log.info("webclient started");
        HttpAsyncClientBuilder clientBuilder = HttpAsyncClients.custom();
        clientBuilder.setDefaultRequestConfig(...);
        CloseableHttpAsyncClient client = clientBuilder.build();
        ClientHttpConnector connector = new HttpComponentsClientHttpConnector(client);

        WebClient webClient = WebClient.builder().clientConnector(connector).build();

        Mono<String> stringMono = webClient.get()
                .uri("localhost:8080/member/checkLogin")
                .retrieve()
                .bodyToMono(String.class);

        log.info(stringMono.toString());


        log.info("webclient ended");


        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            HttpMessageEntity httpMessageEntity = new HttpMessageEntity();
            httpMessageEntity.setHost(request.getHeaders().getHost().toString());
            httpMessageEntity.setDivision("default");
            httpMessageEntity.setEndPoint(request.getPath().toString());
            httpMessageEntity.setMethod(request.getMethod().toString());
            httpMessageEntity.setContentType(request.getHeaders().getContentType().toString());
            httpMessageEntity.setRemoteIpAddr(request.getRemoteAddress().toString());
            httpMessageEntity.setUser("default");
            httpMessageEntity.setDate(LocalDateTime.now());

            config.setHttpMessageEntity(httpMessageEntity);

            log.info("here is pre filter");
            return chain.filter(exchange).then(Mono.fromRunnable(() ->{
                log.info("here is post filter");

                ServerHttpResponse response = exchange.getResponse();
                HttpMessageEntity http = config.getHttpMessageEntity();
                log.info("okestro-http: "+ http);
                http.setStatus(response.getStatusCode().toString());
                httpMessageRepo.save(http);

            }));
        });
    }

    @Setter
    @Getter
    public static class Config{

        HttpMessageEntity httpMessageEntity;

    }
}
