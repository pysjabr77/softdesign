package br.com.pedroyodasaito.softdesign.api.v1.dto.associado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociadoDTO implements Serializable {
    private Integer id;
    private String nome;
    private String cpf;
}
