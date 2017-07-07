package br.com.crescer.caronas.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alexia.pereira
 */
@Entity
@Table(name = "ROTINA")
public class Rotina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ROTINA")
    private Long idRotina;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PASSAGEIRO")
    private boolean passageiro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORARIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DISTANCIA")
    private BigInteger distancia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DURACAO")
    private BigInteger duracao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rotina")
    private List<RotinaDiaSemana> rotinaDiaSemanaList;
    @JoinColumn(name = "ID_DESTINO", referencedColumnName = "ID_DESTINO")
    @ManyToOne(optional = false)
    private Destino destino;
    @JoinColumn(name = "ID_ORIGEM", referencedColumnName = "ID_ORIGEM")
    @ManyToOne(optional = false)
    private Origem origem;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Rotina() {
    }

    public Rotina(Long idRotina) {
        this.idRotina = idRotina;
    }

    public Rotina(Long idRotina, boolean passageiro, Date horario, BigInteger distancia, BigInteger duracao) {
        this.idRotina = idRotina;
        this.passageiro = passageiro;
        this.horario = horario;
        this.distancia = distancia;
        this.duracao = duracao;
    }

    public Long getIdRotina() {
        return idRotina;
    }

    public void setIdRotina(Long idRotina) {
        this.idRotina = idRotina;
    }

    public boolean getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(boolean passageiro) {
        this.passageiro = passageiro;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public BigInteger getDistancia() {
        return distancia;
    }

    public void setDistancia(BigInteger distancia) {
        this.distancia = distancia;
    }

    public BigInteger getDuracao() {
        return duracao;
    }

    public void setDuracao(BigInteger duracao) {
        this.duracao = duracao;
    }

    @XmlTransient
    public List<RotinaDiaSemana> getRotinaDiaSemanaList() {
        return rotinaDiaSemanaList;
    }

    public void setRotinaDiaSemanaList(List<RotinaDiaSemana> rotinaDiaSemanaList) {
        this.rotinaDiaSemanaList = rotinaDiaSemanaList;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public Origem getIdOrigem() {
        return origem;
    }

    public void setIdOrigem(Origem origem) {
        this.origem = origem;
    }

    public Usuario getIdUsuario() {
        return usuario;
    }

    public void setIdUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
