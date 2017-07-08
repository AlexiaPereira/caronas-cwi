package br.com.crescer.caronas.Controller;

import br.com.crescer.caronas.Service.DiaSemanaService;
import br.com.crescer.caronas.entity.DiaSemana;
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
@RequestMapping("/dias-semana")
public class DiaSemanaController {

    @Autowired
    DiaSemanaService diaSemanaService;

    @GetMapping
    public Iterable<DiaSemana> findAll() {
        return diaSemanaService.findAll();
    }
    
    @GetMapping(value = "/{id}")
    public DiaSemana loadById(@PathVariable Long id) {
        return diaSemanaService.loadById(id);
    }
    
    @PostMapping
    public DiaSemana save(@RequestBody DiaSemana diaSemana) {
        return diaSemanaService.save(diaSemana);
    }

    @PutMapping
    public DiaSemana update(@RequestBody DiaSemana diaSemana) {
        return diaSemanaService.update(diaSemana);
    }

    @DeleteMapping(value = "/{id}")
    public void remove(@PathVariable Long id) {
        DiaSemana diaSemana = diaSemanaService.loadById(id);
        diaSemanaService.remove(diaSemana);
    }    
}
