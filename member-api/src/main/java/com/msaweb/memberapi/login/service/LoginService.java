package com.msaweb.memberapi.login.service;

import com.msaweb.memberapi.login.model.LoginVo;
import com.msaweb.memberapi.member.model.entity.Admin;

/**
 * @author agj017@gmail.com
 * @since 2021/09/17
 */
public interface LoginService {

    Admin loginAdmin(LoginVo loginVo);
}
