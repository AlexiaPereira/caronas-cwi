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
public class ValidarDistanciaService {

    @Autowired
    RotinaRepository rotinaRepository;
    
    @Autowired
    RotinaDiaSemanaService rotinaDiaSemanaService;

    
    public List<Rotina> validarRotinasCompativeis (Rotina rotinaPassageiro, List<DistanciaRotina> distanciaRotinasMotorista) {
        return verificarDistancias(rotinaPassageiro, distanciaRotinasMotorista);
    }
    
    public List<Rotina> verificarDistancias (Rotina rotinaPrincipal, List<DistanciaRotina> distanciaRotina)  {
        List<Rotina> rotinasComDistanciaAceitavel = new ArrayList<>();
        distanciaRotina.forEach((rotina) -> {
            BigDecimal distanciaComCaroneiro = rotina.getDistancia().add(rotinaPrincipal.getDistancia());
            BigDecimal distanciaPassageiroAteCWI = rotinaPrincipal.getDistancia();
            BigDecimal diferencaDePercurso = distanciaComCaroneiro.subtract(distanciaPassageiroAteCWI);
            BigDecimal desvioPermitido = new BigDecimal("1000");
            if ((desvioPermitido.compareTo(diferencaDePercurso)) == 1) {
                rotinasComDistanciaAceitavel.add(rotina.getRotina());
            }
        });
        return rotinasComDistanciaAceitavel;
    }
}
