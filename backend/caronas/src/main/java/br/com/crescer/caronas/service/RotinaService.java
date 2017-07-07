package br.com.crescer.caronas.Service;

import br.com.crescer.caronas.entity.Rotina;
import br.com.crescer.caronas.repository.RotinaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chris
 */
@Service
public class RotinaService {

    @Autowired
    RotinaRepository rotinaRepository;

    public Iterable<Rotina> findAll() {
        return rotinaRepository.findAll();
    }

    public Rotina save(Rotina rotina) {
        return rotinaRepository.save(rotina);
    }

    public Rotina update(Rotina rotina) {
        return rotinaRepository.save(rotina);
    }

    public void remove(Rotina rotina) {
        rotinaRepository.delete(rotina);
    }

    public Rotina loadById(Long id) {
        return rotinaRepository.findOne(id);
    }
    
    public List<Rotina> findByPassageiro (Boolean bool) {
        return rotinaRepository.findByPassageiro(bool);
    }
}