package br.com.crescer.caronas.service;

import br.com.crescer.caronas.entity.Destino;
import br.com.crescer.caronas.entity.DiaSemana;
import br.com.crescer.caronas.entity.Origem;
import br.com.crescer.caronas.entity.Rotina;
import br.com.crescer.caronas.entity.RotinaDiaSemana;
import br.com.crescer.caronas.entity.Usuario;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author alexia.pereira
 */
public class ValidarHorarioServiceTest {

    final SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    final ValidarHorarioService service = new ValidarHorarioService();
    
    @Test
    public void testBuscarDiasDaSemanaCompativeis() throws ParseException {
        Rotina rotinaPrincipal = instanciarRotinaPadrao();
        List<Rotina> rotinasDeMotoristas = this.rotinasDeMotoristas();
        List<Rotina> rotinasCompativeis
                = service.buscarDiasDaSemanaCompativeis(rotinaPrincipal, rotinasDeMotoristas);

        assertTrue(rotinasCompativeis.contains(rotinasDeMotoristas.get(0)));
        assertEquals(1, rotinasCompativeis.size());
        assertFalse(rotinasCompativeis.contains(rotinasDeMotoristas.get(1)));
        assertFalse(rotinasCompativeis.contains(rotinasDeMotoristas.get(2)));
    }

    private List<Rotina> rotinasDeMotoristas() throws ParseException {

        List<Rotina> retorno = new ArrayList<>();

        Date dataUm = formatador.parse("09/07/2017 08:15");
        List<RotinaDiaSemana> listaDeDiasUm = new ArrayList<>();
        listaDeDiasUm.add(new RotinaDiaSemana(5, new DiaSemana("SEGUNDA")));
        listaDeDiasUm.add(new RotinaDiaSemana(5, new DiaSemana("TERÇA")));
        listaDeDiasUm.add(new RotinaDiaSemana(5, new DiaSemana("QUARTA")));
        Rotina rotinaUm = instanciarRotinaPadrao();
        rotinaUm.setHorario(dataUm);
        rotinaUm.setPassageiro(false);
        rotinaUm.setRotinaDiaSemanaList(listaDeDiasUm);
        retorno.add(rotinaUm);

        Date dataDois = formatador.parse("06/04/2017 07:30");
        List<RotinaDiaSemana> listaDeDiasDois = new ArrayList<>();
        listaDeDiasDois.add(new RotinaDiaSemana(5, new DiaSemana("QUARTA")));
        listaDeDiasDois.add(new RotinaDiaSemana(5, new DiaSemana("QUINTA")));
        listaDeDiasDois.add(new RotinaDiaSemana(5, new DiaSemana("SEXTA")));
        Rotina rotinaDois = instanciarRotinaPadrao();
        rotinaDois.setHorario(dataDois);
        rotinaDois.setPassageiro(false);
        rotinaDois.setRotinaDiaSemanaList(listaDeDiasDois);
        retorno.add(rotinaDois);

        Date dataTres = formatador.parse("08/06/2017 07:00");
        List<RotinaDiaSemana> listaDeDiasTres = new ArrayList<>();
        listaDeDiasTres.add(new RotinaDiaSemana(5, new DiaSemana("TERÇA")));
        listaDeDiasTres.add(new RotinaDiaSemana(5, new DiaSemana("QUINTA")));
        listaDeDiasTres.add(new RotinaDiaSemana(5, new DiaSemana("SABADO")));
        Rotina rotinaTres = instanciarRotinaPadrao();
        rotinaTres.setHorario(dataTres);
        rotinaTres.setPassageiro(false);
        rotinaTres.setRotinaDiaSemanaList(listaDeDiasTres);
        retorno.add(rotinaTres);

        return retorno;
    }

    private Rotina instanciarRotinaPadrao() throws ParseException {
        Usuario usuario = new Usuario("Teste", "teste@teste.com", "Masculino", "2", "senha");
        Destino destino = new Destino("destino", BigDecimal.ONE, BigDecimal.ONE);
        Origem origem = new Origem("origem", BigDecimal.ONE, BigDecimal.ONE);
        List<RotinaDiaSemana> listaDeDias = new ArrayList<>();
        listaDeDias.add(new RotinaDiaSemana(5, new DiaSemana("SEGUNDA")));
        String horario = "12/12/2012 07:15";
        Rotina rotina = new Rotina(true, formatador.parse(horario), BigDecimal.TEN, BigDecimal.ZERO, listaDeDias, destino, origem, usuario);
        for (RotinaDiaSemana rotinaDiaSemana : listaDeDias) {
            rotinaDiaSemana.setRotina(rotina);
        }

        return rotina;
    }
}
