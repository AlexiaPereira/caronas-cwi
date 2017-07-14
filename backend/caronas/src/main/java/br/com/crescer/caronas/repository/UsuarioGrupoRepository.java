package br.com.crescer.caronas.repository;

import br.com.crescer.caronas.entity.Usuario;
import br.com.crescer.caronas.entity.UsuarioGrupo;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author chris
 */
public interface UsuarioGrupoRepository extends CrudRepository<UsuarioGrupo, Long>{
    List<UsuarioGrupo> findByUsuario(Usuario usuario);
}
