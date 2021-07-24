package br.com.pedroyodasaito.softdesign.integracao;

import br.com.pedroyodasaito.softdesign.integracao.dto.StatusVotoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IntegracaoCpfValidatorService {

    public static StatusVotoDTO validarCpf(String cpf) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("https://user-info.herokuapp.com/users/"+cpf, StatusVotoDTO.class);
    }

}
