package br.com.crescer.caronas.entity;

import java.io.Serializable;
import java.lang.Long;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author alexia.pereira
 */
@Entity
@Table(name = "DESTINO")
public class Destino implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_DESTINO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DESTINO")
    @SequenceGenerator(
            name = "SEQ_DESTINO",
            sequenceName = "SEQ_DESTINO",
            allocationSize = 1
    )
    private Long idDestino;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "ENDERECO")
    private String endereco;

    @Basic(optional = false)
    @NotNull
    @Column(name = "LATITUDE")
    private BigDecimal latitude;

    @Basic(optional = false)
    @NotNull
    @Column(name = "LONGITUDE")
    private BigDecimal longitude;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "destino")
    private Rotina rotina;

    public Destino() {
    }

    public Destino(Long idDestino) {
        this.idDestino = idDestino;
    }

    public Destino(Long idDestino, String endereco, BigDecimal latitude, BigDecimal longitude) {
        this.idDestino = idDestino;
        this.endereco = endereco;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Destino(String endereco, BigDecimal latitude, BigDecimal longitude) {
        this.endereco = endereco;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(Long idDestino) {
        this.idDestino = idDestino;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Rotina getRotina() {
        return rotina;
    }

    public void setRotina(Rotina rotina) {
        this.rotina = rotina;
    }

}
