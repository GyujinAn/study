package com.msaweb.memberapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * @author agj017@gmail.com
 * @since 2021/09/14
 */
@Entity
@Getter
@Setter
public class Admin extends Member{
    private String etc;
}
