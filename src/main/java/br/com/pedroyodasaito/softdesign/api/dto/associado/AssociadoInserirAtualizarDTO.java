package br.com.pedroyodasaito.softdesign.api.dto.associado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociadoInserirAtualizarDTO {
    private String nome;
    private String cpf;
}
