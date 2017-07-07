package br.com.crescer.caronas.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SocialUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final List<GrantedAuthority> grants = new ArrayList<>();
        if ("admin".equals(username)) {
            grants.add(() -> "ROLE_ADMIN");
        }
        return new User(username, new BCryptPasswordEncoder().encode("password"), grants);
    }

}
