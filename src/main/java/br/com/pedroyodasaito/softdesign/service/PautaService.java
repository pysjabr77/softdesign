package br.com.pedroyodasaito.softdesign.service;

import br.com.pedroyodasaito.softdesign.api.dto.pauta.PautaDTO;
import br.com.pedroyodasaito.softdesign.api.dto.pauta.PautaInserirAtualizarDTO;
import br.com.pedroyodasaito.softdesign.entity.Pauta;

import java.util.Optional;

public interface PautaService {

    Pauta criarPauta(PautaInserirAtualizarDTO dto);
    Optional<PautaDTO> obterPauta(Integer id);
    void atualizarPauta(Pauta pauta);
}
