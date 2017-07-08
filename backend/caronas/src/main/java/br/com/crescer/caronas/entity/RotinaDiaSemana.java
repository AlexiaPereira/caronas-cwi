package br.com.crescer.caronas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.lang.Long;
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
@Table(name = "ROTINA_DIA_SEMANA")
public class RotinaDiaSemana implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ROTINA_DIA_SEMANA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ROTINADIASEMANA")
    @SequenceGenerator(
            name = "SEQ_ROTINADIASEMANA",
            sequenceName = "SEQ_ROTINADIASEMANA",
            allocationSize = 1
    )
    private Long idRotinaDiaSemana;

    @Column(name = "VAGAS_DISPONIVEIS")
    private int vagasDisponiveis;

    @JoinColumn(name = "ID_DIA_SEMANA", referencedColumnName = "ID_DIA_SEMANA")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private DiaSemana diaSemana;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ROTINA", referencedColumnName = "ID_ROTINA")
    @JsonBackReference
    private Rotina rotina;

    public RotinaDiaSemana() {
    }

    public RotinaDiaSemana(Long idRotinaDiaSemana) {
        this.idRotinaDiaSemana = idRotinaDiaSemana;
    }

    public RotinaDiaSemana(Long idRotinaDiaSemana, DiaSemana diaSemana) {
        this.idRotinaDiaSemana = idRotinaDiaSemana;
        this.diaSemana = diaSemana;
    }

//    public RotinaDiaSemana(int vagasDisponiveis, DiaSemana diaSemana, Rotina rotina) {
//        this.vagasDisponiveis = vagasDisponiveis;
//        this.diaSemana = diaSemana;
//        this.rotina = rotina;
//    }  
    public RotinaDiaSemana(int vagasDisponiveis, DiaSemana diaSemana) {
        this.vagasDisponiveis = vagasDisponiveis;
        this.diaSemana = diaSemana;
    }

    public Long getIdRotinaDiaSemana() {
        return idRotinaDiaSemana;
    }

    public void setIdRotinaDiaSemana(Long idRotinaDiaSemana) {
        this.idRotinaDiaSemana = idRotinaDiaSemana;
    }

    public int getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    public void setVagasDisponiveis(int vagasDisponiveis) {
        this.vagasDisponiveis = vagasDisponiveis;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setIdDiasemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Rotina getRotina() {
        return rotina;
    }

    public void setRotina(Rotina rotina) {
        this.rotina = rotina;
    }

}
