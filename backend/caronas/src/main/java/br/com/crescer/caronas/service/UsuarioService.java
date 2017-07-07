package br.com.crescer.caronas.Service;

import br.com.crescer.caronas.entity.Usuario;
import br.com.crescer.caronas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chris
 */
@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Iterable<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void remove(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    public Usuario loadById(Long id) {
        return usuarioRepository.findOne(id);
    }
}