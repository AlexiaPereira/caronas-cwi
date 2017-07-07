package br.com.crescer.caronas.entity;

import java.io.Serializable;
import java.lang.Long;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

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
    private BigInteger latitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LONGITUDE")
    private BigInteger longitude;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destino")
    private List<Rotina> rotinaList;

    public Destino() {
    }

    public Destino(Long idDestino) {
        this.idDestino = idDestino;
    }

    public Destino(Long idDestino, String endereco, BigInteger latitude, BigInteger longitude) {
        this.idDestino = idDestino;
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

    public BigInteger getLatitude() {
        return latitude;
    }

    public void setLatitude(BigInteger latitude) {
        this.latitude = latitude;
    }

    public BigInteger getLongitude() {
        return longitude;
    }

    public void setLongitude(BigInteger longitude) {
        this.longitude = longitude;
    }

    @XmlTransient
    public List<Rotina> getRotinaList() {
        return rotinaList;
    }

    public void setRotinaList(List<Rotina> rotinaList) {
        this.rotinaList = rotinaList;
    }

}
