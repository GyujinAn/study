package com.example.resource1;

import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)//Spring과 관련된 테스트를 할거라고 junit에게 알려준다.
@SpringBootTest
public class QuestionRepositoryTest {

    @Autowired
    QuestionRepository questionRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testQuestion() throws Exception {

        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setContent("Test");
        questionEntity.setCreateAt(LocalDateTime.now());
        questionEntity.setEmail("test@test.com");
        questionEntity.setStatus(QuestionEntity.Status.APPLY);
        questionEntity.setTitle("test");

        QuestionEntity save = questionRepository.save(questionEntity);


        System.out.println("id: " + save.getId());


    }


}