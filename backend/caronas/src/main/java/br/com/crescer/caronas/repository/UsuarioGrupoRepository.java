package br.com.crescer.caronas.repository;

import br.com.crescer.caronas.entity.Grupo;
import br.com.crescer.caronas.entity.Usuario;
import br.com.crescer.caronas.entity.UsuarioGrupo;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author chris
 */
public interface UsuarioGrupoRepository extends CrudRepository<UsuarioGrupo, Long> {

    List<UsuarioGrupo> findByUsuario(Usuario usuario);

    public void deleteByGrupo(Grupo grupo);

    public UsuarioGrupo findByUsuarioAndGrupo(Usuario usuario, Grupo grupo);

    public List<UsuarioGrupo> findByGrupo(Grupo grupo);

}
