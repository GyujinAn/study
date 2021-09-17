package com.msaweb.memberapi.controller;

import com.msaweb.memberapi.model.Admin;
import com.msaweb.memberapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author agj017@gmail.com
 * @since 2021/09/17
 */

@RestController
@RequiredArgsConstructor
public class AdminController{

    private final MemberRepository memberRepository;

    @GetMapping("/admin/{id}")
    @Transactional
    public Admin read(@PathVariable long id) {

        Admin admin = memberRepository.findAdmin(id);
        return admin;
    }


    @PostMapping("/admin")
    @Transactional
    public Long create(@RequestBody Admin admin) {


        Long id = memberRepository.save(admin);

        return id;
    }

    @PutMapping("/admin/{id}")
    public void update(@PathVariable long id) {

    }

    @DeleteMapping("/admin/")
    public void delete(@PathVariable long id) {

    }

}
