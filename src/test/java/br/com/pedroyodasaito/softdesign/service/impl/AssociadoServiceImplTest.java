package br.com.pedroyodasaito.softdesign.service.impl;

import br.com.pedroyodasaito.softdesign.api.v1.dto.associado.AssociadoInserirAtualizarDTO;
import br.com.pedroyodasaito.softdesign.entity.Associado;
import br.com.pedroyodasaito.softdesign.repository.AssociadoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AssociadoServiceImplTest {

    @Autowired
    private AssociadoRepository repository;

    @Autowired
    private AssociadoServiceImpl service;

    @Test
    void criar() {
        AssociadoInserirAtualizarDTO associadoInserirAtualizarDTO = new AssociadoInserirAtualizarDTO();
        associadoInserirAtualizarDTO.setNome("Associado 1");
        associadoInserirAtualizarDTO.setCpf("373.761.410-57");

        Associado associado = service.criarAssociado(associadoInserirAtualizarDTO);

        assertThat(associado.getNome()).isSameAs(associadoInserirAtualizarDTO.getNome());

        repository.delete(associado);
    }


}
