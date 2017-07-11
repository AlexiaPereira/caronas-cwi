package br.com.crescer.caronas.service;

import br.com.crescer.caronas.service.ValidarDistanciaService;
import br.com.crescer.caronas.dto.DistanciaRotina;
import br.com.crescer.caronas.entity.Destino;
import br.com.crescer.caronas.entity.DiaSemana;
import br.com.crescer.caronas.entity.Rotina;
import br.com.crescer.caronas.entity.Usuario;
import br.com.crescer.caronas.entity.Origem;
import br.com.crescer.caronas.entity.RotinaDiaSemana;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author christopher.michel
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.REQUIRED)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class ValidarDistanciaServiceTest {

    ValidarDistanciaService service = new ValidarDistanciaService();
    
    ValidarVagasService validarVagasService = new ValidarVagasService();
    
    @Test
    public void testFiltrarDiaSemana2DiasVolta1() {
        final Rotina rotina = instanciarRotina();
        final List<String> listaStringTest = new ArrayList<>();
        listaStringTest.add("SEGUNDA");
        listaStringTest.add("TERCA");
        List<RotinaDiaSemana> rotinaDiaSemana = validarVagasService.filtrarDiaSemana(listaStringTest, rotina);
        assertEquals(1, rotinaDiaSemana.size());
    }
    
    @Test
    public void testFiltrarDiaSemana4DiasVolta2() {
        final Rotina rotina = instanciarRotina2();
        final List<String> listaStringTest = new ArrayList<>();
        listaStringTest.add("SEGUNDA");
        listaStringTest.add("TERCA");
        listaStringTest.add("QUARTA");
        listaStringTest.add("QUINTA");
        List<RotinaDiaSemana> rotinaDiaSemana = validarVagasService.filtrarDiaSemana(listaStringTest, rotina);
        assertEquals(2, rotinaDiaSemana.size());
    }
    
    @Test
    public void testFiltrarDiaSemana4DiasVolta0() {
        final Rotina rotina = instanciarRotina3();
        final List<String> listaStringTest = new ArrayList<>();
        listaStringTest.add("SEGUNDA");
        listaStringTest.add("TERCA");
        listaStringTest.add("QUARTA");
        listaStringTest.add("QUINTA");
        List<RotinaDiaSemana> rotinaDiaSemana = validarVagasService.filtrarDiaSemana(listaStringTest, rotina);
        assertEquals(0, rotinaDiaSemana.size());
    }
    
    @Test
    public void testVerificarDistancias() {
        List<Rotina> matches = new ArrayList<>();
        List<DistanciaRotina> motoristas = new ArrayList<>();
        final Rotina rotinaMotorista = instanciarRotina2();
        final Rotina rotinaPassageiro = instanciarRotina();
        final BigDecimal distancia = new BigDecimal ("500");
        final DistanciaRotina distanciaRotina = new DistanciaRotina(distancia, rotinaMotorista);
        motoristas.add(distanciaRotina);
        matches = service.verificarDistancias(rotinaPassageiro, motoristas);
        assertEquals(1, matches.size());
    }
    
    @Test
    public void testVerificarDistanciasFalso() {
        List<Rotina> matches = new ArrayList<>();
        List<DistanciaRotina> motoristas = new ArrayList<>();
        final Rotina rotinaMotorista = instanciarRotina2();
        final Rotina rotinaPassageiro = instanciarRotina();
        final BigDecimal distancia = new BigDecimal ("2000");
        final DistanciaRotina distanciaRotina = new DistanciaRotina(distancia, rotinaMotorista);
        motoristas.add(distanciaRotina);
        matches = service.verificarDistancias(rotinaPassageiro, motoristas);
        assertEquals(0, matches.size());
    }
        
    private Rotina instanciarRotina() {
        Usuario usuario = new Usuario("Teste", "teste@teste.com", "Masculino", "2", "senha");
        Destino destino = new Destino("destino", BigDecimal.ONE, BigDecimal.ONE);
        Origem origem = new Origem("origem", BigDecimal.ONE, BigDecimal.ONE);
        List<RotinaDiaSemana> listaDeDias = new ArrayList<>();
        listaDeDias.add(new RotinaDiaSemana(5, new DiaSemana("SEGUNDA")));
        Rotina rotina = new Rotina(true, new Date(), BigDecimal.TEN, BigDecimal.ZERO, listaDeDias, destino, origem, usuario);
        for (RotinaDiaSemana rotinaDiaSemana : listaDeDias) {
            rotinaDiaSemana.setRotina(rotina);
        }
        return rotina;
    }
    
    private Rotina instanciarRotina2() {
        Usuario usuario = new Usuario("Teste", "teste@teste.com", "Masculino", "2", "senha");
        Destino destino = new Destino("destino", BigDecimal.ONE, BigDecimal.ONE);
        Origem origem = new Origem("origem", BigDecimal.ONE, BigDecimal.ONE);
        List<RotinaDiaSemana> listaDeDias = new ArrayList<>();
        listaDeDias.add(new RotinaDiaSemana(5, new DiaSemana("SEGUNDA")));
        listaDeDias.add(new RotinaDiaSemana(5, new DiaSemana("TERCA")));
        Rotina rotina = new Rotina(false, new Date(), BigDecimal.TEN, BigDecimal.ZERO, listaDeDias, destino, origem, usuario);
        for (RotinaDiaSemana rotinaDiaSemana : listaDeDias) {
            rotinaDiaSemana.setRotina(rotina);
        }
        return rotina;
    }
    
    private Rotina instanciarRotina3() {
        Usuario usuario = new Usuario("Teste", "teste@teste.com", "Masculino", "2", "senha");
        Destino destino = new Destino("destino", BigDecimal.ONE, BigDecimal.ONE);
        Origem origem = new Origem("origem", BigDecimal.ONE, BigDecimal.ONE);
        List<RotinaDiaSemana> listaDeDias = new ArrayList<>();
        Rotina rotina = new Rotina(true, new Date(), BigDecimal.TEN, BigDecimal.ZERO, listaDeDias, destino, origem, usuario);
        for (RotinaDiaSemana rotinaDiaSemana : listaDeDias) {
            rotinaDiaSemana.setRotina(rotina);
        }
        return rotina;
    }
}
