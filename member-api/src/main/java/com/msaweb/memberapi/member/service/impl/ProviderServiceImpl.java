package com.msaweb.memberapi.member.service.impl;

import com.msaweb.memberapi.member.model.entity.Provider;
import com.msaweb.memberapi.member.service.ProviderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author agj017@gmail.com
 * @since 2021/10/04
 */
@Service
@Transactional
public class ProviderServiceImpl implements ProviderService {
    @Override
    public Provider get(Long id) {
        return null;
    }

    @Override
    public Long save(Provider provider) {
        return null;
    }

    @Override
    public Long update(Provider provider) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
