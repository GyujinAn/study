package kakaostyle.documentapproval.security;


import kakaostyle.documentapproval.user.KakaoUser;
import kakaostyle.documentapproval.user.KakaoUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class KakaoUserDetailsService implements UserDetailsService {

    final private KakaoUserRepository kakaoUserRepository;

    final private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        KakaoUser kakaoUser = kakaoUserRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 사용자"));
        return kakaoUser;
    }

    @Transactional(readOnly = true)
    public KakaoUser login(String email, String password){
        KakaoUser kakaoUser = kakaoUserRepository.findByEmail(email);
        if(kakaoUser == null){
            throw new LoginFailedException("사용자를 찾을 수 없습니다.");
        }
        if(!passwordEncoder.matches(password, kakaoUser.getPassword())){
            throw new LoginFailedException("비밀번호가 유효하지 않습니다");
        }

        return kakaoUser;

    }
}
