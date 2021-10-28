package jpabopok.jpashop.domain;

import hellojpa.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author gj.an@okestro.com
 * @since 2021/07/02
 */
public class JpaMain {


    public static void main(String[] args) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{

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
