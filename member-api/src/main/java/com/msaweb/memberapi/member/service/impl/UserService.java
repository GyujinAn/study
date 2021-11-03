package com.msaweb.memberapi.member.service.impl;

import com.msaweb.memberapi.member.model.entity.Admin;
import com.msaweb.memberapi.member.model.entity.User;
import com.msaweb.memberapi.member.repository.UserRepository;
import com.msaweb.memberapi.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author agj017@gmail.com
 * @since 2021/10/04
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements MemberService<User> {


    private final UserRepository userRepository;

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public Long save(User user) {

        User save = userRepository.save(user);
        return save.getId();
    }

    @Override
    public Long update(User user) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
