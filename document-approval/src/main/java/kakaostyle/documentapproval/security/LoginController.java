package kakaostyle.documentapproval.security;

import io.jsonwebtoken.Jwt;
import kakaostyle.documentapproval.user.KakaoUser;
import kakaostyle.documentapproval.user.KakaoUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class LoginController {

    final private JwtProvider jwtProvider;

    final private KakaoUserDetailsService kakaoUserDetailsService;

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto, HttpServletResponse httpServletResponse){


        try{
            KakaoUser login = kakaoUserDetailsService.login(loginDto.getEmail(), loginDto.getPassword());
            String token = jwtProvider.createToken(String.valueOf(login.getId()));
            httpServletResponse.setStatus(HttpStatus.OK.value());
            httpServletResponse.setHeader("X-AUTH-TOKEN",token);
            return "success";
        }catch (LoginFailedException exception){
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            return exception.getMessage();
        }


    }
}
