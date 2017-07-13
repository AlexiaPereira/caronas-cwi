package br.com.crescer.caronas.service;

import br.com.crescer.caronas.dto.DistanciaRotinaDTO;
import br.com.crescer.caronas.entity.Rotina;
import br.com.crescer.caronas.repository.RotinaRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author chris
 */
public class ValidarDistanciaService {

    @Autowired
    RotinaRepository rotinaRepository;

    @Autowired
    RotinaDiaSemanaService rotinaDiaSemanaService;

    public List<Rotina> validarRotinasCompativeis(Rotina rotinaPassageiro, List<DistanciaRotinaDTO> distanciaRotinasMotorista) {
        return verificarDistancias(rotinaPassageiro, distanciaRotinasMotorista);
    }

    public List<Rotina> verificarDistancias(Rotina rotinaPrincipal, List<DistanciaRotinaDTO> distanciaRotina) {
        List<Rotina> rotinasComDistanciaAceitavel = new ArrayList<>();
        distanciaRotina.forEach((rotina) -> {
            BigDecimal distanciaSemCaroneiro = rotina.getRotina().getDistancia();
            BigDecimal distanciaPassageiroAteCWI = rotinaPrincipal.getDistancia();
            BigDecimal distanciaComCaroneiro = rotina.getDistancia().add(distanciaPassageiroAteCWI);
            BigDecimal diferencaDePercurso = distanciaComCaroneiro.subtract(distanciaSemCaroneiro);
            BigDecimal desvioPermitido = new BigDecimal("4000");
            if ((desvioPermitido.compareTo(diferencaDePercurso)) == 1) {
                rotinasComDistanciaAceitavel.add(rotina.getRotina());
            }
        });
        return rotinasComDistanciaAceitavel;
    }
}
