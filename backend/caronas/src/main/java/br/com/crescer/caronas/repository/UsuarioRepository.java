package br.com.crescer.caronas.repository;

import br.com.crescer.caronas.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author chris
 */
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
    
    public Usuario findByEmail (String email);
    
}
