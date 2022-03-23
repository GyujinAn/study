package kakaostyle.documentapproval.security;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    String email;
    String password;
}
