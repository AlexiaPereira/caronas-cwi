package br.com.crescer.caronas.service;

import br.com.crescer.caronas.Service.GrupoService;
import br.com.crescer.caronas.Service.UsuarioGrupoService;
import br.com.crescer.caronas.dto.SolicitacaoRotinaDTO;
import br.com.crescer.caronas.entity.Grupo;
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

    public Iterable<Solicitacao> findAll() {
        return solicitacaoRepository.findAll();
    }

    public Solicitacao save(Solicitacao solicitacao) {
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

    public void aceitarSolicitacao(SolicitacaoRotinaDTO solicitacaoDTO) {
        Grupo grupo = grupoService.loadByRotina(solicitacaoDTO.getRotinaMotorista());
        UsuarioGrupo usuarioGrupo = new UsuarioGrupo(solicitacaoDTO.getSolicitacao().getUsuarioDono(), grupo, new Date());
        usuarioGrupoService.save(usuarioGrupo);
        solicitacaoRepository.delete(solicitacaoDTO.getSolicitacao());
    }

}
