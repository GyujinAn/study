package com.msaweb.memberapi.member.service.conc;

import com.msaweb.memberapi.member.model.entity.Admin;
import com.msaweb.memberapi.member.service.AbstractMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author agj017@gmail.com
 * @since 2021/10/19
 */
@Service
public class AdminConcreteService extends AbstractMemberService<Admin> {

    @Autowired
    public AdminConcreteService(JpaRepository<Admin, Long> repo) {
        super(repo);
    }

    @Override
    protected Long putMemberInfo(Admin member) {
        repo.getById(1L);
        return null;
    }
}
