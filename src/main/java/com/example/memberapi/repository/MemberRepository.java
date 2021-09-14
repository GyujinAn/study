package com.example.memberapi.repository;

import com.example.memberapi.model.Admin;
import com.example.memberapi.model.Provider;
import com.example.memberapi.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author agj017@gmail.com
 * @since 2021/09/14
 */

@Repository
public class MemberRepository {
    @PersistenceContext
    EntityManager em;

    public Long save(Admin admin){
        em.persist(admin);
        return admin.getId();
    }

    public Admin findAdmin (Long id){

        return em.find(Admin.class, id);
    }

    public Long save(User user){
        em.persist(user);
        return user.getId();
    }

    public User findUser (Long id){
        return em.find(User.class, id);
    }

    public Long save(Provider provider){
        em.persist(provider);
        return provider.getId();
    }

    public Provider findProvider (Long id){
        return em.find(Provider.class, id);
    }


}
