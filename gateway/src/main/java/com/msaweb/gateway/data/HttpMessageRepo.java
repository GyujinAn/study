package com.msaweb.gateway.data;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author agj017@gmail.com
 * @since 2021/11/01
 */

public interface HttpMessageRepo extends JpaRepository<HttpMessageEntity, Long> {
}
