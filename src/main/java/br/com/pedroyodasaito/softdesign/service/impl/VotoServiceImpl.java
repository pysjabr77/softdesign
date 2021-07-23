package br.com.pedroyodasaito.softdesign.service.impl;

import br.com.pedroyodasaito.softdesign.api.dto.voto.VotarDTO;
import br.com.pedroyodasaito.softdesign.entity.Associado;
import br.com.pedroyodasaito.softdesign.entity.Sessao;
import br.com.pedroyodasaito.softdesign.entity.Voto;
import br.com.pedroyodasaito.softdesign.exception.NegocioException;
import br.com.pedroyodasaito.softdesign.repository.AssociadoRepository;
import br.com.pedroyodasaito.softdesign.repository.SessaoRepository;
import br.com.pedroyodasaito.softdesign.repository.VotoRepository;
import br.com.pedroyodasaito.softdesign.service.VotoService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VotoServiceImpl implements VotoService {

    private final VotoRepository repository;

    private final AssociadoRepository associadoRepository;

    private final SessaoRepository sessaoRepository;

    public VotoServiceImpl(VotoRepository repository, AssociadoRepository associadoRepository,
                           SessaoRepository sessaoRepository) {
        this.repository = repository;
        this.sessaoRepository = sessaoRepository;
        this.associadoRepository = associadoRepository;
    }

    @Override
    public void votar(VotarDTO dto) {
        Voto voto = new Voto();
        voto.setVoto(dto.isVoto());
        voto.setSessao(validarSessao(dto.getSessaoId()));
        voto.setAssociado(validarAssociado(dto.getAssociadoId()));
        repository.save(voto);
    }

    private Sessao validarSessao(Integer sessaoId) {
        Optional<Sessao> optionalSessao = sessaoRepository.findById(sessaoId);
        if (!optionalSessao.isPresent()) {
            throw new NegocioException("Sessão não existe");
        }
        return optionalSessao.get();
    }

    private Associado validarAssociado(Integer associadoId) {
        Optional<Associado> optionalAssociado = associadoRepository.findById(associadoId);
        if (!optionalAssociado.isPresent()) {
            throw new NegocioException("Associado não existe");
        }
        return optionalAssociado.get();
    }
}
