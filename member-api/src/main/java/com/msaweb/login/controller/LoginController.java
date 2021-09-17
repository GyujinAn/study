package com.msaweb.login.controller;

import com.msaweb.login.model.LoginVo;
import com.msaweb.login.service.LoginService;
import com.msaweb.memberapi.model.Admin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author agj017@gmail.com
 * @since 2021/09/17
 */

@Slf4j
@RequiredArgsConstructor
@RestController
public class LoginController {


    private final LoginService loginService;


    @PostMapping("/admin/login")
    public String login(@RequestBody LoginVo loginVo, HttpSession httpSession){


        Admin admin = loginService.loginAdmin(loginVo);

        if(admin == null){
            return "failed";
        }

        httpSession.setAttribute("lgoinId", admin.getLoginId());

        return "succeeded";
    }

    @GetMapping("/checkLogin")
    public String checkLogin(HttpSession httpSession){

        if(httpSession == null){
            return "null";
        }
        return httpSession.getAttribute("lgoinId").toString();

    }
}
