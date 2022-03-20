package kakaostyle.documentapproval.user;

import kakaostyle.documentapproval.BaseTimeEntity;
import kakaostyle.documentapproval.document.domain.Approver;
import kakaostyle.documentapproval.document.domain.Document;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Getter
public class KakaoUser extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String name;

//    @OneToMany(mappedBy = "kakaoUser")
//    private List<Approver> approvers = new ArrayList<>();

//    @OneToMany(mappedBy = "kakaoUser")
//    private List<Document> documents = new ArrayList<>();


    public KakaoUser() {

    }

    @Builder
    public KakaoUser(String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
