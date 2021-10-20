package com.msaweb.memberapi.member.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author agj017@gmail.com
 * @since 2021/10/19
 */
public abstract class AbstractMemberService<T> {
    JpaRepository<T, Long> repo;

    T get(Long id){
        T member = repo.getById(id);
        return member;
    };

    Long save(T member){
        T savedMember = repo.save(member);
        return getMemberId(member);
    };


    Long update(T member){

        Long memberId = getMemberId(member);
        Optional<T> byId = repo.findById(memberId);

        putMemberInfo(member);

        return memberId;
    };

    void delete(Long id){
    };

    abstract protected Long getMemberId(T member);

    abstract protected Long putMemberInfo(T member);

}
