package br.com.pedroyodasaito.softdesign.api.v1.controller;

import br.com.pedroyodasaito.softdesign.api.dto.associado.AssociadoDTO;
import br.com.pedroyodasaito.softdesign.api.dto.associado.AssociadoInserirAtualizarDTO;
import br.com.pedroyodasaito.softdesign.entity.Associado;
import br.com.pedroyodasaito.softdesign.service.AssociadoService;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/v1/associado")
public class AssociadoController {

    private final AssociadoService service;

    public AssociadoController(AssociadoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer salvar(@RequestBody AssociadoInserirAtualizarDTO dto){
        return service.criarAssociado(dto).getId();
    }

    @GetMapping("{id}")
    public AssociadoDTO obterPorId(@PathVariable Integer id){
        return service.obterAssociado(id)
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND, "Associado não encontrado."));
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody AssociadoInserirAtualizarDTO dto) {
        AssociadoDTO associadoDTO = service.obterAssociado(id)
                .map(a -> {
                    a.setId(id);
                    a.setNome(dto.getNome());
                    a.setCpf(dto.getCpf());
                    return a;
                })
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Associado não encontrado."));

        service.atualizarAssociado(mapperDtoToEntity(associadoDTO));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        AssociadoDTO associadoDTO = service.obterAssociado(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Associado não encontrado."));

        service.deletarAssociado(mapperDtoToEntity(associadoDTO));
    }

    private Associado mapperDtoToEntity(AssociadoDTO associadoDTO) {
        Associado associado = new Associado();
        associado.setId(associadoDTO.getId());
        associado.setNome(associadoDTO.getNome());
        associado.setCpf(associadoDTO.getCpf());
        return associado;
    }
}

