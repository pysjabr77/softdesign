package br.com.pedroyodasaito.softdesign.api.v1.dto.pauta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PautaDTO  implements Serializable {
    private Integer id;
    private String nome;
}
