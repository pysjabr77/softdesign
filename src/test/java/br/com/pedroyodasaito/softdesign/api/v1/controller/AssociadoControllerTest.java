package br.com.pedroyodasaito.softdesign.api.v1.controller;

import br.com.pedroyodasaito.softdesign.entity.Associado;
import br.com.pedroyodasaito.softdesign.repository.AssociadoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URL;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AssociadoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AssociadoRepository repository;

    @Test
    void find() throws Exception {
        Associado associado = new Associado();
        associado.setNome("Associado 1");
        associado.setCpf("373.761.410-57");

        associado = repository.save(associado);

        ResponseEntity<String> result = restTemplate
                .getForEntity(new URL("http://localhost:" + port + "/api/v1/associado/" +
                        associado.getId()).toString(), String.class);
        assertEquals(200, result.getStatusCodeValue());
        assertTrue(Objects.requireNonNull(result.getBody()).contains("Associado 1"));

        repository.delete(associado);
    }

    @Test
    void save() throws Exception {
        Associado associado = new Associado();
        associado.setId(2);
        associado.setNome("Associado 1");
        associado.setCpf("373.761.410-57");

        ResponseEntity<Integer> response = restTemplate
                .postForEntity(new URL("http://localhost:" + port + "/api/v1/associado").toString(),
                        associado, Integer.class);
        assertEquals(2, response.getBody());
    }
}
