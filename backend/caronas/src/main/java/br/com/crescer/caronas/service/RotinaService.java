package br.com.crescer.caronas.Service;

import br.com.crescer.caronas.dto.DistanciaRotina;
import br.com.crescer.caronas.entity.Rotina;
import br.com.crescer.caronas.entity.RotinaDiaSemana;
import br.com.crescer.caronas.entity.Usuario;
import br.com.crescer.caronas.repository.RotinaRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    
    public List<List<Rotina>> validarRotinasCompativeis (List<Rotina> rotinasPassageiro, List<List<DistanciaRotina>> distanciaRotinasList) {
        List<List<Rotina>> listaRotinasMotoristaAceitavelParaCadaRotinaPassageiro = new ArrayList<>();
        List<Rotina> rotinasMotoristaParaUmaRotinaPassageiro = new ArrayList<>();
        int i = 0;
        for(List<DistanciaRotina> distanciaRotina: distanciaRotinasList) {
            rotinasMotoristaParaUmaRotinaPassageiro = verificarDistancias(rotinasPassageiro.get(i), distanciaRotina);
            //falta descontar vaga no dia da semana
            listaRotinasMotoristaAceitavelParaCadaRotinaPassageiro.add(rotinasMotoristaParaUmaRotinaPassageiro);
            rotinasMotoristaParaUmaRotinaPassageiro.clear();
            i++;
        };
        
        return listaRotinasMotoristaAceitavelParaCadaRotinaPassageiro;
    }
    
    public List<Rotina> verificarDistancias (Rotina rotinaPrincipal, List<DistanciaRotina> distanciaRotina)  {
        List<Rotina> rotinasComDistanciaAceitavel = new ArrayList<>();
        distanciaRotina.forEach((rotina) -> {
            BigDecimal distanciaComCaroneiro = rotina.getDistancia().add(rotinaPrincipal.getDistancia());
            BigDecimal distanciaPassageiroAteCWI = rotinaPrincipal.getDistancia();
            if (distanciaComCaroneiro.compareTo(distanciaPassageiroAteCWI) <= 1000) {
                rotinasComDistanciaAceitavel.add(rotina.getRotina());
            }
        });
        return rotinasComDistanciaAceitavel;
    }
    
}