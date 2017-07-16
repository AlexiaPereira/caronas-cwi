package br.com.crescer.caronas.dto;

import br.com.crescer.caronas.entity.Grupo;
import br.com.crescer.caronas.entity.Rotina;
import br.com.crescer.caronas.entity.UsuarioGrupo;
import java.util.List;

/**
 *
 * @author alexia.pereira
 */
public class UsuarioGrupoDTO {

    private Long idGrupo;
    private String nome;
    private Rotina rotina;
    private List<UsuarioGrupo> usuarioGrupoList;

    public UsuarioGrupoDTO() {
    }

    public UsuarioGrupoDTO(Grupo grupo, List<UsuarioGrupo> usuarioGrupoList) {
        this.idGrupo = grupo.getIdGrupo();
        this.nome = grupo.getNome();
        this.rotina = grupo.getRotina();
        this.usuarioGrupoList = usuarioGrupoList;
    }

    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Rotina getRotina() {
        return rotina;
    }

    public void setRotina(Rotina rotina) {
        this.rotina = rotina;
    }

    public List<UsuarioGrupo> getUsuarioGrupoList() {
        return usuarioGrupoList;
    }

    public void setUsuarioGrupoList(List<UsuarioGrupo> usuarioGrupoList) {
        this.usuarioGrupoList = usuarioGrupoList;
    }

}
