package br.com.crescer.caronas.controller;

import br.com.crescer.caronas.dto.SolicitacaoRotinaDTO;
import br.com.crescer.caronas.entity.Grupo;
import br.com.crescer.caronas.entity.Rotina;
import br.com.crescer.caronas.entity.Solicitacao;
import br.com.crescer.caronas.entity.Usuario;
import br.com.crescer.caronas.service.GrupoService;
import br.com.crescer.caronas.service.NotificacaoService;
import br.com.crescer.caronas.service.RotinaService;
import br.com.crescer.caronas.service.SolicitacaoService;
import br.com.crescer.caronas.service.UsuarioService;
import br.com.crescer.caronas.service.exceptions.CaronasException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alexia.pereira
 */
@RestController
@RequestMapping("/solicitacoes")
public class SolicitacaoController {
    
    @Autowired
    SolicitacaoService solicitacaoService;
    
    @Autowired
    NotificacaoService notificacaoService;
    
    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    GrupoService grupoService;
    
    @Autowired
    RotinaService rotinaService;
    
    @GetMapping
    public Iterable<Solicitacao> findAll() {
        return solicitacaoService.findAll();
    }
    
    @GetMapping(value = "/{id}")
    public Solicitacao loadById(@PathVariable Long id) {
        return solicitacaoService.loadById(id);
    }
    
    @PostMapping
    public Solicitacao save(@RequestBody SolicitacaoRotinaDTO solicitacaoDTO, @AuthenticationPrincipal User user) throws CaronasException {
        Usuario usuarioDono = usuarioService.findByIdAutorizacao(user.getUsername());
        Usuario usuarioAlvo = usuarioService.findByIdAutorizacao(solicitacaoDTO.getSolicitacao().getUsuarioAlvo().getIdAutorizacao());
        Rotina rotinaPassageiro = rotinaService.loadById(solicitacaoDTO.getSolicitacao().getRotinaUsuarioDono().getIdRotina());
        Grupo grupo = grupoService.loadByRotina(solicitacaoDTO.getRotinaMotorista());
        Solicitacao solicitacaoParaPersistir = new Solicitacao(usuarioDono, usuarioAlvo, rotinaPassageiro, grupo);
        return solicitacaoService.save(solicitacaoParaPersistir);
    }
    
    @PutMapping
    public Solicitacao update(@RequestBody Solicitacao solicitacao) {
        return solicitacaoService.update(solicitacao);
    }
    
    @DeleteMapping(value = "/{idSolicitacao}")
    public void remove(@PathVariable Long idSolicitacao) {
        Solicitacao solicitacao = solicitacaoService.loadById(idSolicitacao);
        solicitacaoService.remove(solicitacao);
    }
    
    @GetMapping(value = "/pendentes")
    public List<Solicitacao> solicitacoesPendentes(@AuthenticationPrincipal User user) {
        Usuario usuario = usuarioService.findByIdAutorizacao(user.getUsername());
        return solicitacaoService.loadByUsuarioAlvo(usuario);
    }
    
    @PostMapping(value = "/aceitar")
    public void aceitarSolicitacao(@RequestBody Solicitacao solicitacao, @AuthenticationPrincipal User user) throws CaronasException {
        Usuario usuarioAlvo = usuarioService.findByIdAutorizacao(user.getUsername());
        solicitacao.setUsuarioAlvo(usuarioAlvo);
        solicitacao.setUsuarioDono(usuarioService.findByIdAutorizacao(solicitacao.getUsuarioDono().getIdAutorizacao()));
        solicitacaoService.aceitarSolicitacao(solicitacao);
        notificacaoService.enviarNotificacaoDeAceite(solicitacao.getUsuarioDono(), solicitacao.getGrupo().getNome());
    }
    
}
