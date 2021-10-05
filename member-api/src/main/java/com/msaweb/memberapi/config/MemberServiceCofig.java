package com.msaweb.memberapi.config;

import com.msaweb.memberapi.member.model.entity.Admin;
import com.msaweb.memberapi.member.model.entity.Provider;
import com.msaweb.memberapi.member.model.entity.User;
import com.msaweb.memberapi.member.service.MemberService;
import com.msaweb.memberapi.member.service.impl.MemberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author agj017@gmail.com
 * @since 2021/10/04
 */
@Configuration
public class MemberServiceCofig {

    @Bean
    public MemberService adminService(){

        return new MemberServiceImpl<Admin>();
    }

    @Bean
    public MemberService userService(){

        return new MemberServiceImpl<User>();
    }

    @Bean
    public MemberService providerService(){

        return new MemberServiceImpl<Provider>();
    }
}
