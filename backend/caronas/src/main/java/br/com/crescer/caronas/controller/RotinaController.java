package br.com.crescer.caronas.Controller;

import br.com.crescer.caronas.Service.RotinaService;
import br.com.crescer.caronas.entity.Rotina;
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
@RequestMapping("/rotinas")
public class RotinaController {

    @Autowired
    RotinaService rotinaService;

    @GetMapping
    public Iterable<Rotina> findAll() {
        return rotinaService.findAll();
    }
    
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
    
    //implementar get para obter rotinas de um usuário
}