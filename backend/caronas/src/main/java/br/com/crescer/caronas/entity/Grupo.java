package br.com.crescer.caronas.entity;

import java.io.Serializable;
import java.lang.Long;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupo")
    private List<RotinaGrupo> rotinaGrupoList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
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

    @XmlTransient
    public List<RotinaGrupo> getRotinaGrupoList() {
        return rotinaGrupoList;
    }

    public void setRotinaGrupoList(List<RotinaGrupo> rotinaGrupoList) {
        this.rotinaGrupoList = rotinaGrupoList;
    }

    public List<UsuarioGrupo> getUsuarioGrupoList() {
        return usuarioGrupoList;
    }

    public void setUsuarioGrupoList(List<UsuarioGrupo> usuarioGrupoList) {
        this.usuarioGrupoList = usuarioGrupoList;
    }
}
