package br.com.crescer.caronas.Service;

import br.com.crescer.caronas.entity.DiaSemana;
import br.com.crescer.caronas.repository.DiaSemanaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chris
 */
@Service
public class DiaSemanaService {

    @Autowired
    DiaSemanaRepository diaSemanaRepository;

    public Iterable<DiaSemana> findAll() {
        return diaSemanaRepository.findAll();
    }

    public DiaSemana save(DiaSemana diaSemana) {
        return diaSemanaRepository.save(diaSemana);
    }

    public DiaSemana update(DiaSemana usuario) {
        return diaSemanaRepository.save(usuario);
    }

    public void remove(DiaSemana diaSemana) {
        diaSemanaRepository.delete(diaSemana);
    }

    public DiaSemana loadById(Long id) {
        return diaSemanaRepository.findOne(id);
    }
}