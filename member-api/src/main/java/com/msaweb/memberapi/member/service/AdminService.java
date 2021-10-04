package com.msaweb.memberapi.member.service;

import com.msaweb.memberapi.member.model.entity.Admin;

/**
 * @author agj017@gmail.com
 * @since 2021/10/04
 */
public interface AdminService <T> {
    Admin get(Long id);

    Long save(Admin admin);

    Long update(Admin admin);

    void delete(Long id);
}
