package br.com.crescer.caronas.service;

import br.com.crescer.caronas.entity.DiaSemana;
import br.com.crescer.caronas.entity.Rotina;
import br.com.crescer.caronas.entity.RotinaDiaSemana;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author alexia.pereira;
 */
public class ValidarHorarioService {

    public List<Rotina> buscarRotinasDeMotoristasComHorariosCompativeis(Rotina rotina, List<Rotina> rotinasDeMotoristas) throws ParseException {

        List<Rotina> rotinasComDiasDaSemanaCompativeis = this.buscarDiasDaSemanaCompativeis(rotina, rotinasDeMotoristas);
        return this.validarHorarios(rotina, rotinasComDiasDaSemanaCompativeis);
    }

    public List<Rotina> buscarDiasDaSemanaCompativeis(Rotina rotinaPrincipal, List<Rotina> rotinasDeMotoristas) {

        List<Rotina> rotinasComDiasDaSemanaCompativeis = new ArrayList<>();

        List<String> diasDaRotinaPrincipal = rotinaPrincipal.getRotinaDiaSemanaList()
                .stream()
                .map(RotinaDiaSemana::getDiaSemana)
                .map(DiaSemana::getNome)
                .collect(toList());

        for (Rotina rotinaAtual : rotinasDeMotoristas) {
            List<String> diasDaRotinaAtual = rotinaAtual.getRotinaDiaSemanaList()
                    .stream()
                    .map(RotinaDiaSemana::getDiaSemana)
                    .map(DiaSemana::getNome)
                    .collect(toList());

            if (CollectionUtils.containsAny(diasDaRotinaAtual, diasDaRotinaPrincipal)) {
                rotinasComDiasDaSemanaCompativeis.add(rotinaAtual);
            }
        }
        return rotinasComDiasDaSemanaCompativeis;
    }

    public List<Rotina> validarHorarios(Rotina rotinaPrincipal, List<Rotina> rotinasFiltradasPorDias) throws ParseException {
        List<Rotina> rotinasComHorariosCompativeis = new ArrayList<>();

        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");

        LocalDateTime horarioRotinaPrincipal
                = LocalDateTime.ofInstant(formatador.parse(formatador.format(rotinaPrincipal.getHorario())).toInstant(),
                        ZoneId.systemDefault());

        for (Rotina rotinaAtual : rotinasFiltradasPorDias) {
            LocalDateTime horarioRotinaLista
                    = LocalDateTime.ofInstant(formatador.parse(formatador.format(rotinaAtual.getHorario())).toInstant(),
                            ZoneId.systemDefault());
            long diferencaEmMinutos = horarioRotinaPrincipal.until(horarioRotinaLista, ChronoUnit.MINUTES);

            if (diferencaEmMinutos >= -30 && diferencaEmMinutos <= 30) {
                rotinasComHorariosCompativeis.add(rotinaAtual);
            }
        }

        return rotinasComHorariosCompativeis;
    }
}
