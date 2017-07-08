package br.com.crescer.caronas.repository;

import br.com.crescer.caronas.entity.DiaSemana;
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
public class DiaSemanaRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private DiaSemanaRepository repositorio;

    /**
     * Test of save method, of class GeneroService.
     */
    @Test
    public void testSave() {
        final DiaSemana diaSemana = instanciarDiaSemana();
        repositorio.save(diaSemana);
        assertEquals(diaSemana.getNome(), testEntityManager.find(DiaSemana.class, diaSemana.getIdDiaSemana()).getNome());
    }

    /**
     * Test of findAll method, of class GeneroService.
     */
    @Test
    public void testFindAll() {
        final DiaSemana diaSemana = instanciarDiaSemana();
        testEntityManager.persist(diaSemana);

        assertTrue(StreamSupport.stream(repositorio.findAll().spliterator(), false)
                .map(DiaSemana::getNome)
                .collect(toList())
                .contains(diaSemana.getNome()));

    }

    /**
     * Test of findOne method, of class GeneroService.
     */
    @Test
    public void testFindOne() {
        final DiaSemana diaSemana = instanciarDiaSemana();
        testEntityManager.persist(diaSemana);
        assertEquals(diaSemana.getNome(), repositorio.findOne(diaSemana.getIdDiaSemana()).getNome());
    }

    @Test
    public void testRemove() {
        final DiaSemana diaSemana = instanciarDiaSemana();
        testEntityManager.persist(diaSemana);
        repositorio.delete(diaSemana);
        assertNull(repositorio.findOne(diaSemana.getIdDiaSemana()));
    }

    @Test
    public void testUpdate() {
        final DiaSemana diaSemana = instanciarDiaSemana();
        String nomeAntigo = diaSemana.getNome();
        testEntityManager.persist(diaSemana);
        String nomeNovo = "Novo nome";
        diaSemana.setNome(nomeNovo);
        testEntityManager.persist(diaSemana);
        assertNotEquals(nomeAntigo, repositorio.findOne(diaSemana.getIdDiaSemana()).getNome());
        assertEquals(nomeNovo, repositorio.findOne(diaSemana.getIdDiaSemana()).getNome());
    }

    private DiaSemana instanciarDiaSemana() {
        return new DiaSemana("Segunda");
    }

}
