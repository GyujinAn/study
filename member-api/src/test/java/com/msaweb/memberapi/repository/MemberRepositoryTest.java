package com.msaweb.memberapi.repository;

import com.msaweb.memberapi.model.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author agj017@gmail.com
 * @since 2021/09/17
 */

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;


    @Test
    @Transactional
    @Rollback(value = false)
    public void save() throws Exception {
        //given
        Admin admin = new Admin();
        admin.setName("hellow");


        //when
        Long saveId = memberRepository.save(admin);
        Admin findAdmin = memberRepository.findAdmin(saveId);

        //then
        assertEquals(saveId, findAdmin.getId());

    }
}