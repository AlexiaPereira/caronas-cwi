package br.com.crescer.caronas.service;

import br.com.crescer.caronas.entity.Grupo;
import br.com.crescer.caronas.entity.Notificacao;
import br.com.crescer.caronas.entity.Usuario;
import br.com.crescer.caronas.entity.UsuarioGrupo;
import br.com.crescer.caronas.repository.UsuarioGrupoRepository;
import java.util.List;
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

    @Autowired
    NotificacaoService notificacaoService;

    public Iterable<UsuarioGrupo> findAll() {
        return usuarioGrupoRepository.findAll();
    }

    public UsuarioGrupo save(UsuarioGrupo usuarioGrupo) {
        if (usuarioEstaNoGrupo(usuarioGrupo, usuarioGrupo.getGrupo())) {
            throw new RuntimeException("O usuário já está nesse grupo");
        }
        String conteudoNotificacao = String.format("%s entrou no grupo %s", usuarioGrupo.getUsuario().getNome(), usuarioGrupo.getGrupo().getNome());
        Notificacao notificacao = new Notificacao(conteudoNotificacao, null);
        notificacaoService.enviarNotificacao(usuarioGrupo.getGrupo(), notificacao);
        return usuarioGrupoRepository.save(usuarioGrupo);
    }

    public UsuarioGrupo update(UsuarioGrupo usuarioGrupo) {
        return usuarioGrupoRepository.save(usuarioGrupo);
    }

    public void remove(UsuarioGrupo usuarioGrupo) {
        List<UsuarioGrupo> usuariosDoGrupo = usuarioGrupoRepository.findByGrupo(usuarioGrupo.getGrupo());

        if (!this.usuarioEstaNoGrupo(usuarioGrupo, usuarioGrupo.getGrupo())) {
            throw new RuntimeException("Usuário não está no grupo");
        }

        String conteudoNotificacao = String.format("%s deixou o grupo '%s'", usuarioGrupo.getUsuario().getNome(), usuarioGrupo.getGrupo().getNome());
        Notificacao notificacao = new Notificacao(conteudoNotificacao, null);
        notificacaoService.enviarNotificacao(usuarioGrupo.getGrupo(), notificacao);
        usuarioGrupoRepository.delete(usuarioGrupo);
    }

    public UsuarioGrupo loadById(Long id) {
        return usuarioGrupoRepository.findOne(id);
    }

    public List<UsuarioGrupo> findByUsuario(Usuario usuario) {
        return usuarioGrupoRepository.findByUsuario(usuario);
    }

    public boolean usuarioEstaNoGrupo(UsuarioGrupo usuarioGrupo, Grupo grupo) {
        List<UsuarioGrupo> usuariosDoGrupo = usuarioGrupoRepository.findByGrupo(usuarioGrupo.getGrupo());
        return usuariosDoGrupo
                .stream()
                .map(UsuarioGrupo::getUsuario)
                .collect(toList())
                .contains(usuarioGrupo.getUsuario());
    }

    public void deleteByGrupo(Grupo grupo) {
        usuarioGrupoRepository.deleteByGrupo(grupo);
    }

    public UsuarioGrupo findByUsuarioAndGrupo(Usuario usuario, Grupo grupo) {
        return usuarioGrupoRepository.findByUsuarioAndGrupo(usuario, grupo);
    }

}
