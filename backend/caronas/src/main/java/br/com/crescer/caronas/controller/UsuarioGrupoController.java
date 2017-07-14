package br.com.crescer.caronas.controller;

import br.com.crescer.caronas.entity.Grupo;
import br.com.crescer.caronas.entity.Usuario;
import br.com.crescer.caronas.entity.UsuarioGrupo;
import br.com.crescer.caronas.service.GrupoService;
import br.com.crescer.caronas.service.UsuarioGrupoService;
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
@RequestMapping("/usuarios-grupo")
public class UsuarioGrupoController {

    @Autowired
    UsuarioGrupoService usuarioGrupoService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    GrupoService grupoService;

    @GetMapping
    public Iterable<UsuarioGrupo> findAll() {
        return usuarioGrupoService.findAll();
    }

    @GetMapping(value = "/{id}")
    public UsuarioGrupo loadById(@PathVariable Long id) {
        return usuarioGrupoService.loadById(id);
    }

    @PostMapping
    public UsuarioGrupo save(@RequestBody UsuarioGrupo usuarioGrupo) {
        return usuarioGrupoService.save(usuarioGrupo);
    }

    @PutMapping
    public UsuarioGrupo update(@RequestBody UsuarioGrupo usuarioGrupo) {
        return usuarioGrupoService.update(usuarioGrupo);
    }

    @DeleteMapping(value = "/{idUsuarioGrupo}")
    public void remove(@PathVariable Long idUsuarioGrupo) {
        UsuarioGrupo usuarioGrupo = usuarioGrupoService.loadById(idUsuarioGrupo);
        usuarioGrupoService.remove(usuarioGrupo);
    }

    @DeleteMapping(value = "/remover/{idGrupo}")
    public void remove(@PathVariable Long idGrupo, @AuthenticationPrincipal User user) {
        Grupo grupo = grupoService.loadById(idGrupo);
        Usuario usuario = usuarioService.findByIdAutorizacao(user.getUsername());
        UsuarioGrupo usuarioGrupoCorreto = usuarioGrupoService.findByUsuarioAndGrupo(usuario, grupo);
        usuarioGrupoService.remove(usuarioGrupoCorreto);
    }

}
