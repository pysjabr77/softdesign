package br.com.pedroyodasaito.softdesign.api.v1.controller;

import br.com.pedroyodasaito.softdesign.api.v1.dto.sessao.SessaoAbrirDTO;
import br.com.pedroyodasaito.softdesign.service.SessaoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/sessao")
public class SessaoController {

    private final SessaoService service;

    public SessaoController(SessaoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer abrir(@RequestBody SessaoAbrirDTO dto){
        return service.abrirSessao(dto).getId();
    }

}
