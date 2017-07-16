package br.com.crescer.caronas.service;

import br.com.crescer.caronas.entity.Grupo;
import br.com.crescer.caronas.entity.Notificacao;
import br.com.crescer.caronas.entity.Usuario;
import br.com.crescer.caronas.entity.UsuarioGrupo;
import br.com.crescer.caronas.repository.NotificacaoRepository;
import br.com.crescer.caronas.repository.UsuarioGrupoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chris
 */
@Service
public class NotificacaoService {

    @Autowired
    NotificacaoRepository notificacaoRepository;

    @Autowired
    UsuarioGrupoRepository usuarioGrupoRepository;

    public Iterable<Notificacao> findAll() {
        return notificacaoRepository.findAll();
    }

    public List<Notificacao> findAllByUsuario(Usuario usuario) {
        return notificacaoRepository.findByUsuario(usuario);
    }

    public Notificacao save(Notificacao notificacao) {
        return notificacaoRepository.save(notificacao);
    }

    public Notificacao update(Notificacao notificacao) {
        return notificacaoRepository.save(notificacao);
    }

    public void remove(Notificacao notificacao) {
        notificacaoRepository.delete(notificacao);
    }
    
    public void removeByUser(Usuario usuario) {
        List<Notificacao> notificacoesUsuario = notificacaoRepository.findByUsuario(usuario);
        notificacoesUsuario.forEach((notificacao) -> {
        notificacaoRepository.delete(notificacao);
        });
    }
    
    public Notificacao loadById(Long id) {
        return notificacaoRepository.findOne(id);
    }

    public void enviarNotificacao(Grupo grupo, Notificacao notificacao) {
        List<UsuarioGrupo> usuariosDoGrupo = usuarioGrupoRepository.findByGrupo(grupo);
        usuariosDoGrupo
                .stream()
                .map(UsuarioGrupo::getUsuario)
                .forEach(usuario -> {
                    notificacao.setUsuario(usuario);
                    usuario.getNotificacaoList().add(notificacao);
                    this.save(notificacao);
                });
    }
}
