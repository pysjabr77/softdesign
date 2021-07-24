package br.com.pedroyodasaito.softdesign.service.impl;

import br.com.pedroyodasaito.softdesign.api.dto.contabiliza.ContabilizacaoDTO;
import br.com.pedroyodasaito.softdesign.entity.Sessao;
import br.com.pedroyodasaito.softdesign.entity.Voto;
import br.com.pedroyodasaito.softdesign.exception.NegocioException;
import br.com.pedroyodasaito.softdesign.repository.SessaoRepository;
import br.com.pedroyodasaito.softdesign.repository.VotoRepository;
import br.com.pedroyodasaito.softdesign.service.ContabilizaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContabilizaServiceImpl implements ContabilizaService {

    private final SessaoRepository sessaoRepository;

    private final VotoRepository votoRepository;

    public ContabilizaServiceImpl(SessaoRepository sessaoRepository, VotoRepository votoRepository) {
        this.sessaoRepository = sessaoRepository;
        this.votoRepository = votoRepository;
    }

    @Override
    public ContabilizacaoDTO contabilizarVotacao(Integer sessaoId) {
        Sessao sessao = validarSessao(sessaoId);

        List<Voto> listaDeVotos = votoRepository.obterVotosPorPauta(sessao);

        ContabilizacaoDTO dto = new ContabilizacaoDTO();
        dto.setPauta(sessao.getPauta());
        dto.setTotalVotos(listaDeVotos.size());

        int votosSim = (int) listaDeVotos.stream().filter(Voto::isVoto).count();
        dto.setVotosSim(votosSim);
        dto.setVotosNao(listaDeVotos.size() - votosSim);

        if (dto.getTotalVotos() <= 0) dto.setStatusVotacao("Votação não aprovada [nenhum voto foi computado]");
        else dto.setStatusVotacao(votosSim > dto.getVotosNao() ? "Votação aprovada" : "Votação não aprovada");

        return dto;
    }

    private Sessao validarSessao(Integer sessaoId) {
        Optional<Sessao> optionalSessao = sessaoRepository.findById(sessaoId);
        if (!optionalSessao.isPresent()) {
            throw new NegocioException("Sessão não existe");
        }
        return optionalSessao.get();
    }
}
