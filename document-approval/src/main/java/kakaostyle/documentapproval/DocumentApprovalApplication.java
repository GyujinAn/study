package kakaostyle.documentapproval;


import kakaostyle.documentapproval.user.KakaoUser;
import kakaostyle.documentapproval.user.KakaoUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@SpringBootApplication
public class DocumentApprovalApplication implements CommandLineRunner {


	final private KakaoUserRepository kakaoUserRepository;


//	final private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(DocumentApprovalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String password = passwordEncoder().encode("password");

		kakaoUserRepository.save(KakaoUser.builder().email("user1@kakao.com").name("안규진").password(password).build());
		kakaoUserRepository.save(KakaoUser.builder().email("user2@kakao.com").name("안규진").password(password).build());
		kakaoUserRepository.save(KakaoUser.builder().email("user3@kakao.com").name("안규진").password(password).build());

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
