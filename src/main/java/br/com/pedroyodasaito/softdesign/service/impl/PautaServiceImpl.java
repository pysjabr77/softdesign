package br.com.pedroyodasaito.softdesign.service.impl;

import br.com.pedroyodasaito.softdesign.api.dto.InserirAtualizarPautaDTO;
import br.com.pedroyodasaito.softdesign.api.dto.PautaDTO;
import br.com.pedroyodasaito.softdesign.entity.Pauta;
import br.com.pedroyodasaito.softdesign.repository.PautaRepository;
import br.com.pedroyodasaito.softdesign.service.PautaService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PautaServiceImpl implements PautaService {

    private final PautaRepository repository;

    public PautaServiceImpl(PautaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pauta criarPauta(InserirAtualizarPautaDTO dto) {
        Pauta pauta = new Pauta();
        pauta.setNome(dto.getNome());
        return this.repository.save(pauta);
    }

    @Override
    public Optional<PautaDTO> obterPauta(Integer id) {
        Optional<Pauta> pauta = this.repository.findById(id);
        return pauta
                .map(p -> {
                    PautaDTO pautaDTO = new PautaDTO();
                    pautaDTO.setId(p.getId());
                    pautaDTO.setNome(p.getNome());
                    return pautaDTO;
                });
    }

    @Override
    public void atualizarPauta(Pauta pauta) {
        this.repository.save(pauta);
    }
}
