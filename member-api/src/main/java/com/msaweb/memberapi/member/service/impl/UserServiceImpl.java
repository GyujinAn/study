package com.msaweb.memberapi.member.service.impl;

import com.msaweb.memberapi.member.model.entity.User;
import com.msaweb.memberapi.member.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author agj017@gmail.com
 * @since 2021/10/04
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public Long save(User user) {
        return null;
    }

    @Override
    public Long update(User user) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
