package br.com.crescer.caronas.dto;

import br.com.crescer.caronas.entity.Rotina;
import br.com.crescer.caronas.entity.Solicitacao;

/**
 *
 * @author alexia.pereira
 */
public class SolicitacaoRotinaDTO {

    private Solicitacao solicitacao;
    private Rotina rotinaMotorista;

    public SolicitacaoRotinaDTO() {
    }

    public SolicitacaoRotinaDTO(Solicitacao solicitacao, Rotina rotinaMotorista) {
        this.solicitacao = solicitacao;
        this.rotinaMotorista = rotinaMotorista;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    public Rotina getRotinaMotorista() {
        return rotinaMotorista;
    }

    public void setRotinaMotorista(Rotina rotinaMotorista) {
        this.rotinaMotorista = rotinaMotorista;
    }

}
