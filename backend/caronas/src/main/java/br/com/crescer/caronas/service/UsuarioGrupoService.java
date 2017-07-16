package br.com.crescer.caronas.service;

import br.com.crescer.caronas.entity.Grupo;
import br.com.crescer.caronas.entity.Notificacao;
import br.com.crescer.caronas.entity.Rotina;
import br.com.crescer.caronas.entity.RotinaDiaSemana;
import br.com.crescer.caronas.entity.Usuario;
import br.com.crescer.caronas.entity.UsuarioGrupo;
import br.com.crescer.caronas.repository.UsuarioGrupoRepository;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 *
 *
 * @author chris
 *
 */
@Service

public class UsuarioGrupoService {

    @Autowired
    UsuarioGrupoRepository usuarioGrupoRepository;

    @Autowired
    NotificacaoService notificacaoService;

    @Autowired
    RotinaService rotinaService;

    ValidarVagasService validarVagasService;
    ValidarHorarioService validarHorarioService;

    public Iterable<UsuarioGrupo> findAll() {
        return usuarioGrupoRepository.findAll();
    }

    public UsuarioGrupo save(UsuarioGrupo usuarioGrupo) {

        if (usuarioEstaNoGrupo(usuarioGrupo, usuarioGrupo.getGrupo())) {
            throw new RuntimeException("O usuário já está nesse grupo");
        }

        String conteudoNotificacao = String.format("%s entrou no grupo %s", usuarioGrupo.getUsuario().getNome(), usuarioGrupo.getGrupo().getNome());
        Notificacao notificacao = new Notificacao(conteudoNotificacao, null);
        notificacaoService.enviarNotificacaoTodosUsuariosDoGrupo(usuarioGrupo.getGrupo(), notificacao);
        return usuarioGrupoRepository.save(usuarioGrupo);

    }

    public UsuarioGrupo update(UsuarioGrupo usuarioGrupo) {
        return usuarioGrupoRepository.save(usuarioGrupo);
    }

    public void remove(UsuarioGrupo usuarioGrupo) throws ParseException {
        if (!this.usuarioEstaNoGrupo(usuarioGrupo, usuarioGrupo.getGrupo())) {
            throw new RuntimeException("Usuário não está no grupo");
        }

        Rotina rotinaUsuarioQueEstaSaindo = this.buscarRotinaUsuario(usuarioGrupo.getUsuario(), usuarioGrupo.getGrupo().getRotina());
        rotinaUsuarioQueEstaSaindo.setDisponivel(true);
        this.descontarVagas(rotinaUsuarioQueEstaSaindo, usuarioGrupo.getGrupo().getRotina(), 1);

        String conteudoNotificacao = String.format("'%s': %s removeu %s do grupo",
                usuarioGrupo.getGrupo().getNome(), usuarioGrupo.getGrupo().getRotina().getUsuario().getNome(),
                usuarioGrupo.getUsuario().getNome());

        String conteudoNotificaoEspecifica = String.format("%s removeu você do grupo %s",
                usuarioGrupo.getGrupo().getRotina().getUsuario().getNome(), usuarioGrupo.getGrupo().getNome());
        Notificacao notificacaoEspecifica = new Notificacao(conteudoNotificaoEspecifica, usuarioGrupo.getUsuario());
        notificacaoService.save(notificacaoEspecifica);

        Notificacao notificacao = new Notificacao(conteudoNotificacao, null);
        usuarioGrupoRepository.delete(usuarioGrupo);
        notificacaoService.enviarNotificacaoTodosUsuariosDoGrupo(usuarioGrupo.getGrupo(), notificacao);
    }

    public UsuarioGrupo loadById(Long id) {
        return usuarioGrupoRepository.findOne(id);
    }

    public List<UsuarioGrupo> findByUsuario(Usuario usuario) {
        return usuarioGrupoRepository.findByUsuario(usuario);
    }

    public void deleteByGrupo(Grupo grupo) {
        usuarioGrupoRepository.deleteByGrupo(grupo);
    }

    public UsuarioGrupo findByUsuarioAndGrupo(Usuario usuario, Grupo grupo) {
        return usuarioGrupoRepository.findByUsuarioAndGrupo(usuario, grupo);
    }

    public List<UsuarioGrupo> findByGrupo(Grupo grupo) {
        return usuarioGrupoRepository.findByGrupo(grupo);
    }

    public boolean usuarioEstaNoGrupo(UsuarioGrupo usuarioGrupo, Grupo grupo) {

        List<UsuarioGrupo> usuariosDoGrupo = usuarioGrupoRepository.findByGrupo(usuarioGrupo.getGrupo());
        return usuariosDoGrupo
                .stream()
                .map(UsuarioGrupo::getUsuario)
                .collect(toList())
                .contains(usuarioGrupo.getUsuario());
    }

    public void descontarVagas(Rotina rotinaPrincipal, Rotina rotinaMatchMotorista, int fator) {
        validarVagasService = new ValidarVagasService();
        List<RotinaDiaSemana> diasDaSemanaComMatch
                = validarVagasService.filtrarDiaSemana(
                        validarVagasService.gerarDiasRotinaPrincipal(rotinaPrincipal), rotinaMatchMotorista);
        rotinaMatchMotorista.getRotinaDiaSemanaList()
                .forEach(rotinaDiaSemana -> {
                    rotinaDiaSemana.setRotina(rotinaMatchMotorista);
                    if (diasDaSemanaComMatch.contains(rotinaDiaSemana)) {
                        rotinaDiaSemana.setVagasDisponiveis(rotinaDiaSemana.getVagasDisponiveis() + fator);
                    }
                });

    }

    private Rotina buscarRotinaUsuario(Usuario usuario, Rotina rotina) throws ParseException {
        List<Rotina> rotinasDoMotorista = new ArrayList<>();
        rotinasDoMotorista.add(rotina);

        List<Rotina> possibilidades = new ArrayList<>();

        validarHorarioService = new ValidarHorarioService();

        List<Rotina> rotinasDoUsuario = rotinaService.findByUsuarioAndPassageiroAndNaoDisponivel(usuario, true);
        for (Rotina rotinaDoUsuario : rotinasDoUsuario) {
            List<Rotina> matchHorario = validarHorarioService
                    .buscarRotinasDeMotoristasComHorariosCompativeis(rotinaDoUsuario, rotinasDoMotorista);
            if (matchHorario.size() > 0) {
                possibilidades.add(rotinaDoUsuario);
            }
        }
        return possibilidades.get(0);
    }

}
