//package com.msaweb.memberapi.member.controller;
//
//import com.msaweb.memberapi.member.model.entity.Admin;
//import com.msaweb.memberapi.member.model.entity.Member;
//import com.msaweb.memberapi.member.repository.AdminRepository;
//import com.msaweb.memberapi.member.service.MemberService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
///**
// * @author agj017@gmail.com
// * @since 2021/09/17
// */
//
//@RestController
//@RequestMapping("/member/admin")
//@RequiredArgsConstructor
//public class AdminController{
//
//    private final AdminRepository adminRepository;
//
//    @GetMapping("/{id}")
//    public Admin read(@PathVariable long id) {
//
//        return null;
//    }
//
//
//    @PostMapping
//    public Long create(@RequestBody Admin admin) {
//
//        Admin save = adminRepository.save(admin);
//        return save.getId();
//    }
//
//    @PutMapping("/{id}")
//    public void update(@PathVariable long id) {
//
//    }
//
//    @DeleteMapping()
//    public void delete(@PathVariable long id) {
//
//    }
//
//}
