package gyujinan.run;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author agj017@gmail.com
 * @since 2021/09/12
 */


@RunWith(SpringRunner.class)//Spring과 관련된 테스트를 할거라고 junit에게 알려준다.
@SpringBootTest
public class MemberRepositoryBakTest {

    @Autowired
    MemberRepositoryBak memberRepository;

    @Test
    @Transactional //Transactional 어노테이션이 테스트 케이스에 있으면 바로 트랜젝션을 롤백한다.
    @Rollback(value = false) //테스트 케이스에 대한 트랜젝션을 롤백하고 싶지 않을 때 사용
    public void testMember() throws Exception {
        //given
        MemberBak member = new MemberBak();
        member.setUsername("memberA");


        //when
        Long saveId = memberRepository.save(member);
        MemberBak findMember = memberRepository.find(saveId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);

        // true 같은 트렌젝션 안에서는 persistance context가 똑같다.
        // 같은 persistance context에서는 id가 같으면 같은 entity로 인식한다.
        System.out.println("findMember == member : " + (findMember == member));



    }


}