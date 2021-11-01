package com.msaweb.gateway;

import com.msaweb.gateway.data.HttpMessageEntity;
import com.msaweb.gateway.data.HttpMessageRepo;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
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

    @Override
    public GatewayFilter apply(Config config) {


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
