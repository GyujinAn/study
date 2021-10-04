package com.msaweb.memberapi.member.service;

import com.msaweb.memberapi.member.model.entity.Admin;
import com.msaweb.memberapi.member.model.entity.Provider;

/**
 * @author agj017@gmail.com
 * @since 2021/10/04
 */
public interface ProviderService {
    Provider get(Long id);

    Long save(Provider provider);

    Long update(Provider provider);

    void delete(Long id);
}
