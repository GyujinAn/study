package com.example.shopping.api.member;

import lombok.Getter;

import javax.persistence.Entity;

/**
 * @author agj017@gmail.com
 * @since 2021/11/05
 */
@Entity
@Getter
public class Consumer extends Member {

    private String url;

}
