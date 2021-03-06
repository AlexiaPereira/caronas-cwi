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
import static org.junit.Assert.assertFalse;
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
public class RotinaRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private RotinaRepository repositorio;

    @Autowired
    private UsuarioRepository usuarioRepositorio;

    /**
     * Test of save method, of class GeneroService.
     */
    @Test
    public void testSave() {
        final Rotina rotina = instanciarRotina();
        repositorio.save(rotina);
        assertEquals(rotina.getUsuario().getNome(), testEntityManager.find(Rotina.class, rotina.getIdRotina()).getUsuario().getNome());
    }

    /**
     * Test of findAll method, of class GeneroService.
     */
    @Test
    public void testFindAll() {
        final Rotina rotina = instanciarRotina();
        testEntityManager.persist(rotina);
        assertTrue(StreamSupport.stream(repositorio.findAll().spliterator(), false)
                .map(Rotina::getUsuario)
                .map(Usuario::getNome)
                .collect(toList())
                .contains(rotina.getUsuario().getNome()));

    }

    /**
     * Test of findOne method, of class GeneroService.
     */
    @Test
    public void testFindOne() {
        final Rotina rotina = instanciarRotina();
        testEntityManager.persist(rotina);
        assertEquals(rotina.getUsuario().getNome(), repositorio.findOne(rotina.getIdRotina()).getUsuario().getNome());
    }

    @Test
    public void testFindByUsuario() {
        final Rotina rotina = instanciarRotina();
        testEntityManager.persist(rotina);
        final List<Rotina> listaRotina = new ArrayList<>();
        listaRotina.add(rotina);

        assertEquals(listaRotina, repositorio.findByUsuario(usuarioRepositorio.findByIdAutorizacao("2")));

        Usuario donoDasRotinas = new Usuario("Dono", "dono@dono.com", "masc", "10", "teste");
        testEntityManager.persist(donoDasRotinas);

        final Rotina rotinaDois = instanciarRotina();
        rotinaDois.setUsuario(donoDasRotinas);
        testEntityManager.persist(rotinaDois);

        final Rotina segundaRotina = instanciarRotina();
        segundaRotina.setDistancia(BigDecimal.TEN);
        segundaRotina.setUsuario(donoDasRotinas);
        testEntityManager.persist(segundaRotina);

        final Rotina rotinaComOutroUsuario = instanciarRotina();
        Usuario novoUsuario = new Usuario("oi", "oi@oi.com", "feminino", "1", "teste");
        rotinaComOutroUsuario.setUsuario(novoUsuario);
        testEntityManager.persist(rotinaComOutroUsuario);

        assertFalse(repositorio.findByUsuario(rotinaDois.getUsuario())
                .stream()
                .map(Rotina::getIdRotina)
                .collect(toList())
                .contains(rotinaComOutroUsuario.getIdRotina()));

        assertEquals(2, repositorio.findByUsuario(rotinaDois.getUsuario()).size());

        assertTrue(repositorio.findByUsuario(rotinaDois.getUsuario())
                .stream()
                .map(Rotina::getUsuario)
                .map(Usuario::getIdUsuario)
                .collect(toList())
                .contains(rotinaDois.getUsuario().getIdUsuario()));

    }

    @Test
    public void testFindByPassageiro() {
        final Rotina rotina = instanciarRotina();
        testEntityManager.persist(rotina);
        final List<Rotina> listaRotina = new ArrayList<>();
        listaRotina.add(rotina);
        assertEquals(listaRotina, repositorio.findByPassageiro(rotina.getPassageiro()));
    }

    @Test
    public void testRemove() {
        final Rotina rotina = instanciarRotina();
        testEntityManager.persist(rotina);
        repositorio.delete(rotina);
        assertNull(repositorio.findOne(rotina.getIdRotina()));
    }

    @Test
    public void testUpdate() {
        final Rotina rotina = instanciarRotina();
        Long idAntigo = rotina.getUsuario().getIdUsuario();
        testEntityManager.persist(rotina);
        Usuario novoUsuario = new Usuario("oi", "oi@oi.com", "feminino", "1", "teste");
        rotina.setUsuario(novoUsuario);
        testEntityManager.persist(rotina);
        assertNotEquals(idAntigo, repositorio.findOne(rotina.getIdRotina()).getUsuario().getIdUsuario());
        assertEquals(novoUsuario.getNome(), repositorio.findOne(rotina.getIdRotina()).getUsuario().getNome());
    }

    @Test
    public void testFindByUsusarioAndPassangeiro() {
        final Rotina rotina = instanciarRotina();
        testEntityManager.persist(rotina);

        Usuario donoDasRotinas = new Usuario("Dono", "dono@dono.com", "masc", "10", "teste");
        testEntityManager.persist(donoDasRotinas);

        final Rotina rotinaDois = instanciarRotina();
        rotinaDois.setUsuario(donoDasRotinas);
        testEntityManager.persist(rotinaDois);

        final Rotina segundaRotina = instanciarRotina();
        segundaRotina.setDistancia(BigDecimal.TEN);
        segundaRotina.setUsuario(donoDasRotinas);
        segundaRotina.setPassageiro(false);
        testEntityManager.persist(segundaRotina);

        final Rotina rotinaComOutroUsuario = instanciarRotina();
        Usuario novoUsuario = new Usuario("oi", "oi@oi.com", "feminino", "1", "teste");
        rotinaComOutroUsuario.setUsuario(novoUsuario);
        testEntityManager.persist(rotinaComOutroUsuario);

        assertFalse(repositorio.findByUsuarioAndPassageiroAndDisponivel(rotinaDois.getUsuario(), true, true)
                .stream()
                .map(Rotina::getIdRotina)
                .collect(toList())
                .contains(rotinaComOutroUsuario.getIdRotina()));

        assertFalse(repositorio.findByUsuarioAndPassageiroAndDisponivel(rotinaDois.getUsuario(), true, true)
                .stream()
                .map(Rotina::getIdRotina)
                .collect(toList())
                .contains(segundaRotina.getIdRotina()));

        assertEquals(1, repositorio.findByUsuarioAndPassageiroAndDisponivel(rotinaDois.getUsuario(), true, true).size());
        assertEquals(1, repositorio.findByUsuarioAndPassageiroAndDisponivel(rotinaDois.getUsuario(), false, true).size());

        assertTrue(repositorio.findByUsuarioAndPassageiroAndDisponivel(rotinaDois.getUsuario(), true, true)
                .stream()
                .map(Rotina::getUsuario)
                .map(Usuario::getIdUsuario)
                .collect(toList())
                .contains(rotinaDois.getUsuario().getIdUsuario()));

    }

    private Rotina instanciarRotina() {
        Usuario usuario = new Usuario("Teste", "teste@teste.com", "Masculino", "2", "senha");
        Destino destino = new Destino("destino", BigDecimal.ONE, BigDecimal.ONE);
        Origem origem = new Origem("origem", BigDecimal.ONE, BigDecimal.ONE);
        List<RotinaDiaSemana> listaDeDias = new ArrayList<>();
        listaDeDias.add(new RotinaDiaSemana(5, new DiaSemana("SEGUNDA")));
        Rotina rotina = new Rotina(true, new Date(), BigDecimal.TEN, BigDecimal.ZERO, listaDeDias, destino, origem, usuario, true);
        for (RotinaDiaSemana rotinaDiaSemana : listaDeDias) {
            rotinaDiaSemana.setRotina(rotina);
        }

        return rotina;
    }

}
