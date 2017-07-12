package br.com.crescer.caronas.repository;

import br.com.crescer.caronas.entity.Destino;
import br.com.crescer.caronas.entity.DiaSemana;
import br.com.crescer.caronas.entity.Origem;
import br.com.crescer.caronas.entity.Rotina;
import br.com.crescer.caronas.entity.RotinaDiaSemana;
import br.com.crescer.caronas.entity.Solicitacao;
import br.com.crescer.caronas.entity.Usuario;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.StreamSupport;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alexia.pereira
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.REQUIRED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class SolicitacaoRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private SolicitacaoRepository repositorio;

    /**
     * Test of save method, of class GeneroService.
     */
    @Test
    public void testSave() {
        final Solicitacao solicitacao = instanciarSolicitacao();
        repositorio.save(solicitacao);
        assertEquals(solicitacao.getUsuarioDono().getNome(),
                testEntityManager.find(Solicitacao.class, solicitacao.getIdSolicitacao()).getUsuarioDono().getNome());
    }

    /**
     * Test of findAll method, of class GeneroService.
     */
    @Test
    public void testFindAll() {
        final Solicitacao solicitacao = instanciarSolicitacao();
        testEntityManager.persist(solicitacao);

        assertTrue(StreamSupport.stream(repositorio.findAll().spliterator(), false)
                .map(Solicitacao::getUsuarioDono)
                .map(Usuario::getNome)
                .collect(toList())
                .contains(solicitacao.getUsuarioDono().getNome()));

    }

    /**
     * Test of findOne method, of class GeneroService.
     */
    @Test
    public void testFindOne() {
        final Solicitacao solicitacao = instanciarSolicitacao();
        testEntityManager.persist(solicitacao);
        assertEquals(solicitacao.getUsuarioDono().getNome(),
                repositorio.findOne(solicitacao.getIdSolicitacao()).getUsuarioDono().getNome());
    }

    @Test
    public void testRemove() {
        final Solicitacao solicitacao = instanciarSolicitacao();
        testEntityManager.persist(solicitacao);
        repositorio.delete(solicitacao);
        assertNull(repositorio.findOne(solicitacao.getIdSolicitacao()));
    }

    @Test
    public void testUpdate() {
        final Solicitacao solicitacao = instanciarSolicitacao();
        Long idAntigo = solicitacao.getUsuarioAlvo().getIdUsuario();
        testEntityManager.persist(solicitacao);
        Usuario novoUsuario = new Usuario("oi", "oi@oi.com", "feminino", "2487374", "teste");
        solicitacao.setUsuarioAlvo(novoUsuario);
        testEntityManager.persist(solicitacao);
        assertNotEquals(idAntigo, repositorio.findOne(solicitacao.getIdSolicitacao()).getUsuarioAlvo().getIdUsuario());
        assertEquals(novoUsuario.getNome(), repositorio.findOne(solicitacao.getIdSolicitacao()).getUsuarioAlvo().getNome());
    }

    @Test
    public void testFindByUsuarioAlvo() {
        final Solicitacao solicitacao = instanciarSolicitacao();
        testEntityManager.persist(solicitacao);
        final Solicitacao outraSolicitacao = instanciarSolicitacao();
        Usuario outroUsuarioAlvo = new Usuario("Novo Usuario", "teste@teste.com", "Masculino", "7686", "senha");
        outraSolicitacao.setUsuarioAlvo(outroUsuarioAlvo);
        outraSolicitacao.setUsuarioDono(solicitacao.getUsuarioDono());
        testEntityManager.persist(outraSolicitacao);
        final List<Solicitacao> retorno = repositorio.findByUsuarioAlvo(solicitacao.getUsuarioAlvo());
        assertFalse(retorno.size() > 1);
        assertEquals(retorno.get(0).getIdSolicitacao(), solicitacao.getIdSolicitacao());
    }

    private Solicitacao instanciarSolicitacao() {
        Usuario usuarioDono = new Usuario("Teste", "teste@teste.com", "Masculino", "7665654", "senha");
        Usuario usuarioAlvo = new Usuario("Teste Alvo", "testealvo@teste.com", "Feminino", "0989809", "senha2");
        return new Solicitacao(usuarioDono, usuarioAlvo, instanciarRotina(usuarioDono));
    }

    private Rotina instanciarRotina(Usuario usuarioDono) {
        Destino destino = new Destino("destino", BigDecimal.ONE, BigDecimal.ONE);
        Origem origem = new Origem("origem", BigDecimal.ONE, BigDecimal.ONE);
        List<RotinaDiaSemana> listaDeDias = new ArrayList<>();
        listaDeDias.add(new RotinaDiaSemana(5, new DiaSemana("SEGUNDA")));
        Rotina rotina = new Rotina(true, new Date(), BigDecimal.TEN, BigDecimal.ZERO, listaDeDias, destino, origem, usuarioDono);
        for (RotinaDiaSemana rotinaDiaSemana : listaDeDias) {
            rotinaDiaSemana.setRotina(rotina);
        }

        return rotina;
    }

}
