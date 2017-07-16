package br.com.crescer.caronas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ROTINA")
    @SequenceGenerator(
            name = "SEQ_ROTINA",
            sequenceName = "SEQ_ROTINA",
            allocationSize = 1
    )
    private Long idRotina;

    @Basic(optional = false)
    @NotNull
    @Column(name = "PASSAGEIRO")
    private boolean passageiro;

    @Basic(optional = false)
    @NotNull
    @Column(name = "HORARIO")
//    @Temporal(TemporalType.TIMESTAMP)
    private Date horario;

    @Basic(optional = false)
    @NotNull
    @Column(name = "DISTANCIA")
    private BigDecimal distancia;

    @Basic(optional = false)
    @NotNull
    @Column(name = "DURACAO")
    private BigDecimal duracao;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rotina")
    private List<RotinaDiaSemana> rotinaDiaSemanaList;

    @JoinColumn(name = "ID_DESTINO", referencedColumnName = "ID_DESTINO")
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private Destino destino;

    @JoinColumn(name = "ID_ORIGEM", referencedColumnName = "ID_ORIGEM")
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JsonBackReference
    private Origem origem;

    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private Usuario usuario;

    @Basic(optional = false)
    @NotNull
    @Column(name = "DISPONIVEL")
    private boolean disponivel;

    public Rotina() {
    }

    public Rotina(Long idRotina) {
        this.idRotina = idRotina;
    }

    public Rotina(Long idRotina, boolean passageiro, Date horario, BigDecimal distancia, BigDecimal duracao) {
        this.idRotina = idRotina;
        this.passageiro = passageiro;
        this.horario = horario;
        this.distancia = distancia;
        this.duracao = duracao;
    }

    public Rotina(boolean passageiro, Date horario, BigDecimal distancia, BigDecimal duracao, List<RotinaDiaSemana> rotinaDiaSemanaList, Destino destino, Origem origem, Usuario usuario, boolean disponivel) {
        this.passageiro = passageiro;
        this.horario = horario;
        this.distancia = distancia;
        this.duracao = duracao;
        this.rotinaDiaSemanaList = rotinaDiaSemanaList;
        this.destino = destino;
        this.origem = origem;
        this.usuario = usuario;
        this.disponivel = disponivel;
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

    public BigDecimal getDistancia() {
        return distancia;
    }

    public void setDistancia(BigDecimal distancia) {
        this.distancia = distancia;
    }

    public BigDecimal getDuracao() {
        return duracao;
    }

    public void setDuracao(BigDecimal duracao) {
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Origem getOrigem() {
        return origem;
    }

    public void setOrigem(Origem origem) {
        this.origem = origem;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

}
