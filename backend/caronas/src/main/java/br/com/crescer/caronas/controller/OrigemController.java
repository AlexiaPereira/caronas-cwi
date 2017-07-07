package br.com.crescer.caronas.Controller;

import br.com.crescer.caronas.Service.OrigemService;
import br.com.crescer.caronas.entity.Origem;
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
@RequestMapping("/origens")
public class OrigemController {

    @Autowired
    OrigemService origemService;

    @GetMapping
    public Iterable<Origem> findAll() {
        return origemService.findAll();
    }
    
    @PostMapping
    public Origem save(@RequestBody Origem origem) {
        return origemService.save(origem);
    }

    @PutMapping
    public Origem update(@RequestBody Origem origem) {
        return origemService.update(origem);
    }

    @DeleteMapping(value = "/{idOrigem}")
    public void remove(@PathVariable Long idOrigem) {
        Origem origem = origemService.loadById(idOrigem);
        origemService.remove(origem);
    }    
}
