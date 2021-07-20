package br.com.pedroyodasaito.softdesign.service;

import br.com.pedroyodasaito.softdesign.api.dto.InserirAtualizarPautaDTO;
import br.com.pedroyodasaito.softdesign.api.dto.PautaDTO;
import br.com.pedroyodasaito.softdesign.entity.Pauta;

import java.util.Optional;

public interface PautaService {

    Pauta criarPauta(InserirAtualizarPautaDTO dto);
    Optional<PautaDTO> obterPauta(Integer id);
    void atualizarPauta(Pauta pauta);
}
