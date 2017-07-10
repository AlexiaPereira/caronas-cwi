package br.com.crescer.caronas.service;

import br.com.crescer.caronas.entity.DiaSemana;
import br.com.crescer.caronas.entity.Rotina;
import br.com.crescer.caronas.entity.RotinaDiaSemana;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author alexia.pereira
 */
public class ValidarVagasService {

    public List<Rotina> validarVagas(Rotina rotinaPrincipal, List<Rotina> rotinasValidadasPorHorario) {

        List<String> diasDaRotinaPrincipal = this.gerarDiasRotinaPrincipal(rotinaPrincipal);

        return rotinasValidadasPorHorario.stream().filter(rotina -> {
            return this.filtrarDiaSemana(diasDaRotinaPrincipal, rotina).size() > 0;
        }).collect(toList());

    }

    public List<RotinaDiaSemana> filtrarDiaSemana(List<String> diasDaRotinaPrincipal, Rotina rotina) {
        return rotina.getRotinaDiaSemanaList().stream().filter(rotinaDiaSemana
                -> diasDaRotinaPrincipal.contains(rotinaDiaSemana.getDiaSemana().getNome())
                && rotinaDiaSemana.getVagasDisponiveis() > 0)
                .collect(toList());
    }

    public List<String> gerarDiasRotinaPrincipal(Rotina rotinaPrincipal) {
        return rotinaPrincipal.getRotinaDiaSemanaList()
                .stream()
                .map(RotinaDiaSemana::getDiaSemana)
                .map(DiaSemana::getNome)
                .collect(toList());
    }

}
