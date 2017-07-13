package br.com.crescer.caronas.repository;

import br.com.crescer.caronas.entity.Notificacao;
import br.com.crescer.caronas.entity.Usuario;
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
public class NotificacaoRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private NotificacaoRepository repositorio;

    /**
     * Test of save method, of class GeneroService.
     */
    @Test
    public void testSave() {
        final Notificacao notificacao = instanciarNotificacao();
        repositorio.save(notificacao);
        assertEquals(notificacao.getConteudo(), testEntityManager.find(Notificacao.class, notificacao.getIdNotificacao()).getConteudo());
    }

    /**
     * Test of findAll method, of class GeneroService.
     */
    @Test
    public void testFindAll() {
        final Notificacao notificacao = instanciarNotificacao();
        testEntityManager.persist(notificacao);

        assertTrue(StreamSupport.stream(repositorio.findAll().spliterator(), false)
                .map(Notificacao::getConteudo)
                .collect(toList())
                .contains(notificacao.getConteudo()));

    }

    /**
     * Test of findOne method, of class GeneroService.
     */
    @Test
    public void testFindOne() {
        final Notificacao notificacao = instanciarNotificacao();
        testEntityManager.persist(notificacao);
        assertEquals(notificacao.getConteudo(), repositorio.findOne(notificacao.getIdNotificacao()).getConteudo());
    }

    @Test
    public void testRemove() {
        final Notificacao notificacao = instanciarNotificacao();
        testEntityManager.persist(notificacao);
        repositorio.delete(notificacao);
        assertNull(repositorio.findOne(notificacao.getIdNotificacao()));
    }

    @Test
    public void testUpdate() {
        final Notificacao notificacao = instanciarNotificacao();
        String conteudoAntigo = notificacao.getConteudo();
        testEntityManager.persist(notificacao);
        String conteudoNovo = "Novo conteudo";
        notificacao.setConteudo(conteudoNovo);
        testEntityManager.persist(notificacao);
        assertNotEquals(conteudoAntigo, repositorio.findOne(notificacao.getIdNotificacao()).getConteudo());
        assertEquals(conteudoNovo, repositorio.findOne(notificacao.getIdNotificacao()).getConteudo());
    }

    private Notificacao instanciarNotificacao() {
        return new Notificacao("Notificacao teste", new Usuario("Teste", "teste@teste.com", "Masculino", "2", "senha"));
    }

}
