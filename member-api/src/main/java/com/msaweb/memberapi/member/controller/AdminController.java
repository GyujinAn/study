package com.msaweb.memberapi.member.controller;

import com.msaweb.memberapi.member.model.entity.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author agj017@gmail.com
 * @since 2021/09/17
 */

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class AdminController{



    @GetMapping("/admin/{id}")
    public Admin read(@PathVariable long id) {

        return null;
    }


    @PostMapping("/admin")
    public Long create(@RequestBody Admin admin) {

        return 0L;
    }

    @PutMapping("/admin/{id}")
    public void update(@PathVariable long id) {

    }

    @DeleteMapping("/admin/")
    public void delete(@PathVariable long id) {

    }

}
