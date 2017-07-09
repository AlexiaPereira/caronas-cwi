package br.com.crescer.caronas.security;

import br.com.crescer.caronas.entity.Usuario;
import br.com.crescer.caronas.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SocialUserDetailsService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final List<GrantedAuthority> grants = new ArrayList<>();
        if (username.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Usuario com identificador =%s não encontrado", username));
        }
        Usuario usuarioAutorizado = usuarioRepository.findByIdAutorizacao(username);
        return new User(usuarioAutorizado.getIdAutorizacao(), usuarioAutorizado.getSenha(), grants);
    }

}
