package br.com.crescer.caronas.repository;

import br.com.crescer.caronas.entity.Rotina;
import br.com.crescer.caronas.entity.Usuario;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author chris
 */
public interface RotinaRepository extends CrudRepository<Rotina, Long>{
    
    public List<Rotina> findByPassageiro(Boolean bool);
    
    public List<Rotina> findByUsuario (Usuario usuario);
    
}
