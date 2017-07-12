package br.com.crescer.caronas.dto;

import br.com.crescer.caronas.entity.Rotina;
import java.math.BigDecimal;

/**
 *
 *
 *
 * @author Deordines
 *
 */
public class DistanciaRotinaDTO {

    private Rotina rotina;
    private BigDecimal distancia;

    public DistanciaRotinaDTO(BigDecimal distancia, Rotina rotina) {
        this.rotina = rotina;
        this.distancia = distancia;
    }

    public DistanciaRotinaDTO() {
    }

    public Rotina getRotina() {
        return rotina;
    }

    public void setRotina(Rotina rotina) {
        this.rotina = rotina;
    }

    public BigDecimal getDistancia() {
        return distancia;
    }

    public void setDistancia(BigDecimal distancia) {
        this.distancia = distancia;
    }

}
