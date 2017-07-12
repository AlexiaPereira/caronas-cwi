package br.com.crescer.caronas.entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;

/**
 *
 * @author alexia.pereira
 */
@Entity
@Table(name = "SOLICITACAO")
public class Solicitacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SOLICITACAO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SOLICITACAO")
    @SequenceGenerator(
            name = "SEQ_SOLICITACAO",
            sequenceName = "SEQ_SOLICITACAO",
            allocationSize = 1
    )
    private Long idSolicitacao;

    @NotNull
    @JoinColumn(name = "ID_USUARIO_DONO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Usuario usuarioDono;

    @NotNull
    @JoinColumn(name = "ID_USUARIO_ALVO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Usuario usuarioAlvo;

    @NotNull
    @JoinColumn(name = "ID_ROTINA_USUARIO_DONO", referencedColumnName = "ID_ROTINA")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Rotina rotinaUsuarioDono;

    public Solicitacao() {
    }

    public Solicitacao(Long idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public Solicitacao(Long idSolicitacao, Usuario usuarioAlvo, Usuario usuarioDono) {
        this.idSolicitacao = idSolicitacao;
        this.usuarioDono = usuarioDono;
        this.usuarioAlvo = usuarioAlvo;
    }

    public Solicitacao(Usuario usuarioDono, Usuario usuarioAlvo, Rotina rotinaUsuarioDono) {
        this.usuarioDono = usuarioDono;
        this.usuarioAlvo = usuarioAlvo;
        this.rotinaUsuarioDono = rotinaUsuarioDono;
    }

    public Long getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(Long idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public Usuario getUsuarioDono() {
        return usuarioDono;
    }

    public void setUsuarioDono(Usuario usuarioDono) {
        this.usuarioDono = usuarioDono;
    }

    public Usuario getUsuarioAlvo() {
        return usuarioAlvo;
    }

    public void setUsuarioAlvo(Usuario usuarioAlvo) {
        this.usuarioAlvo = usuarioAlvo;
    }

    public Rotina getRotinaUsuarioDono() {
        return rotinaUsuarioDono;
    }

    public void setRotinaUsuarioDono(Rotina rotinaUsuarioDono) {
        this.rotinaUsuarioDono = rotinaUsuarioDono;
    }

}
