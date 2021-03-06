package br.com.crescer.caronas.service;

import br.com.crescer.caronas.entity.Notificacao;
import br.com.crescer.caronas.entity.Solicitacao;
import br.com.crescer.caronas.entity.Usuario;
import br.com.crescer.caronas.entity.UsuarioGrupo;
import br.com.crescer.caronas.repository.SolicitacaoRepository;
import br.com.crescer.caronas.service.exceptions.CaronasException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexia.pereira
 */
@Service
public class SolicitacaoService {

    @Autowired
    SolicitacaoRepository solicitacaoRepository;

    @Autowired
    GrupoService grupoService;

    @Autowired
    UsuarioGrupoService usuarioGrupoService;

    @Autowired
    NotificacaoService notificacaoService;

    @Autowired
    RotinaDiaSemanaService rotinaDiaSemanaService;

    @Autowired
    RotinaService rotinaService;

    public Iterable<Solicitacao> findAll() {
        return solicitacaoRepository.findAll();
    }

    public Solicitacao save(Solicitacao solicitacao) throws CaronasException{
        try {
            if (!this.solicitacaoEhValida(solicitacao)) {
                throw new CaronasException("A solicitação não é válida!");
            }
            
        } catch (RuntimeException ex) {
        }
        
        String conteudo = String.format("%s solicitou entrar no grupo %s", solicitacao.getUsuarioDono().getNome(), solicitacao.getGrupo().getNome());
        Notificacao notificacao = new Notificacao(conteudo, null);
        notificacaoService.enviarNotificacaoTodosUsuariosDoGrupo(solicitacao.getGrupo(), notificacao);
        return solicitacaoRepository.save(solicitacao);
    }

    public Solicitacao update(Solicitacao solicitacao) {
        return solicitacaoRepository.save(solicitacao);
    }

    public void remove(Solicitacao solicitacao) {
        solicitacaoRepository.delete(solicitacao);
    }

    public Solicitacao loadById(Long id) {
        return solicitacaoRepository.findOne(id);
    }

    public List<Solicitacao> loadByUsuarioAlvo(Usuario usuarioAlvo) {
        return solicitacaoRepository.findByUsuarioAlvo(usuarioAlvo);
    }

    public void aceitarSolicitacao(Solicitacao solicitacao) throws CaronasException {
        UsuarioGrupo usuarioGrupo = new UsuarioGrupo(solicitacao.getUsuarioDono(), solicitacao.getGrupo(), new Date());
        solicitacao.getRotinaUsuarioDono().setDisponivel(false);
        rotinaService.update(solicitacao.getRotinaUsuarioDono());
        usuarioGrupoService.descontarVagas(solicitacao.getRotinaUsuarioDono(), solicitacao.getGrupo().getRotina(), -1);
        grupoService.update(usuarioGrupo.getGrupo());
        usuarioGrupoService.save(usuarioGrupo);
        solicitacaoRepository.delete(solicitacao);
    }

    private boolean solicitacaoEhValida(Solicitacao solicitacao) {
        return solicitacao.getUsuarioAlvo().getIdAutorizacao() != solicitacao.getUsuarioDono().getIdAutorizacao()
                && solicitacaoRepository.countByUsuarioDonoAndGrupo(solicitacao.getUsuarioDono(), solicitacao.getGrupo()) == 0;
    }

}
