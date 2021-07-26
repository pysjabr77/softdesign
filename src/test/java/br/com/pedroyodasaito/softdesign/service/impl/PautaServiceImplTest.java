package br.com.pedroyodasaito.softdesign.service.impl;

import br.com.pedroyodasaito.softdesign.api.v1.dto.pauta.PautaInserirAtualizarDTO;
import br.com.pedroyodasaito.softdesign.entity.Pauta;
import br.com.pedroyodasaito.softdesign.repository.PautaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PautaServiceImplTest {
    @Autowired
    private PautaRepository repository;

    @Autowired
    private PautaServiceImpl service;

    @Test
    void criar() {
        PautaInserirAtualizarDTO pautaInserirAtualizarDTO = new PautaInserirAtualizarDTO();
        pautaInserirAtualizarDTO.setNome("Associado 1");

        Pauta pauta = service.criarPauta(pautaInserirAtualizarDTO);

        assertThat(pauta.getNome()).isSameAs(pautaInserirAtualizarDTO.getNome());

        repository.delete(pauta);
    }
}
