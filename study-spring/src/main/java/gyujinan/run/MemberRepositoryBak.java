package gyujinan.run;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author agj017@gmail.com
 * @since 2021/09/12
 */

@Repository
public class MemberRepositoryBak {

    @PersistenceContext
    private EntityManager em;

    public Long save(MemberBak member){
        em.persist(member);
        return member.getId();

    }

    public MemberBak find(Long id){
        return em.find(MemberBak.class, id);

    }

}
