package br.com.crescer.caronas.controller;

import br.com.crescer.caronas.dto.SolicitacaoRotinaDTO;
import br.com.crescer.caronas.entity.Solicitacao;
import br.com.crescer.caronas.entity.Usuario;
import br.com.crescer.caronas.service.SolicitacaoService;
import br.com.crescer.caronas.service.UsuarioService;
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
    UsuarioService usuarioService;

    @GetMapping
    public Iterable<Solicitacao> findAll() {
        return solicitacaoService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Solicitacao loadById(@PathVariable Long id) {
        return solicitacaoService.loadById(id);
    }

    @PostMapping
    public Solicitacao save(@RequestBody Solicitacao solicitacao, @AuthenticationPrincipal User user) {
        Usuario usuarioDono = usuarioService.findByIdAutorizacao(user.getUsername());
        Usuario usuarioAlvo = usuarioService.findByIdAutorizacao(solicitacao.getUsuarioAlvo().getIdAutorizacao());
        solicitacao.setUsuarioDono(usuarioDono);
        solicitacao.setUsuarioAlvo(usuarioAlvo);
        return solicitacaoService.save(solicitacao);
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

    @GetMapping(value = "/pendentes/{idUsuario}")
    public List<Solicitacao> solicitacoesPendentes(@PathVariable Long idUsuario) {
        Usuario usuario = usuarioService.loadById(idUsuario);
        return solicitacaoService.loadByUsuarioAlvo(usuario);
    }

    @PostMapping(value = "/aceitar")
    public void aceitarSolicitacao(@RequestBody SolicitacaoRotinaDTO solicitacaoDTO, @AuthenticationPrincipal User user) {
        Usuario usuarioAlvo = usuarioService.findByIdAutorizacao(user.getUsername());
        solicitacaoDTO.getSolicitacao().setUsuarioAlvo(usuarioAlvo);
        solicitacaoService.aceitarSolicitacao(solicitacaoDTO);
    }

}
