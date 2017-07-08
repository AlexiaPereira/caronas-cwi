package br.com.crescer.caronas.Controller;

import br.com.crescer.caronas.Service.UsuarioGrupoService;
import br.com.crescer.caronas.entity.UsuarioGrupo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @DeleteMapping(value = "/{id}")
    public void remove(@PathVariable Long id) {
        UsuarioGrupo usuarioGrupo = usuarioGrupoService.loadById(id);
        usuarioGrupoService.remove(usuarioGrupo);
    }    
}
