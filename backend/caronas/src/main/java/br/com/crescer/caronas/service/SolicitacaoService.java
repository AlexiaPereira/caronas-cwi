package br.com.crescer.caronas.service;

import br.com.crescer.caronas.entity.Rotina;
import br.com.crescer.caronas.entity.RotinaDiaSemana;
import br.com.crescer.caronas.entity.Grupo;
import br.com.crescer.caronas.entity.Notificacao;
import br.com.crescer.caronas.entity.Solicitacao;
import br.com.crescer.caronas.entity.Usuario;
import br.com.crescer.caronas.entity.UsuarioGrupo;
import br.com.crescer.caronas.repository.SolicitacaoRepository;
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
    RotinaDiaSemanaService rotinaDiaSemanaService;

    private ValidarVagasService validarVagasService;

    public Iterable<Solicitacao> findAll() {
        return solicitacaoRepository.findAll();
    }

    public Solicitacao save(Solicitacao solicitacao) {
        if (!this.solicitacaoEhValida(solicitacao)) {
            throw new RuntimeException("Solicitação Inválida");
        }
        String conteudo = String.format("%s solicitou entrar no grupo %s", solicitacao.getUsuarioDono().getNome(), solicitacao.getGrupo().getNome());
        Notificacao notificacao = new Notificacao(conteudo, null);
        usuarioGrupoService.enviarNotificacao(solicitacao.getGrupo().getUsuarioGrupoList(), notificacao);
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

    public void aceitarSolicitacao(Solicitacao solicitacao) {
        UsuarioGrupo usuarioGrupo = new UsuarioGrupo(solicitacao.getUsuarioDono(), solicitacao.getGrupo(), new Date());
        solicitacao.getRotinaUsuarioDono().setDisponivel(false);
        this.descontarVagas(solicitacao.getRotinaUsuarioDono(), solicitacao.getGrupo().getRotina());
        grupoService.update(usuarioGrupo.getGrupo());
        //        rotinaService.update(usuarioGrupo.getGrupo().getRotina());
        usuarioGrupoService.save(usuarioGrupo);
        solicitacaoRepository.delete(solicitacao);
    }

    private boolean solicitacaoEhValida(Solicitacao solicitacao) {
        return solicitacao.getUsuarioAlvo().getIdAutorizacao() != solicitacao.getUsuarioDono().getIdAutorizacao()
                && solicitacaoRepository.countByUsuarioDonoAndGrupo(solicitacao.getUsuarioDono(), solicitacao.getGrupo()) == 0;
    }

    public void descontarVagas(Rotina rotinaPrincipal, Rotina rotinaMatchMotorista) {
        validarVagasService = new ValidarVagasService();
        List<RotinaDiaSemana> diasDaSemanaComMatch
                = validarVagasService.filtrarDiaSemana(
                        validarVagasService.gerarDiasRotinaPrincipal(rotinaPrincipal), rotinaMatchMotorista);
//        List<RotinaDiaSemana> auxiliar = new ArrayList<>();
        rotinaMatchMotorista.getRotinaDiaSemanaList()
                .forEach(rotinaDiaSemana -> {
                    rotinaDiaSemana.setRotina(rotinaMatchMotorista);
                    if (diasDaSemanaComMatch.contains(rotinaDiaSemana)) {
                        rotinaDiaSemana.setVagasDisponiveis(rotinaDiaSemana.getVagasDisponiveis() - 1);
//                        rotinaDiaSemanaService.update(rotinaDiaSemana);
                    }
                });

    }

}
