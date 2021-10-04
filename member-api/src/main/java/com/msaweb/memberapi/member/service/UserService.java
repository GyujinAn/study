package com.msaweb.memberapi.member.service;


import com.msaweb.memberapi.member.model.entity.User;

/**
 * @author agj017@gmail.com
 * @since 2021/10/04
 */
public interface UserService {
    User get(Long id);

    Long save(User user);

    Long update(User user);

    void delete(Long id);
}
