package br.com.pedroyodasaito.softdesign.service.impl;

import br.com.pedroyodasaito.softdesign.api.v1.dto.contabiliza.ContabilizacaoDTO;
import br.com.pedroyodasaito.softdesign.api.v1.dto.sessao.SessaoAbrirDTO;
import br.com.pedroyodasaito.softdesign.entity.Pauta;
import br.com.pedroyodasaito.softdesign.entity.Sessao;
import br.com.pedroyodasaito.softdesign.exception.NegocioException;
import br.com.pedroyodasaito.softdesign.mensageria.contabilizavotacao.FilaEnvioContabilizaVotacao;
import br.com.pedroyodasaito.softdesign.repository.PautaRepository;
import br.com.pedroyodasaito.softdesign.repository.SessaoRepository;
import br.com.pedroyodasaito.softdesign.service.ContabilizaService;
import br.com.pedroyodasaito.softdesign.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class SessaoServiceImpl implements SessaoService {
    private final SessaoRepository repository;

    private final PautaRepository pautaRepository;

    private final ContabilizaService contabilizaService;

    @Autowired
    private FilaEnvioContabilizaVotacao filaEnvioContabilizaVotacao;

    public SessaoServiceImpl(SessaoRepository repository, PautaRepository pautaRepository, ContabilizaService contabilizaService) {
        this.repository = repository;
        this.pautaRepository = pautaRepository;
        this.contabilizaService = contabilizaService;
    }

    @Override
    public Sessao abrirSessao(SessaoAbrirDTO dto) {
        validarInicoSessao(dto.getInicio(), dto.getFim());

        Sessao sessao = new Sessao();
        sessao.setDescricao(dto.getDescricao());
        sessao.setInico(dto.getInicio());
        sessao.setFim(validarFimSessao(dto.getInicio(), dto.getFim()));
        sessao.setPauta(validarPauta(dto.getPautaId()));

        sessao =  repository.save(sessao);

        agendarContabilizadorVotos(sessao);

        return sessao;
    }

    private void agendarContabilizadorVotos(Sessao sessao) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ContabilizacaoDTO contabilizacaoDTO = contabilizaService.contabilizarVotacao(sessao.getId());
                filaEnvioContabilizaVotacao.enviar(contabilizacaoDTO);
            }
        }, Date.from(sessao.getFim().plusMinutes(1).atZone(ZoneId.systemDefault()).toInstant()));
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
