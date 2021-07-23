package br.com.pedroyodasaito.softdesign.api.dto.contabiliza;

import br.com.pedroyodasaito.softdesign.entity.Pauta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContabilizacaoDTO {
    private Pauta pauta;
    private Integer totalVotos;
    private Integer votosSim;
    private Integer votosNao;
    private String statusVotacao;
}
