package com.msaweb.memberapi.member.controller;

import com.msaweb.memberapi.member.model.entity.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author agj017@gmail.com
 * @since 2021/10/04
 */
@RestController
@RequestMapping("/member/provider")
public class ProviderController extends MemberController<Provider>{
}
