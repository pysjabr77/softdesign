package br.com.pedroyodasaito.softdesign.service.impl;

import br.com.pedroyodasaito.softdesign.api.dto.contabiliza.ContabilizacaoDTO;
import br.com.pedroyodasaito.softdesign.entity.Sessao;
import br.com.pedroyodasaito.softdesign.entity.Voto;
import br.com.pedroyodasaito.softdesign.repository.SessaoRepository;
import br.com.pedroyodasaito.softdesign.repository.VotoRepository;
import br.com.pedroyodasaito.softdesign.service.ContabilizaService;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Sessao sessao = sessaoRepository.getById(sessaoId);

        List<Voto> listaDeVotos = votoRepository.obterVotosPorPauta(sessao);

        ContabilizacaoDTO dto = new ContabilizacaoDTO();
        dto.setPauta(sessao.getPauta());
        dto.setTotalVotos(listaDeVotos.size());

        int votosSim = (int) listaDeVotos.stream().filter(Voto::isVoto).count();
        dto.setVotosSim(votosSim);
        dto.setVotosNao(listaDeVotos.size() - votosSim);

        dto.setStatusVotacao(votosSim > dto.getVotosNao()? "Votação aprovada" : "Votação não aprovada");

        return dto;
    }
}
