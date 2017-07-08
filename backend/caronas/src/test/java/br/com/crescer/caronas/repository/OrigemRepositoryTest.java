package br.com.crescer.caronas.repository;

import br.com.crescer.caronas.entity.Origem;
import br.com.crescer.caronas.entity.Usuario;
import br.com.crescer.caronas.entity.Grupo;
import java.math.BigDecimal;
import java.math.BigInteger;
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
public class OrigemRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private OrigemRepository repositorio;

    /**
     * Test of save method, of class GeneroService.
     */
    @Test
    public void testSave() {
        final Origem origem = instanciarOrigem();
        repositorio.save(origem);
        assertEquals(origem.getEndereco(), testEntityManager.find(Origem.class, origem.getIdOrigem()).getEndereco());
    }

    /**
     * Test of findAll method, of class GeneroService.
     */
    @Test
    public void testFindAll() {
        final Origem origem = instanciarOrigem();
        testEntityManager.persist(origem);

        assertTrue(StreamSupport.stream(repositorio.findAll().spliterator(), false)
                .map(Origem::getEndereco)
                .collect(toList())
                .contains(origem.getEndereco()));

    }

    /**
     * Test of findOne method, of class GeneroService.
     */
    @Test
    public void testFindOne() {
        final Origem origem = instanciarOrigem();
        testEntityManager.persist(origem);
        assertEquals(origem.getEndereco(), repositorio.findOne(origem.getIdOrigem()).getEndereco());
    }

    @Test
    public void testRemove() {
        final Origem origem = instanciarOrigem();
        testEntityManager.persist(origem);
        repositorio.delete(origem);
        assertNull(repositorio.findOne(origem.getIdOrigem()));
    }

    @Test
    public void testUpdate() {
        final Origem origem = instanciarOrigem();
        String enderecoAntigo = origem.getEndereco();
        testEntityManager.persist(origem);
        String enderecoNovo = "Novo endereco";
        origem.setEndereco(enderecoNovo);
        testEntityManager.persist(origem);
        assertNotEquals(enderecoAntigo, repositorio.findOne(origem.getIdOrigem()).getEndereco());
        assertEquals(enderecoNovo, repositorio.findOne(origem.getIdOrigem()).getEndereco());
    }

    private Origem instanciarOrigem() {
        return new Origem("Rua teste - UF", BigDecimal.ONE, BigDecimal.ONE);
    }

}
