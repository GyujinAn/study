package com.msaweb.memberapi.member.service.impl;

import com.msaweb.memberapi.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author agj017@gmail.com
 * @since 2021/10/04
 */
@Service
public class MemberServiceImpl<T> implements MemberService<T> {


    @Override
    public T get(Long id) {
        return null;
    }

    @Override
    public Long save(T admin) {
        return null;
    }

    @Override
    public Long update(T admin) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
