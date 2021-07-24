package br.com.pedroyodasaito.softdesign.api.dto.sessao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessaoAbrirDTO {
    private String descricao;

    private Integer pautaId;

    private LocalDateTime inicio;

    private LocalDateTime fim;
}
