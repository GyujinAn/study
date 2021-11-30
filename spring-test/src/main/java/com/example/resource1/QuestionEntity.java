package com.example.resource1;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author agj017@gmail.com
 * @since 2021/11/30
 */

@Entity
@Setter
@Getter
@Table(name="TADP_UN_LOGIN_USER_QNA")
public class QuestionEntity {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="EMAIL")
    private String email;

    @Transient
    private String emailAccount;

    @Transient
    private String emailAddress;

    @Column(name="TTL")
    private String title;

    @Column(name="CN")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name="STT")
    private Status status;

    @Column(name="UPD_DT")
    private LocalDateTime updateAt;

    @Column(name="CREAT_DT")
    private LocalDateTime createAt;

    public enum Status{
        APPLY, ANSWERED
    }


}