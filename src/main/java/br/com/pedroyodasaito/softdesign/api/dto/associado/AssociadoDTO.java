package br.com.pedroyodasaito.softdesign.api.dto.associado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociadoDTO {
    private Integer id;
    private String nome;
    private String cpf;
}
