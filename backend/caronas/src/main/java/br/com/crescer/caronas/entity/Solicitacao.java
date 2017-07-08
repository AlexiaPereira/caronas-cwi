package br.com.crescer.caronas.entity;

import java.io.Serializable;
import javax.persistence.Basic;
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
    @ManyToOne
    private Usuario usuarioDono;

    @NotNull
    @JoinColumn(name = "ID_USUARIO_ALVO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario usuarioAlvo;

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

    public Solicitacao(Usuario usuarioDono, Usuario usuarioAlvo) {
        this.usuarioDono = usuarioDono;
        this.usuarioAlvo = usuarioAlvo;
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

}
