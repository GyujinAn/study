package com.example.shopping.api.member;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author agj017@gmail.com
 * @since 2021/11/05
 */

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Getter
public abstract class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String loginId;

    private String loginPw;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;

    private enum Role{
        CONSUMER, MODEL, SELLER, ADMIN
    }

    private enum Status{
        MEMBER, WITHDRAWAL
    }

}
