package br.com.crescer.caronas.repository;

import br.com.crescer.caronas.entity.Grupo;
import br.com.crescer.caronas.entity.Rotina;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author chris
 */
public interface GrupoRepository extends CrudRepository<Grupo, Long> {

    Grupo findOneByRotina(Rotina rotina);
}
