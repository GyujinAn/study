package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * @author gj.an@okestro.com
 * @since 2021/06/23
 */
public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            //데이터 생성
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("helloB");

            //데이터 검색
//            Member member = em.find(Member.class, 1L);

            //데이터 수정
//            member.setName("helloJpa");

//            em.persist(member);



//            List<Member> resultList = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(2)
//                    .getResultList();
///Users/gyujinan/workspace/okestro/cloud-platform-lab-setting/portal-dev/ingress-portal-dev.yml
//
//            for(Member member : resultList){
//                System.out.println("member.getName() = " + member.getName());
//            }
            
            ///Users/gyujinan/workspace/okestro/cloud-platform-lab-setting/portal-dev/admin-api/svc-admin-api.yml비영속성
            Member member = new Member();
            member.setId(100L);
            member.setName("Hello");
            
            //영속
            System.out.println("== BEFORM ==");
            em.persist(member);
            //영속성 컨테스트에서 지워
            //em.detach(member);

            //DB에서 지워
            //em.remove(member);
            System.out.println("== AFTER ==");
            
            Member findMember = em.find(Member.class, 100L);

            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getName());
            
            
            //영속성

            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally {
            //내부적으로 데이타베이스 커넥션을 물고 동작하기 때문에 끝나면 닫아줘야한다.
            em.close();
        }

        emf.close();

    }

}
