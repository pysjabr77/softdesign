package br.com.pedroyodasaito.softdesign.service.impl;

import br.com.pedroyodasaito.softdesign.api.v1.dto.voto.VotarDTO;
import br.com.pedroyodasaito.softdesign.entity.Associado;
import br.com.pedroyodasaito.softdesign.entity.Sessao;
import br.com.pedroyodasaito.softdesign.entity.Voto;
import br.com.pedroyodasaito.softdesign.exception.NegocioException;
import br.com.pedroyodasaito.softdesign.integracao.IntegracaoCpfValidatorService;
import br.com.pedroyodasaito.softdesign.integracao.dto.StatusVotoDTO;
import br.com.pedroyodasaito.softdesign.mensageria.voto.FilaEnvioVoto;
import br.com.pedroyodasaito.softdesign.repository.AssociadoRepository;
import br.com.pedroyodasaito.softdesign.repository.SessaoRepository;
import br.com.pedroyodasaito.softdesign.repository.VotoRepository;
import br.com.pedroyodasaito.softdesign.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class VotoServiceImpl implements VotoService {

    private final VotoRepository repository;

    private final AssociadoRepository associadoRepository;

    private final SessaoRepository sessaoRepository;

    @Autowired
    private FilaEnvioVoto filaEnvioVoto;

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

        validarHorarioDoVoto(voto);
        validarCpfVoto(voto.getAssociado());

        filaEnvioVoto.enviar(voto);
    }

    private void validarCpfVoto(Associado associado) {
        try {
            StatusVotoDTO statusVotoDTO = IntegracaoCpfValidatorService.validarCpf(associado.getCpf().replaceAll("[^0-9,]", ""));
            if (Objects.isNull(statusVotoDTO) || statusVotoDTO.getStatus().equals("UNABLE_TO_VOTE")) {
                throw new NegocioException("CPF não é valido para realizar a votação.");
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new NegocioException("CPF não encontrado para realizar a votação.");
            }
        }
    }

    @Override
    public void consolidarVoto(Voto voto) {
        repository.save(voto);
    }

    private void validarHorarioDoVoto(Voto voto) {
        if (LocalDateTime.now().isAfter(voto.getSessao().getFim())) {
            throw new NegocioException("Voto não será computado, pois foi realizado após o fim da sessão.");
        }
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
