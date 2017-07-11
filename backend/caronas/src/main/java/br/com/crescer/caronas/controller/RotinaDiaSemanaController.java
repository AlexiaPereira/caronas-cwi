package br.com.crescer.caronas.controller;

import br.com.crescer.caronas.service.RotinaDiaSemanaService;
import br.com.crescer.caronas.entity.RotinaDiaSemana;
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
@RequestMapping("/rotina-dia-semana")
public class RotinaDiaSemanaController {

    @Autowired
    RotinaDiaSemanaService rotinaDiaSemanaService;

    @GetMapping
    public Iterable<RotinaDiaSemana> findAll() {
        return rotinaDiaSemanaService.findAll();
    }
    
    @GetMapping(value = "/{id}")
    public RotinaDiaSemana loadById(@PathVariable Long id) {
        return rotinaDiaSemanaService.loadById(id);
    }
    
    @PostMapping
    public RotinaDiaSemana save(@RequestBody RotinaDiaSemana rotinaDiaSemana) {
        return rotinaDiaSemanaService.save(rotinaDiaSemana);
    }

    @PutMapping
    public RotinaDiaSemana update(@RequestBody RotinaDiaSemana rotinaDiaSemana) {
        return rotinaDiaSemanaService.update(rotinaDiaSemana);
    }

    @DeleteMapping(value = "/{id}")
    public void remove(@PathVariable Long id) {
        RotinaDiaSemana rotinaDiaSemana = rotinaDiaSemanaService.loadById(id);
        rotinaDiaSemanaService.remove(rotinaDiaSemana);
    }    
}
