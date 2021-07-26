package br.com.pedroyodasaito.softdesign.api.v1.controller;

import br.com.pedroyodasaito.softdesign.api.v1.dto.pauta.PautaDTO;
import br.com.pedroyodasaito.softdesign.api.v1.dto.pauta.PautaInserirAtualizarDTO;
import br.com.pedroyodasaito.softdesign.entity.Pauta;
import br.com.pedroyodasaito.softdesign.service.PautaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/v1/pauta")
public class PautaController {

    private final PautaService service;

    public PautaController(PautaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer salvar(@RequestBody PautaInserirAtualizarDTO dto){
        return service.criarPauta(dto).getId();
    }

    @GetMapping("{id}")
    public PautaDTO obterPorId(@PathVariable Integer id){
        return service.obterPauta(id)
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND, "Pauta não encontrado."));
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody PautaInserirAtualizarDTO dto) {
        PautaDTO pautaDTO = service.obterPauta(id)
                .orElseThrow(() ->
                new ResponseStatusException(NOT_FOUND, "Pauta não encontrado."));

        Pauta pauta = new Pauta();
        pauta.setId(pautaDTO.getId());
        pauta.setNome(dto.getNome());

        service.atualizarPauta(pauta);
    }

}

