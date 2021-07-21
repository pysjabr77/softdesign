package br.com.pedroyodasaito.softdesign.repository;

import br.com.pedroyodasaito.softdesign.entity.Associado;
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
class AssociadoRepositoryTests {

    @Autowired
    private AssociadoRepository repository;

    @Test
    void insertAssociado() {
        Associado associado = new Associado();
        associado.setNome("Associado 1");
        associado.setCpf("373.761.410-57");
        repository.save(associado);
        Integer countAssociado = repository.findAll().size();
        assertEquals(1, countAssociado);
    }

    @Test
    void atualizarAssociado() {
        Associado associado = new Associado();
        associado.setNome("Associado 1");
        associado.setCpf("373.761.410-57");
        repository.save(associado);

        associado.setNome("Associado 1 Atualizado");
        repository.save(associado);

        Optional<Associado> associadoAtualizado = repository.findById(associado.getId());

        assertTrue(associadoAtualizado.isPresent());
        assertEquals("Associado 1 Atualizado", associadoAtualizado.get().getNome());
    }

    @Test
    void deletarAssociado() {
        Associado associado = new Associado();
        associado.setNome("Associado 1");
        associado.setCpf("373.761.410-57");
        repository.save(associado);

        Integer id = associado.getId();

        repository.delete(associado);

        Optional<Associado> associadoAtualizado = repository.findById(id);

        assertFalse(associadoAtualizado.isPresent());
    }
}
