package br.com.pedroyodasaito.softdesign.service;

import br.com.pedroyodasaito.softdesign.api.dto.sessao.SessaoAbrirDTO;
import br.com.pedroyodasaito.softdesign.entity.Sessao;

public interface SessaoService {

    Sessao abrirSessao(SessaoAbrirDTO dto);

}
