package br.com.pedroyodasaito.softdesign.api.dto.pauta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PautaDTO {
    private Integer id;
    private String nome;
}