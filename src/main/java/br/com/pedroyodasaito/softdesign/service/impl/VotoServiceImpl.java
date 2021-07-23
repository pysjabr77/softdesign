package br.com.pedroyodasaito.softdesign.service.impl;

import br.com.pedroyodasaito.softdesign.api.dto.voto.VotarDTO;
import br.com.pedroyodasaito.softdesign.entity.Associado;
import br.com.pedroyodasaito.softdesign.entity.Sessao;
import br.com.pedroyodasaito.softdesign.entity.Voto;
import br.com.pedroyodasaito.softdesign.repository.AssociadoRepository;
import br.com.pedroyodasaito.softdesign.repository.SessaoRepository;
import br.com.pedroyodasaito.softdesign.repository.VotoRepository;
import br.com.pedroyodasaito.softdesign.service.VotoService;
import org.springframework.stereotype.Service;

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
        Sessao sessao = sessaoRepository.findById(dto.getSessaoId()).get();
        voto.setSessao(sessao);
        Associado associado = associadoRepository.findById(dto.getAssociadoId()).get();
        voto.setAssociado(associado);

        repository.save(voto);
    }
}
