package br.com.crescer.caronas.entity;

import java.io.Serializable;
import java.lang.Long;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alexia.pereira
 */
@Entity
@Table(name = "USUARIO_GRUPO")
public class UsuarioGrupo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO_GRUPO")
    private Long idUsuarioGrupo;

    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario usuario;

    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "ID_GRUPO", referencedColumnName = "ID_GRUPO")
    @ManyToOne
    private Grupo grupo;

    public UsuarioGrupo() {
    }

    public UsuarioGrupo(Long idUsuarioGrupo) {
        this.idUsuarioGrupo = idUsuarioGrupo;
    }

    public UsuarioGrupo(Long idUsuarioGrupo, Usuario usuario, Grupo grupo) {
        this.idUsuarioGrupo = idUsuarioGrupo;
        this.usuario = usuario;
        this.grupo = grupo;
    }

    public Long getIdUsuarioGrupo() {
        return idUsuarioGrupo;
    }

    public void setIdUsuarioGrupo(Long idUsuarioGrupo) {
        this.idUsuarioGrupo = idUsuarioGrupo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setIdUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}
