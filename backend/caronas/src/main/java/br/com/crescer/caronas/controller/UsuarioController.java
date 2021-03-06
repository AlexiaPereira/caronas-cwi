package br.com.crescer.caronas.controller;

import br.com.crescer.caronas.entity.Usuario;
import br.com.crescer.caronas.service.NotificacaoService;
import br.com.crescer.caronas.service.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    NotificacaoService notificacaoService;

    @GetMapping
    public Iterable<Usuario> findAll() {
        return usuarioService.findAll();
    }

    @GetMapping(value = "/email")
    public Usuario loadByEmail(@RequestBody String email) {
        return usuarioService.findByEmail(email);
    }

    @GetMapping(value = "/{id}")
    public Usuario loadById(@PathVariable Long id) {
        return usuarioService.loadById(id);
    }

    @PostMapping
    public Usuario save(@RequestBody Usuario usuario) {
        return usuarioService.verificarUsuario(usuario);
    }

    @PutMapping
    public Usuario update(@RequestBody Usuario usuario) {
        return usuarioService.update(usuario);
    }

    @DeleteMapping(value = "/{idUsuario}")
    public void remove(@PathVariable Long idUsuario) {
        Usuario usuario = usuarioService.loadById(idUsuario);
        usuarioService.remove(usuario);
    }
}
