package br.com.crescer.caronas.repository;

import br.com.crescer.caronas.entity.Destino;
import br.com.crescer.caronas.entity.DiaSemana;
import br.com.crescer.caronas.entity.Origem;
import br.com.crescer.caronas.entity.Rotina;
import br.com.crescer.caronas.entity.RotinaDiaSemana;
import br.com.crescer.caronas.entity.Usuario;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.StreamSupport;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author alexia.pereira
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.REQUIRED)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class RotinaDiaSemanaRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private RotinaDiaSemanaRepository repositorio;

    /**
     * Test of save method, of class GeneroService.
     */
    @Test
    public void testSave() {
        final RotinaDiaSemana rotinaDiaSemana = instanciarRotinaDiaSemana();
        repositorio.save(rotinaDiaSemana);
        assertEquals(rotinaDiaSemana.getDiaSemana().getNome(), testEntityManager.find(RotinaDiaSemana.class, rotinaDiaSemana.getIdRotinaDiaSemana()).getDiaSemana().getNome());
    }

    /**
     * Test of findAll method, of class GeneroService.
     */
    @Test
    public void testFindAll() {
        final RotinaDiaSemana rotinaDiaSemana = instanciarRotinaDiaSemana();
        testEntityManager.persist(rotinaDiaSemana);

        assertTrue(StreamSupport.stream(repositorio.findAll().spliterator(), false)
                .map(RotinaDiaSemana::getDiaSemana)
                .map(DiaSemana::getNome)
                .collect(toList())
                .contains(rotinaDiaSemana.getDiaSemana().getNome()));

    }

    /**
     * Test of findOne method, of class GeneroService.
     */
    @Test
    public void testFindOne() {
        final RotinaDiaSemana rotinaDiaSemana = instanciarRotinaDiaSemana();
        testEntityManager.persist(rotinaDiaSemana);
        assertEquals(rotinaDiaSemana.getDiaSemana().getNome(), repositorio.findOne(rotinaDiaSemana.getIdRotinaDiaSemana()).getDiaSemana().getNome());
    }

    @Test
    public void testRemove() {
        final RotinaDiaSemana rotinaDiaSemana = instanciarRotinaDiaSemana();
        testEntityManager.persist(rotinaDiaSemana);
        repositorio.delete(rotinaDiaSemana);
        assertNull(repositorio.findOne(rotinaDiaSemana.getIdRotinaDiaSemana()));
    }

    @Test
    public void testUpdate() {
        final RotinaDiaSemana rotinaDiaSemana = instanciarRotinaDiaSemana();
        DiaSemana diaDaSemanaAntigo = rotinaDiaSemana.getDiaSemana();
        testEntityManager.persist(rotinaDiaSemana);
        DiaSemana novoDiaSemana = new DiaSemana("SABADO");
        rotinaDiaSemana.setIdDiasemana(novoDiaSemana);
        testEntityManager.persist(rotinaDiaSemana);
        assertNotEquals(diaDaSemanaAntigo.getNome(), repositorio.findOne(rotinaDiaSemana.getIdRotinaDiaSemana()).getDiaSemana().getNome());
        assertEquals(novoDiaSemana.getNome(), repositorio.findOne(rotinaDiaSemana.getIdRotinaDiaSemana()).getDiaSemana().getNome());
    }

    private RotinaDiaSemana instanciarRotinaDiaSemana() {
        DiaSemana diaSemana = new DiaSemana("SEGUNDA");
        testEntityManager.persist(diaSemana);
        return new RotinaDiaSemana(5, diaSemana, this.instanciarRotina());
    }
    
    private Rotina instanciarRotina() {
        Usuario usuario = new Usuario("Teste", "teste@teste.com", "Masculino", 2l, "senha");
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


}
