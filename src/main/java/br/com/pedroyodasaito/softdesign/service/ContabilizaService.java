package br.com.pedroyodasaito.softdesign.service;

import br.com.pedroyodasaito.softdesign.api.dto.contabiliza.ContabilizacaoDTO;

public interface ContabilizaService {
    ContabilizacaoDTO contabilizarVotacao(Integer sessaoId);
}
