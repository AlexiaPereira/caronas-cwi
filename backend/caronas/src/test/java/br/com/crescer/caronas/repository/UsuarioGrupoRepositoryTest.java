package br.com.crescer.caronas.repository;

import br.com.crescer.caronas.entity.UsuarioGrupo;
import br.com.crescer.caronas.entity.Usuario;
import br.com.crescer.caronas.entity.Grupo;
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
public class UsuarioGrupoRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UsuarioGrupoRepository repositorio;

    /**
     * Test of save method, of class GeneroService.
     */
    @Test
    public void testSave() {
        final UsuarioGrupo usuarioGrupo = instanciarUsuarioGrupo();
        repositorio.save(usuarioGrupo);
        assertEquals(usuarioGrupo.getUsuario().getNome(), testEntityManager.find(UsuarioGrupo.class, usuarioGrupo.getIdUsuarioGrupo()).getUsuario().getNome());
    }

    /**
     * Test of findAll method, of class GeneroService.
     */
    @Test
    public void testFindAll() {
        final UsuarioGrupo usuarioGrupo = instanciarUsuarioGrupo();
        testEntityManager.persist(usuarioGrupo);

        assertTrue(StreamSupport.stream(repositorio.findAll().spliterator(), false)
                .map(UsuarioGrupo::getUsuario)
                .map(Usuario::getNome)
                .collect(toList())
                .contains(usuarioGrupo.getUsuario().getNome()));

    }

    /**
     * Test of findOne method, of class GeneroService.
     */
    @Test
    public void testFindOne() {
        final UsuarioGrupo usuarioGrupo = instanciarUsuarioGrupo();
        testEntityManager.persist(usuarioGrupo);
        assertEquals(usuarioGrupo.getUsuario().getNome(), repositorio.findOne(usuarioGrupo.getIdUsuarioGrupo()).getUsuario().getNome());
    }

    @Test
    public void testRemove() {
        final UsuarioGrupo usuarioGrupo = instanciarUsuarioGrupo();
        testEntityManager.persist(usuarioGrupo);
        repositorio.delete(usuarioGrupo);
        assertNull(repositorio.findOne(usuarioGrupo.getIdUsuarioGrupo()));
    }

    @Test
    public void testUpdate() {
        final UsuarioGrupo usuarioGrupo = instanciarUsuarioGrupo();
        Long idAntigo = usuarioGrupo.getUsuario().getIdUsuario();
        testEntityManager.persist(usuarioGrupo);
        Usuario novoUsuario = new Usuario("oi", "oi@oi.com", "feminino", "1", "teste");
        usuarioGrupo.setIdUsuario(novoUsuario);
        testEntityManager.persist(usuarioGrupo);
        assertNotEquals(idAntigo, repositorio.findOne(usuarioGrupo.getIdUsuarioGrupo()).getUsuario().getIdUsuario());
        assertEquals(novoUsuario.getNome(), repositorio.findOne(usuarioGrupo.getIdUsuarioGrupo()).getUsuario().getNome());
    }

    private UsuarioGrupo instanciarUsuarioGrupo() {
        Usuario usuario = new Usuario("Teste", "teste@teste.com", "Masculino", "2", "senha");
        Grupo grupo = new Grupo("Nome do Grupo");
        testEntityManager.persist(usuario);
        testEntityManager.persist(grupo);
        return new UsuarioGrupo(usuario, grupo);
    }

}
