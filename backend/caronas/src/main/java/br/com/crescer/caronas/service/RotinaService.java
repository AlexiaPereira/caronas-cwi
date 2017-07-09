package br.com.crescer.caronas.Service;

import br.com.crescer.caronas.entity.DiaSemana;
import br.com.crescer.caronas.entity.Rotina;
import br.com.crescer.caronas.entity.RotinaDiaSemana;
import br.com.crescer.caronas.repository.RotinaRepository;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    public List<Rotina> findByPassageiro(Boolean bool) {
        return rotinaRepository.findByPassageiro(bool);
    }

    //public List<Rotina> findByUsuario (Usuario usuario) {
    //    return rotinaRepository.findByUsuario(usuario);
    //}
    
    public List<Rotina> buscarRotinasDeMotoristasComHorariosCompativeis(Rotina rotina) {
        List<Rotina> rotinasDeMotoristas = this.findByPassageiro(false);
        List<Rotina> rotinasComDiasDaSemanaCompativeis = this.buscarDiasDaSemanaCompativeis(rotina, rotinasDeMotoristas);
        return this.validarHorarios(rotina, rotinasComDiasDaSemanaCompativeis);
    }

    private List<Rotina> buscarDiasDaSemanaCompativeis(Rotina rotinaPrincipal, List<Rotina> rotinasDeMotoristas) {

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

    private List<Rotina> validarHorarios(Rotina rotinaPrincipal, List<Rotina> rotinasFiltradasPorDias) {
        List<Rotina> rotinasComHorariosCompativeis = new ArrayList<>();

        DateTime horarioRotinaPrincipal = new DateTime(rotinaPrincipal.getHorario());

        for (Rotina rotinaAtual : rotinasFiltradasPorDias) {
            DateTime horarioRotinaDaLista = new DateTime(rotinaAtual.getHorario());
            Period diferençaEntreHorarios = new Period(horarioRotinaPrincipal, horarioRotinaDaLista);

            if (diferençaEntreHorarios.getMinutes() >= -30 && diferençaEntreHorarios.getMinutes() <= 30) {
                rotinasComHorariosCompativeis.add(rotinaAtual);
            }
        }

        return rotinasComHorariosCompativeis;
    }

}
