package br.com.crescer.caronas.service;

import br.com.crescer.caronas.entity.Rotina;
import br.com.crescer.caronas.entity.RotinaDiaSemana;
import br.com.crescer.caronas.entity.Usuario;
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
    
    @Autowired
    RotinaDiaSemanaService rotinaDiaSemanaService;

    public Iterable<Rotina> findAll() {
        return rotinaRepository.findAll();
    }

    public Rotina save(Rotina rotina) {
        List<RotinaDiaSemana> diasSemana = rotina.getRotinaDiaSemanaList();
        for (RotinaDiaSemana rotinaDiaSemana : diasSemana) {
            rotinaDiaSemana.setRotina(rotina);
        }
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
    
    //public List<Rotina> findByUsuario (Usuario usuario) {
    //    return rotinaRepository.findByUsuario(usuario);
    //}
    
    
}