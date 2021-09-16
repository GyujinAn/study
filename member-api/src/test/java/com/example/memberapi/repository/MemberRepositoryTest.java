package com.example.memberapi.repository;

import com.example.memberapi.model.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * @author agj017@gmail.com
 * @since 2021/09/14
 */


@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void saveAndFindAdmin() throws Exception {
        //given
        Admin admin = new Admin();
        admin.setName("hello");

        //when
        Long saveId = memberRepository.save(admin);
        Admin findAdmin = memberRepository.findAdmin(saveId);

        //then
        assertThat(saveId, is(findAdmin.getId()));
    }

}