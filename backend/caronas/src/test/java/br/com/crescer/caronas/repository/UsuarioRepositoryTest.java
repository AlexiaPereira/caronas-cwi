package br.com.crescer.caronas.repository;

import br.com.crescer.caronas.entity.Usuario;
import java.util.Date;
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
public class UsuarioRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UsuarioRepository repositorio;

    /**
     * Test of save method, of class GeneroService.
     */
    @Test
    public void testSave() {
        final Usuario usuario = instanciarUsuario();
        repositorio.save(usuario);
        assertEquals(usuario.getNome(), testEntityManager.find(Usuario.class, usuario.getIdUsuario()).getNome());
    }

    /**
     * Test of findAll method, of class GeneroService.
     */
    @Test
    public void testFindAll() {
        final Usuario usuario = instanciarUsuario();
        testEntityManager.persist(usuario);

        assertTrue(StreamSupport.stream(repositorio.findAll().spliterator(), false)
                .map(Usuario::getNome)
                .collect(toList())
                .contains(usuario.getNome()));

    }

    /**
     * Test of findOne method, of class GeneroService.
     */
    @Test
    public void testFindOne() {
        final Usuario usuario = instanciarUsuario();
        testEntityManager.persist(usuario);
        assertEquals(usuario.getNome(), repositorio.findOne(usuario.getIdUsuario()).getNome());
    }

    @Test
    public void testRemove() {
        final Usuario usuario = instanciarUsuario();
        testEntityManager.persist(usuario);
        repositorio.delete(usuario);
        assertNull(repositorio.findOne(usuario.getIdUsuario()));
    }

    @Test
    public void testUpdate() {
        final Usuario usuario = instanciarUsuario();
        String nomeAntiga = "Nome";
        usuario.setNome("nomeAntiga");
        testEntityManager.persist(usuario);
        String nomeNova = "Testando 2";
        usuario.setNome(nomeNova);
        testEntityManager.persist(usuario);
        assertNotEquals(nomeAntiga, repositorio.findOne(usuario.getIdUsuario()).getNome());
        assertEquals(nomeNova, repositorio.findOne(usuario.getIdUsuario()).getNome());
    }

    private Usuario instanciarUsuario() {
        return new Usuario("Teste", "teste@teste.com", "Masculino", 2l, "senha");
    }

}
