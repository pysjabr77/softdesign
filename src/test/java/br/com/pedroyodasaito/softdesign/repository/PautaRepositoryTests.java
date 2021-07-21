package br.com.pedroyodasaito.softdesign.repository;

import br.com.pedroyodasaito.softdesign.entity.Pauta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PautaRepositoryTests {

    @Autowired
    private PautaRepository repository;

    @Test
    void insertPauta() {
        Pauta pauta = new Pauta();
        pauta.setNome("Pauta 1");
        repository.save(pauta);
        Integer countPauta = repository.findAll().size();
        assertEquals(1, countPauta);
    }

    @Test
    void atualizarPauta() {
        Pauta pauta = new Pauta();
        pauta.setNome("Pauta 1");
        repository.save(pauta);

        pauta.setNome("Pauta 1 Atualizado");
        repository.save(pauta);

        Optional<Pauta> pautaAtualizado = repository.findById(pauta.getId());

        assertTrue(pautaAtualizado.isPresent());
        assertEquals("Pauta 1 Atualizado", pautaAtualizado.get().getNome());
    }

    @Test
    void deletarPauta() {
        Pauta pauta = new Pauta();
        pauta.setNome("Pauta 1");
        repository.save(pauta);

        Integer id = pauta.getId();

        repository.delete(pauta);

        Optional<Pauta> pautaAtualizado = repository.findById(id);

        assertFalse(pautaAtualizado.isPresent());
    }
}
