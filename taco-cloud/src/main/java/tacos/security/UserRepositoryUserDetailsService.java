package tacos.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tacos.User;
import tacos.data.UserRepository;

/**
 * @author agj017@gmail.com
 * @since 2021/10/22
 */
@Service
@RequiredArgsConstructor
public class UserRepositoryUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User byUsername = userRepository.findByUsername(s);
        if(byUsername == null){
            throw new UsernameNotFoundException("User " + s + "not found");
        }
        return byUsername;
    }
}
