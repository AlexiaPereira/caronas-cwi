package br.com.crescer.caronas.dto;
 
import br.com.crescer.caronas.entity.Rotina;
import java.math.BigDecimal;
 

 
/**
 
 *
 
 * @author Deordines
 
 */
 
public class DistanciaRotina {
 
    private Rotina rotina; 
    private BigDecimal distancia;
 
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
 