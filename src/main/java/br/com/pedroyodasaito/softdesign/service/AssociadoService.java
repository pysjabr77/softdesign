package br.com.pedroyodasaito.softdesign.service;

import br.com.pedroyodasaito.softdesign.api.dto.associado.AssociadoDTO;
import br.com.pedroyodasaito.softdesign.api.dto.associado.AssociadoInserirAtualizarDTO;
import br.com.pedroyodasaito.softdesign.api.dto.pauta.PautaDTO;
import br.com.pedroyodasaito.softdesign.entity.Associado;
import br.com.pedroyodasaito.softdesign.entity.Pauta;

import java.util.Optional;

public interface AssociadoService {

    Associado criarAssociado(AssociadoInserirAtualizarDTO dto);
    Optional<AssociadoDTO> obterAssociado(Integer id);
    void atualizarAssociado(Associado associado);
    void deletarAssociado(Associado associado);
}
