package br.com.pedroyodasaito.softdesign.service.impl;

import br.com.pedroyodasaito.softdesign.api.dto.sessao.SessaoAbrirDTO;
import br.com.pedroyodasaito.softdesign.entity.Pauta;
import br.com.pedroyodasaito.softdesign.entity.Sessao;
import br.com.pedroyodasaito.softdesign.exception.NegocioException;
import br.com.pedroyodasaito.softdesign.repository.PautaRepository;
import br.com.pedroyodasaito.softdesign.repository.SessaoRepository;
import br.com.pedroyodasaito.softdesign.service.SessaoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

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
        validarInicoSessao(dto.getInicio(), dto.getFim());

        Sessao sessao = new Sessao();
        sessao.setDescricao(dto.getDescricao());
        sessao.setInico(dto.getInicio());
        sessao.setFim(validarFimSessao(dto.getInicio(), dto.getFim()));
        sessao.setPauta(validarPauta(dto.getPautaId()));

        return repository.save(sessao);
    }

    private void validarInicoSessao(LocalDateTime inicio, LocalDateTime fim) {
        if (Objects.isNull(fim)) {
            throw new NegocioException("Data/hora de inicio da sessão é obrigatório.");
        }
        if (LocalDateTime.now().isAfter(inicio)) {
            throw new NegocioException("Data/hora de inicio da sessão é anterior a data atual.");
        }
        if (inicio.isAfter(fim)) {
            throw new NegocioException("Data/hora de inicio da sessão é maior que a data fim.");
        }
    }

    private LocalDateTime validarFimSessao(LocalDateTime inicio, LocalDateTime fim) {
        if (Objects.isNull(fim)) {
            return inicio.plusMinutes(1);
        }
        return fim;
    }

    private Pauta validarPauta(Integer pautaId) {
        Optional<Pauta> optionalPauta = pautaRepository.findById(pautaId);
        if (!optionalPauta.isPresent()) {
            throw new NegocioException("Pauta não existe");
        }
        return optionalPauta.get();
    }

}
