package br.com.crescer.caronas.service;

import br.com.crescer.caronas.entity.Notificacao;
import br.com.crescer.caronas.repository.NotificacaoRepository;
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

    public Iterable<Notificacao> findAll() {
        return notificacaoRepository.findAll();
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

    public Notificacao loadById(Long id) {
        return notificacaoRepository.findOne(id);
    }
}
