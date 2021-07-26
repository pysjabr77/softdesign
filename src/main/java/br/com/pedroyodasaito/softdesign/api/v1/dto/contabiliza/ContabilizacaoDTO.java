package br.com.pedroyodasaito.softdesign.api.v1.dto.contabiliza;

import br.com.pedroyodasaito.softdesign.entity.Pauta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContabilizacaoDTO implements Serializable {
    private Pauta pauta;
    private Integer totalVotos;
    private Integer votosSim;
    private Integer votosNao;
    private String statusVotacao;
}
