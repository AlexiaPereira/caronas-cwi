package br.com.crescer.caronas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author alexia.pereira
 */
@Entity
@Table(name = "GRUPO")
public class Grupo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_GRUPO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GRUPO")
    @SequenceGenerator(
            name = "SEQ_GRUPO",
            sequenceName = "SEQ_GRUPO",
            allocationSize = 1
    )
    private Long idGrupo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NOME")
    private String nome;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ROTINA")
    private Rotina rotina;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupo")
    @JsonBackReference
    private List<UsuarioGrupo> usuarioGrupoList;

    public Grupo() {
    }

    public Grupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Grupo(Long idGrupo, String nome) {
        this.idGrupo = idGrupo;
        this.nome = nome;
    }

    public Grupo(String nome) {
        this.nome = nome;
    }

    public Grupo(String nome, Rotina rotina) {
        this.nome = nome;
        this.rotina = rotina;
        this.usuarioGrupoList = new ArrayList<>();
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
