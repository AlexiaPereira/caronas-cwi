package br.com.crescer.caronas.Controller;

import br.com.crescer.caronas.Service.RotinaService;
import br.com.crescer.caronas.Service.UsuarioService;
import br.com.crescer.caronas.entity.Rotina;
import br.com.crescer.caronas.entity.Usuario;
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
 * @author chris
 */
@RestController
@RequestMapping("/rotinas")
public class RotinaController {

    @Autowired
    RotinaService rotinaService;
    
    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public Iterable<Rotina> findAll() {
        return rotinaService.findAll();
    }
    
    //@GetMapping
    //public List<Rotina> findByUsuario(@AuthenticationPrincipal User user) {
    //    Usuario usuario = usuarioService.findByIdAutorizacao(user.getUsername());
    //    return rotinaService.findByUsuario(usuario);
    //}
    
    @PostMapping
    public Rotina save(@RequestBody Rotina rotina) {
        return rotinaService.save(rotina);
    }

    @PutMapping
    public Rotina update(@RequestBody Rotina rotina) {
        return rotinaService.update(rotina);
    }

    @DeleteMapping(value = "/{idRotina}")
    public void remove(@PathVariable Long idRotina) {
        Rotina rotina = rotinaService.loadById(idRotina);
        rotinaService.remove(rotina);
    }    
    
}