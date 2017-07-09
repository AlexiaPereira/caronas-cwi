package br.com.crescer.caronas.repository;

import br.com.crescer.caronas.entity.Solicitacao;
import br.com.crescer.caronas.entity.Usuario;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author alexia.pereira
 */
public interface SolicitacaoRepository extends PagingAndSortingRepository<Solicitacao, Long>{
    List<Solicitacao> findByUsuarioAlvo(Usuario usuarioAlvo);
}
