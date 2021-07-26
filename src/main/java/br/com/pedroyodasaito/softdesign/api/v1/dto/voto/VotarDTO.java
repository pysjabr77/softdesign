package br.com.pedroyodasaito.softdesign.api.v1.dto.voto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotarDTO {
    private Integer sessaoId;
    private Integer associadoId;
    private boolean voto;
}
