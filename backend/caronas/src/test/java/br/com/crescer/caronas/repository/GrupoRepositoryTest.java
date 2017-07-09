package br.com.crescer.caronas.repository;

import br.com.crescer.caronas.entity.Grupo;
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
public class GrupoRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private GrupoRepository repositorio;

    /**
     * Test of save method, of class GeneroService.
     */
    @Test
    public void testSave() {
        final Grupo grupo = instanciarGrupo();
        repositorio.save(grupo);
        assertEquals(grupo.getNome(), testEntityManager.find(Grupo.class, grupo.getIdGrupo()).getNome());
    }

    /**
     * Test of findAll method, of class GeneroService.
     */
    @Test
    public void testFindAll() {
        final Grupo grupo = instanciarGrupo();
        testEntityManager.persist(grupo);

        assertTrue(StreamSupport.stream(repositorio.findAll().spliterator(), false)
                .map(Grupo::getNome)
                .collect(toList())
                .contains(grupo.getNome()));

    }

    /**
     * Test of findOne method, of class GeneroService.
     */
    @Test
    public void testFindOne() {
        final Grupo grupo = instanciarGrupo();
        testEntityManager.persist(grupo);
        assertEquals(grupo.getNome(), repositorio.findOne(grupo.getIdGrupo()).getNome());
    }

    @Test
    public void testRemove() {
        final Grupo grupo = instanciarGrupo();
        testEntityManager.persist(grupo);
        repositorio.delete(grupo);
        assertNull(repositorio.findOne(grupo.getIdGrupo()));
    }

    @Test
    public void testUpdate() {
        final Grupo grupo = instanciarGrupo();
        String enderecoAntigo = grupo.getNome();
        testEntityManager.persist(grupo);
        String enderecoNovo = "Novo endereco";
        grupo.setNome(enderecoNovo);
        testEntityManager.persist(grupo);
        assertNotEquals(enderecoAntigo, repositorio.findOne(grupo.getIdGrupo()).getNome());
        assertEquals(enderecoNovo, repositorio.findOne(grupo.getIdGrupo()).getNome());
    }

    private Grupo instanciarGrupo() {
        return new Grupo("Nome do Grupo");
    }

}
