package br.com.crescer.caronas.service;

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
        usuario.criptografarSenha();
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
    
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    
    public Usuario findByIdAutorizacao(String idAutorizacao) {
        return usuarioRepository.findByIdAutorizacao(idAutorizacao);
    }

    public Usuario verificarUsuario(Usuario usuario) {
        Usuario autorizado = this.findByIdAutorizacao(usuario.getIdAutorizacao());
        if (autorizado != null) {
            return autorizado;
        } else {
            return this.save(usuario);
        }
    }
}