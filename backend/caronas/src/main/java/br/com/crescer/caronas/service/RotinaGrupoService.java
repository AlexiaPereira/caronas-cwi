package br.com.crescer.caronas.Service;

import br.com.crescer.caronas.entity.RotinaGrupo;
import br.com.crescer.caronas.repository.RotinaGrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chris
 */
@Service
public class RotinaGrupoService {

    @Autowired
    RotinaGrupoRepository rotinaGrupoRepository;

    public Iterable<RotinaGrupo> findAll() {
        return rotinaGrupoRepository.findAll();
    }

    public RotinaGrupo save(RotinaGrupo rotinaGrupo) {
        return rotinaGrupoRepository.save(rotinaGrupo);
    }

    public RotinaGrupo update(RotinaGrupo rotinaGrupo) {
        return rotinaGrupoRepository.save(rotinaGrupo);
    }

    public void remove(RotinaGrupo rotinaGrupo) {
        rotinaGrupoRepository.delete(rotinaGrupo);
    }

    public RotinaGrupo loadById(Long id) {
        return rotinaGrupoRepository.findOne(id);
    }
}