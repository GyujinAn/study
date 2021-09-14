package com.example.memberapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author agj017@gmail.com
 * @since 2021/09/14
 */


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
}
