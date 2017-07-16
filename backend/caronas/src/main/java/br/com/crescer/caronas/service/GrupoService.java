package br.com.crescer.caronas.service;

import br.com.crescer.caronas.entity.Grupo;
import br.com.crescer.caronas.entity.Notificacao;
import br.com.crescer.caronas.entity.Rotina;
import br.com.crescer.caronas.repository.GrupoRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chris
 */
@Service
public class GrupoService {

    @Autowired
    GrupoRepository grupoRepository;

    @Autowired
    UsuarioGrupoService usuarioGrupoService;

    @Autowired
    NotificacaoService notificacaoService;

    public Iterable<Grupo> findAll() {
        return grupoRepository.findAll();
    }

    public Grupo save(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    public Grupo update(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    //TODO: VERIFICAR SE ELE VAI A BANCO PERSISTIR AS NOTIFICAÇÕES
    @Transactional
    public void remove(Grupo grupo) {
        String conteudoNotificacao = String.format("O grupo '%s' que você participa foi excluído", grupo.getNome());
        Notificacao notificacao = new Notificacao(conteudoNotificacao, null);
        notificacaoService.enviarNotificacao(grupo, notificacao);
        grupoRepository.delete(grupo);
    }

    public Grupo loadById(Long id) {
        return grupoRepository.findOne(id);
    }

    public Grupo loadByRotina(Rotina rotina) {
        return grupoRepository.findOneByRotina(rotina);
    }

}
