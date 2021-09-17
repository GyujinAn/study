package com.msaweb.login.service;

import com.msaweb.login.model.LoginVo;
import com.msaweb.memberapi.model.Admin;

/**
 * @author agj017@gmail.com
 * @since 2021/09/17
 */
public interface LoginService {

    Admin loginAdmin(LoginVo loginVo);
}
