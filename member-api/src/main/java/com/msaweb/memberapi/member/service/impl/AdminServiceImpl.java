package com.msaweb.memberapi.member.service.impl;

import com.msaweb.memberapi.member.model.entity.Admin;
import com.msaweb.memberapi.member.service.AbstractMemberService;

/**
 * @author agj017@gmail.com
 * @since 2021/10/19
 */
public class AdminServiceImpl extends AbstractMemberService<Admin> {
    @Override
    protected Long getMemberId(Admin member) {
        return member.getId();
    }

    @Override
    protected Long putMemberInfo(Admin member) {
        return null;
    }
}
