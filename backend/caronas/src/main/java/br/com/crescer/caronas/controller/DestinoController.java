package br.com.crescer.caronas.Controller;

import br.com.crescer.caronas.Service.DestinoService;
import br.com.crescer.caronas.entity.Destino;
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
@RequestMapping("/destinos")
public class DestinoController {

    @Autowired
    DestinoService destinoService;

    @GetMapping
    public Iterable<Destino> findAll() {
        return destinoService.findAll();
    }
    
    @GetMapping(value = "/{id}")
    public Destino loadById(@PathVariable Long id) {
        return destinoService.loadById(id);
    }
    
    @PostMapping
    public Destino save(@RequestBody Destino destino) {
        return destinoService.save(destino);
    }

    @PutMapping
    public Destino update(@RequestBody Destino destino) {
        return destinoService.update(destino);
    }

    @DeleteMapping(value = "/{id}")
    public void remove(@PathVariable Long id) {
        Destino destino = destinoService.loadById(id);
        destinoService.remove(destino);
    }    
}
