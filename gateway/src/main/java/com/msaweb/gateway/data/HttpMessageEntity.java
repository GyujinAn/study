package com.msaweb.gateway.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @author agj017@gmail.com
 * @since 2021/11/01
 */


@Entity
@Setter
@Getter
public class HttpMessageEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String host;

    private String division;

    private String endPoint;

    private String method;

    private String contentType;

    private String status;

    private String remoteIpAddr;

    private String user;

    private String performance;

    private LocalDateTime date;
}
