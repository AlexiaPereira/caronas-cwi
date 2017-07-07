package br.com.crescer.caronas.Service;

import br.com.crescer.caronas.entity.Origem;
import br.com.crescer.caronas.repository.OrigemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chris
 */
@Service
public class OrigemService {

    @Autowired
    OrigemRepository origemRepository;

    public Iterable<Origem> findAll() {
        return origemRepository.findAll();
    }

    public Origem save(Origem origem) {
        return origemRepository.save(origem);
    }

    public Origem update(Origem origem) {
        return origemRepository.save(origem);
    }

    public void remove(Origem origem) {
        origemRepository.delete(origem);
    }

    public Origem loadById(Long id) {
        return origemRepository.findOne(id);
    }
}