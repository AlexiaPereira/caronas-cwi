package br.com.crescer.caronas.repository;

import br.com.crescer.caronas.entity.Destino;
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
public class DestinoRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private DestinoRepository repositorio;

    /**
     * Test of save method, of class GeneroService.
     */
    @Test
    public void testSave() {
        final Destino destino = instanciarDestino();
        repositorio.save(destino);
        assertEquals(destino.getEndereco(), testEntityManager.find(Destino.class, destino.getIdDestino()).getEndereco());
    }

    /**
     * Test of findAll method, of class GeneroService.
     */
    @Test
    public void testFindAll() {
        final Destino destino = instanciarDestino();
        testEntityManager.persist(destino);

        assertTrue(StreamSupport.stream(repositorio.findAll().spliterator(), false)
                .map(Destino::getEndereco)
                .collect(toList())
                .contains(destino.getEndereco()));

    }

    /**
     * Test of findOne method, of class GeneroService.
     */
    @Test
    public void testFindOne() {
        final Destino destino = instanciarDestino();
        testEntityManager.persist(destino);
        assertEquals(destino.getEndereco(), repositorio.findOne(destino.getIdDestino()).getEndereco());
    }

    @Test
    public void testRemove() {
        final Destino destino = instanciarDestino();
        testEntityManager.persist(destino);
        repositorio.delete(destino);
        assertNull(repositorio.findOne(destino.getIdDestino()));
    }

    @Test
    public void testUpdate() {
        final Destino destino = instanciarDestino();
        String enderecoAntigo = destino.getEndereco();
        testEntityManager.persist(destino);
        String enderecoNovo = "Novo endereco";
        destino.setEndereco(enderecoNovo);
        testEntityManager.persist(destino);
        assertNotEquals(enderecoAntigo, repositorio.findOne(destino.getIdDestino()).getEndereco());
        assertEquals(enderecoNovo, repositorio.findOne(destino.getIdDestino()).getEndereco());
    }

    private Destino instanciarDestino() {
        return new Destino("Rua teste - UF", BigDecimal.ONE, BigDecimal.ONE);
    }

}
