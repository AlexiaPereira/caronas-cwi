package br.com.crescer.caronas.entity;

import java.io.Serializable;
import java.lang.Long;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author alexia.pereira
 */
@Entity
@Table(name = "DIA_SEMANA")
public class DiaSemana implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_DIA_SEMANA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DIASEMANA")
    @SequenceGenerator(
            name = "SEQ_DIASEMANA",
            sequenceName = "SEQ_DIASEMANA",
            allocationSize = 1
    )
    private Long idDiaSemana;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "NOME")
    private String nome;

    public DiaSemana() {
    }

    public DiaSemana(Long idDiaSemana) {
        this.idDiaSemana = idDiaSemana;
    }

    public DiaSemana(Long idDiaSemana, String nome) {
        this.idDiaSemana = idDiaSemana;
        this.nome = nome;
    }

    public DiaSemana(String nome) {
        this.nome = nome;
    }

    public Long getIdDiaSemana() {
        return idDiaSemana;
    }

    public void setIdDiaSemana(Long idDiaSemana) {
        this.idDiaSemana = idDiaSemana;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
