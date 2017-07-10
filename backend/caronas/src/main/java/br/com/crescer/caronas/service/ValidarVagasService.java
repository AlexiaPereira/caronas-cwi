package br.com.crescer.caronas.service;

import br.com.crescer.caronas.entity.DiaSemana;
import br.com.crescer.caronas.entity.Rotina;
import br.com.crescer.caronas.entity.RotinaDiaSemana;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author alexia.pereira
 */
public class ValidarVagasService {
    
    public List<Rotina> validarVagas(Rotina rotinaPrincipal, List<Rotina> rotinasValidadasPorHorario) {
        
        List<String> diasDaRotinaPrincipal = rotinaPrincipal.getRotinaDiaSemanaList()
                .stream()
                .map(RotinaDiaSemana::getDiaSemana)
                .map(DiaSemana::getNome)
                .collect(toList());

        return rotinasValidadasPorHorario.stream().filter(e -> {
            return e.getRotinaDiaSemanaList().stream().filter(rds
                    -> diasDaRotinaPrincipal.contains(rds.getDiaSemana().getNome())
                    && rds.getVagasDisponiveis() > 0).collect(toList()).size() > 0;
        }).collect(toList());

    }
}
