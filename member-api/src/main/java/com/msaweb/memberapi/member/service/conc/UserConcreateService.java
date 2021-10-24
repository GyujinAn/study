package com.msaweb.memberapi.member.service.conc;

import com.msaweb.memberapi.member.model.entity.User;
import com.msaweb.memberapi.member.service.AbstractMemberService;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author agj017@gmail.com
 * @since 2021/10/21
 */
public class UserConcreateService extends AbstractMemberService<User> {

    public UserConcreateService(JpaRepository<User, Long> repo) {
        super(repo);
    }

    @Override
    protected Long putMemberInfo(User member) {
        return null;
    }

}
