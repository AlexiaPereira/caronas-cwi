package br.com.crescer.caronas.controller;

import br.com.crescer.caronas.entity.Notificacao;
import br.com.crescer.caronas.entity.Usuario;
import br.com.crescer.caronas.service.NotificacaoService;
import br.com.crescer.caronas.service.UsuarioService;
import br.com.crescer.caronas.service.exceptions.CaronasException;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;
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
 * @author chris
 */
@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {

    @Autowired
    NotificacaoService notificacaoService;
    
    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public List<Notificacao> findAllByUsuario(@AuthenticationPrincipal User user) {
        Usuario usuario = usuarioService.findByIdAutorizacao(user.getUsername());
        return notificacaoService.findAllByUsuario(usuario);
    }
    
    @DeleteMapping
    public void removeByUser(@AuthenticationPrincipal User user) throws CaronasException{
        notificacaoService.clearByUser(usuarioService.findByIdAutorizacao(user.getUsername()));
    }
}