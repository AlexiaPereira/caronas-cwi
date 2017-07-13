package br.com.crescer.caronas.service;

import br.com.crescer.caronas.entity.Notificacao;
import br.com.crescer.caronas.entity.UsuarioGrupo;
import br.com.crescer.caronas.repository.UsuarioGrupoRepository;
import static java.util.stream.Collectors.toList;
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

    //TODO: VERIFICAR SE ELE VAI A BANCO PERSISTIR AS NOTIFICAÇÕES
    public UsuarioGrupo save(UsuarioGrupo usuarioGrupo) {
        if (usuarioEstaNoGrupo(usuarioGrupo)) {
            throw new RuntimeException("O usuário já está nesse grupo");
        }
        String conteudoNotificacao = String.format("%s entrou no grupo %s", usuarioGrupo.getUsuario().getNome(), usuarioGrupo.getGrupo().getNome());
        Notificacao notificacao = new Notificacao(conteudoNotificacao, null);
        usuarioGrupo.getGrupo().getUsuarioGrupoList()
                .stream()
                .map(UsuarioGrupo::getUsuario)
                .forEach(usuario -> {
                    notificacao.setUsuario(usuario);
                    usuario.getNotificacaoList().add(notificacao);
                });
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

    public boolean usuarioEstaNoGrupo(UsuarioGrupo usuarioGrupo) {
        return usuarioGrupo.getGrupo().getUsuarioGrupoList()
                .stream()
                .map(UsuarioGrupo::getUsuario)
                .collect(toList())
                .contains(usuarioGrupo.getUsuario());
    }
}
