package br.com.crescer.caronas.service;

import br.com.crescer.caronas.entity.Grupo;
import br.com.crescer.caronas.entity.Rotina;
import br.com.crescer.caronas.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chris
 */
@Service
public class GrupoService {

    @Autowired
    GrupoRepository grupoRepository;

    public Iterable<Grupo> findAll() {
        return grupoRepository.findAll();
    }

    public Grupo save(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    public Grupo update(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    public void remove(Grupo grupo) {
        grupoRepository.delete(grupo);
    }

    public Grupo loadById(Long id) {
        return grupoRepository.findOne(id);
    }

    public Grupo loadByRotina(Rotina rotina) {
        return grupoRepository.findOneByRotina(rotina);
    }

}
