package br.com.pedroyodasaito.softdesign.service.impl;

import br.com.pedroyodasaito.softdesign.api.dto.sessao.SessaoAbrirDTO;
import br.com.pedroyodasaito.softdesign.entity.Pauta;
import br.com.pedroyodasaito.softdesign.entity.Sessao;
import br.com.pedroyodasaito.softdesign.repository.PautaRepository;
import br.com.pedroyodasaito.softdesign.repository.SessaoRepository;
import br.com.pedroyodasaito.softdesign.service.SessaoService;
import org.springframework.stereotype.Service;

@Service
public class SessaoServiceImpl implements SessaoService {
    private final SessaoRepository repository;

    private final PautaRepository pautaRepository;

    public SessaoServiceImpl(SessaoRepository repository, PautaRepository pautaRepository) {
        this.repository = repository;
        this.pautaRepository = pautaRepository;
    }

    @Override
    public Sessao abrirSessao(SessaoAbrirDTO dto) {
        Sessao sessao = new Sessao();
        sessao.setDescricao(dto.getDescricao());
        sessao.setInico(dto.getInico());
        sessao.setFim(dto.getFim());

        Pauta pauta = pautaRepository.findById(dto.getPautaId()).get();
        sessao.setPauta(pauta);

        return repository.save(sessao);
    }
}
