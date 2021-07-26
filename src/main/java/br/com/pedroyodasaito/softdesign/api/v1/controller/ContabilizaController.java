package br.com.pedroyodasaito.softdesign.api.v1.controller;

import br.com.pedroyodasaito.softdesign.api.v1.dto.contabiliza.ContabilizacaoDTO;
import br.com.pedroyodasaito.softdesign.service.ContabilizaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/contabiliza")
public class ContabilizaController {

    private final ContabilizaService service;

    public ContabilizaController(ContabilizaService service) {
        this.service = service;
    }

    @GetMapping("{sessaoId}")
    public ContabilizacaoDTO obterContabilizacaoDaVotacao(@PathVariable Integer sessaoId){
        return service.contabilizarVotacao(sessaoId);
    }

}
