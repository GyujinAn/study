package com.msaweb.memberapi.member.model.MemberManager;

import com.msaweb.memberapi.member.model.entity.Member;

/**
 * @author agj017@gmail.com
 * @since 2021/10/04
 */
public interface MemberManager {
    Member get();

    Long save(Member member);

    Long update(Member member);

    void delete(Member member);



}
