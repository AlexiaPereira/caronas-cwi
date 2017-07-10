package br.com.crescer.caronas.service;

import br.com.crescer.caronas.entity.UsuarioGrupo;
import br.com.crescer.caronas.repository.UsuarioGrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chris
 */
@Service
public class UsuarioGrupoService {

    @Autowired
    UsuarioGrupoRepository usuarioGrupoRepository;

    public Iterable<UsuarioGrupo> findAll() {
        return usuarioGrupoRepository.findAll();
    }

    public UsuarioGrupo save(UsuarioGrupo usuarioGrupo) {
        return usuarioGrupoRepository.save(usuarioGrupo);
    }

    public UsuarioGrupo update(UsuarioGrupo usuarioGrupo) {
        return usuarioGrupoRepository.save(usuarioGrupo);
    }

    public void remove(UsuarioGrupo usuarioGrupo) {
        usuarioGrupoRepository.delete(usuarioGrupo);
    }

    public UsuarioGrupo loadById(Long id) {
        return usuarioGrupoRepository.findOne(id);
    }
}