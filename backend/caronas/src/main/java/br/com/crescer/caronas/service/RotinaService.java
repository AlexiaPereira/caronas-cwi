package br.com.crescer.caronas.service;

import br.com.crescer.caronas.entity.Grupo;
import br.com.crescer.caronas.entity.Rotina;
import br.com.crescer.caronas.entity.RotinaDiaSemana;
import br.com.crescer.caronas.entity.Usuario;
import br.com.crescer.caronas.entity.UsuarioGrupo;
import br.com.crescer.caronas.repository.RotinaRepository;
import java.text.ParseException;
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

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    GrupoService grupoService;

    @Autowired
    UsuarioGrupoService usuarioGrupoService;

    private ValidarHorarioService validarHorarioService;
    private ValidarVagasService validarVagasService;

    public Iterable<Rotina> findAll() {
        return rotinaRepository.findAll();
    }

    public Rotina save(Rotina rotina) {
        List<RotinaDiaSemana> diasSemana = rotina.getRotinaDiaSemanaList();
        rotina.setDisponivel(true);

        for (RotinaDiaSemana rotinaDiaSemana : diasSemana) {
            rotinaDiaSemana.setRotina(rotina);
        }

        if (rotina.getPassageiro()) {
            return rotinaRepository.save(rotina);
        }

        rotina = rotinaRepository.save(rotina);
        Grupo grupoComEssaRotina = new Grupo("Grupo de " + rotina.getUsuario().getNome(), rotina);
        grupoService.save(grupoComEssaRotina);
        return rotina;
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

    public List<Rotina> findByUsuarioAndPassageiroAndDisponivel(Usuario usuario, boolean passageiro) {
        return rotinaRepository.findByUsuarioAndPassageiroAndDisponivel(usuario, passageiro, true);
    }

    public List<Rotina> matchHorarios(Rotina rotina) throws ParseException {
        this.validarHorarioService = new ValidarHorarioService();
        List<Rotina> rotinasDeMotoristas = this.findByPassageiro(false);

        List<Rotina> auxiliarMotorista = new ArrayList<>();

        rotinasDeMotoristas.stream()
                .filter(motorista -> motorista.getUsuario() == rotina.getUsuario())
                .forEach(auxiliarMotorista::add);

        rotinasDeMotoristas.stream().forEach(motorista -> {
            List<UsuarioGrupo> listaUsuarioGrupo = grupoService.loadByRotina(motorista).getUsuarioGrupoList();
            listaUsuarioGrupo
                    .stream()
                    .filter(usuarioGrupo -> usuarioGrupoService.usuarioEstaNoGrupo(usuarioGrupo))
                    .map(UsuarioGrupo::getGrupo)
                    .map(Grupo::getRotina)
                    .forEach(auxiliarMotorista::add);
        });

        rotinasDeMotoristas.removeAll(auxiliarMotorista);

        return validarHorarioService.buscarRotinasDeMotoristasComHorariosCompativeis(rotina, rotinasDeMotoristas);
    }

    public List<Rotina> filtrarRotinas(Rotina rotina) throws ParseException {
        this.validarVagasService = new ValidarVagasService();
        List<Rotina> rotinasValidadasPorHorario = this.matchHorarios(rotina);
        return validarVagasService.validarVagas(rotina, rotinasValidadasPorHorario);
    }
}
