package br.com.crescer.caronas.repository;

import br.com.crescer.caronas.entity.Notificacao;
import br.com.crescer.caronas.entity.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author alexia.pereira
 */
public interface NotificacaoRepository extends PagingAndSortingRepository<Notificacao, Long> {

    Long deleteByUsuario(Usuario usuario);
}
