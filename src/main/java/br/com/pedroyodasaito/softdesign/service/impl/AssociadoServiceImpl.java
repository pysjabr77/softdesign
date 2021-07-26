package br.com.pedroyodasaito.softdesign.service.impl;

import br.com.pedroyodasaito.softdesign.api.v1.dto.associado.AssociadoDTO;
import br.com.pedroyodasaito.softdesign.api.v1.dto.associado.AssociadoInserirAtualizarDTO;
import br.com.pedroyodasaito.softdesign.entity.Associado;
import br.com.pedroyodasaito.softdesign.exception.NegocioException;
import br.com.pedroyodasaito.softdesign.repository.AssociadoRepository;
import br.com.pedroyodasaito.softdesign.service.AssociadoService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AssociadoServiceImpl implements AssociadoService {
    private final AssociadoRepository repository;

    public AssociadoServiceImpl(AssociadoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Associado criarAssociado(AssociadoInserirAtualizarDTO dto) {
        validarCPF(dto.getCpf());
        existeAssociado(dto.getCpf());

        Associado associado = new Associado();
        associado.setNome(dto.getNome());
        associado.setCpf(dto.getCpf());

        return this.repository.save(associado);
    }

    private void validarCPF (String cpf) {
        if (Objects.isNull(cpf)) {
            throw new NegocioException("Cpf é obrigatório.");
        }
    }

    private void existeAssociado(String cpf) {
        Associado associado = this.repository.findAssociadoByCpf(cpf);
        if (Objects.nonNull(associado)) {
            throw new NegocioException("Associado já cadastrado.");
        }
    }

    @Override
    public Optional<AssociadoDTO> obterAssociado(Integer id) {
        Optional<Associado> associado = this.repository.findById(id);
        return associado
                .map(p -> {
                    AssociadoDTO associadoDTO = new AssociadoDTO();
                    associadoDTO.setId(p.getId());
                    associadoDTO.setNome(p.getNome());
                    associadoDTO.setCpf(p.getCpf());
                    return associadoDTO;
                });
    }

    @Override
    public void atualizarAssociado(Associado associado) {
        this.repository.save(associado);
    }

    @Override
    public void deletarAssociado(Associado associado) {
        this.repository.delete(associado);
    }
}
