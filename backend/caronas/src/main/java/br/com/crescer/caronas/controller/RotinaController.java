package br.com.crescer.caronas.controller;

import br.com.crescer.caronas.dto.DistanciaRotinaDTO;
import br.com.crescer.caronas.entity.Rotina;
import br.com.crescer.caronas.entity.Usuario;
import br.com.crescer.caronas.service.RotinaService;
import br.com.crescer.caronas.service.UsuarioService;
import br.com.crescer.caronas.service.ValidarDistanciaService;
import java.text.ParseException;
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

    ValidarDistanciaService validarDistanciaService;

    @GetMapping
    public Iterable<Rotina> findAll() {
        return rotinaService.findAll();
    }

    @GetMapping(value = "/usuario")
    public List<Rotina> findByUsuario(@AuthenticationPrincipal User user) {
        Usuario usuario = usuarioService.findByIdAutorizacao(user.getUsername());
        return rotinaService.findByUsuario(usuario);
    }

    @GetMapping(value = "/usuario/passageiro/{passageiro}")
    public List<Rotina> findByUsuarioAndPassageiro(@PathVariable Boolean passageiro, @AuthenticationPrincipal User user) {
        Usuario usuario = usuarioService.findByIdAutorizacao(user.getUsername());
        return rotinaService.findByUsuarioAndPassageiro(usuario, passageiro);
    }

    @PostMapping
    public Rotina save(@RequestBody Rotina rotina, @AuthenticationPrincipal User user) {
        Usuario donoDaRotina = usuarioService.findByIdAutorizacao(user.getUsername());
        rotina.setUsuario(donoDaRotina);
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

    @GetMapping(value = "/match/horario/{idRotina}")
    public List<Rotina> matchHorarioEVagas(@PathVariable Long idRotina) throws ParseException {
        Rotina rotina = rotinaService.loadById(idRotina);
        return rotinaService.filtrarRotinas(rotina);
    }

    @PostMapping(value = "/match/distancia/{idRotina}")
    public List<Rotina> matchDistancia(@PathVariable Long idRotina, @RequestBody List<DistanciaRotinaDTO> distanciaRotinaMotoristas) {
        validarDistanciaService = new ValidarDistanciaService();
        Rotina rotina = rotinaService.loadById(idRotina);
        return validarDistanciaService.validarRotinasCompativeis(rotina, distanciaRotinaMotoristas);
    }
}
