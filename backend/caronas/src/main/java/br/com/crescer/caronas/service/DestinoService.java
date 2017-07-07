package br.com.crescer.caronas.Service;

import br.com.crescer.caronas.entity.Destino;
import br.com.crescer.caronas.repository.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chris
 */
@Service
public class DestinoService {

    @Autowired
    DestinoRepository destinoRepository;

    public Iterable<Destino> findAll() {
        return destinoRepository.findAll();
    }

    public Destino save(Destino destino) {
        return destinoRepository.save(destino);
    }

    public Destino update(Destino destino) {
        return destinoRepository.save(destino);
    }

    public void remove(Destino destino) {
        destinoRepository.delete(destino);
    }

    public Destino loadById(Long id) {
        return destinoRepository.findOne(id);
    }
}