package br.com.crescer.caronas.service;

import br.com.crescer.caronas.entity.Rotina;
import br.com.crescer.caronas.entity.RotinaDiaSemana;
import br.com.crescer.caronas.entity.Usuario;
import br.com.crescer.caronas.repository.RotinaRepository;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    ValidarHorarioService validarHorarioService;

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

    public List<Rotina> findByUsuario(Usuario usuario) {
        return rotinaRepository.findByUsuario(usuario);
    }

    public List<Rotina> matchHorarios(Rotina rotina) throws ParseException {
        List<Rotina> rotinasDeMotoristas = this.findByPassageiro(false);
        return validarHorarioService.buscarRotinasDeMotoristasComHorariosCompativeis(rotina, rotinasDeMotoristas);
    }

}
