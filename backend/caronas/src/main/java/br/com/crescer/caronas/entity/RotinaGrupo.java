package br.com.crescer.caronas.entity;

import java.io.Serializable;
import java.lang.Long;
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
@Table(name = "ROTINA_GRUPO")
public class RotinaGrupo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ROTINA_GRUPO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ROTINAGRUPO")
    @SequenceGenerator(
            name = "SEQ_ROTINAGRUPO",
            sequenceName = "SEQ_ROTINAGRUPO",
            allocationSize = 1
    )
    private Long idRotinaGrupo;

    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "ID_ROTINA", referencedColumnName = "ID_ROTINA")
    @ManyToOne
    private Rotina rotina;

    @JoinColumn(name = "ID_GRUPO", referencedColumnName = "ID_GRUPO")
    @ManyToOne(optional = false)
    private Grupo grupo;

    public RotinaGrupo() {
    }

    public RotinaGrupo(Long idRotinaGrupo) {
        this.idRotinaGrupo = idRotinaGrupo;
    }

    public RotinaGrupo(Long idRotinaGrupo, Rotina rotina) {
        this.idRotinaGrupo = idRotinaGrupo;
        this.rotina = rotina;
    }

    public Long getIdRotinaGrupo() {
        return idRotinaGrupo;
    }

    public void setIdRotinaGrupo(Long idRotinaGrupo) {
        this.idRotinaGrupo = idRotinaGrupo;
    }

    public Rotina getRotina() {
        return rotina;
    }

    public void setIdRotina(Rotina rotina) {
        this.rotina = rotina;
    }

    public Grupo getIdGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

}
