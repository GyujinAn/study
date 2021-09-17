package com.msaweb.login.service.impl;

import com.msaweb.login.model.LoginVo;
import com.msaweb.login.service.LoginService;
import com.msaweb.memberapi.model.Admin;
import com.msaweb.memberapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author agj017@gmail.com
 * @since 2021/09/17
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class LgoinServiceImpl implements LoginService {

    private final MemberRepository mr;


    @Override
    public Admin loginAdmin(LoginVo loginVo) {

        Admin admin = mr.findAdminByLoginId(loginVo.getLoginId());

        if(loginVo.getLoginPw().equals(admin.getLoginPw())){
            return admin;
        }

        return null;
    }


}
