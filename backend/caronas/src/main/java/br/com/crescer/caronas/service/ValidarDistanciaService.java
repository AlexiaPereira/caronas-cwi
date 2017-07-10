package br.com.crescer.caronas.service;

import br.com.crescer.caronas.dto.DistanciaRotina;
import br.com.crescer.caronas.entity.DiaSemana;
import br.com.crescer.caronas.entity.Rotina;
import br.com.crescer.caronas.entity.RotinaDiaSemana;
import br.com.crescer.caronas.repository.RotinaRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chris
 */
@Service
public class ValidarDistanciaService {

    @Autowired
    RotinaRepository rotinaRepository;
    
    @Autowired
    RotinaDiaSemanaService rotinaDiaSemanaService;

    
    public List<Rotina> validarRotinasCompativeis (Rotina rotinaPassageiro, List<DistanciaRotina> distanciaRotinasMotorista) {
        List<Rotina> rotinasComMatch = new ArrayList<>();
            rotinasComMatch = verificarDistancias(rotinaPassageiro, distanciaRotinasMotorista);
        return rotinasComMatch;
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
    
    public void descontarVagas(Rotina rotinaPrincipal, Rotina rotinaMatchMotorista) {
        List<RotinaDiaSemana> diasDaSemanaComMatch = filtrarDiaSemana(converterParaListaDeString(rotinaPrincipal), rotinaMatchMotorista);
        diasDaSemanaComMatch.forEach((diaSemanaComMatch) -> {
            diaSemanaComMatch.setVagasDisponiveis(diaSemanaComMatch.getVagasDisponiveis()-1);
        });
    }
    
    public List<RotinaDiaSemana> filtrarDiaSemana(List<String> diasDaRotinaPrincipal, Rotina e) {
        return e.getRotinaDiaSemanaList().stream().filter(rds
                -> diasDaRotinaPrincipal.contains(rds.getDiaSemana().getNome()))
                .collect(toList());
    }
    
    public List<String> converterParaListaDeString (Rotina rotinaPrincipal) {
        List<String> listaDiaSemana = new ArrayList<>();
         rotinaPrincipal.getRotinaDiaSemanaList().forEach((diaSemana) ->{
             String stringDiaSemana = diaSemana.getDiaSemana().toString();
             listaDiaSemana.add(stringDiaSemana);
         });
         return listaDiaSemana;
    }
}