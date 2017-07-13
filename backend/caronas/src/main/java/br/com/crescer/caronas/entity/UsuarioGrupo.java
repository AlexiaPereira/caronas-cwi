package br.com.crescer.caronas.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIOGRUPO")
    @SequenceGenerator(
            name = "SEQ_USUARIOGRUPO",
            sequenceName = "SEQ_USUARIOGRUPO",
            allocationSize = 1
    )
    private Long idUsuarioGrupo;

    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Usuario usuario;

    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "ID_GRUPO", referencedColumnName = "ID_GRUPO")
    @ManyToOne
    private Grupo grupo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "DATA_ENTRADA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEntrada;

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

    public UsuarioGrupo(Usuario usuario, Grupo grupo, Date dataEntrada) {
        this.usuario = usuario;
        this.grupo = grupo;
        this.dataEntrada = dataEntrada;
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

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

}
