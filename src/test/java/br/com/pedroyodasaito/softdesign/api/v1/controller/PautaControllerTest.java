package br.com.pedroyodasaito.softdesign.api.v1.controller;

import br.com.pedroyodasaito.softdesign.entity.Pauta;
import br.com.pedroyodasaito.softdesign.repository.PautaRepository;
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
class PautaControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PautaRepository repository;

    @Test
    void save() throws Exception {
        Pauta pauta = new Pauta();
        pauta.setId(2);
        pauta.setNome("Pauta 1");

        ResponseEntity<Integer> response = restTemplate
                .postForEntity(new URL("http://localhost:" + port + "/api/v1/pauta").toString(),
                        pauta, Integer.class);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(2, response.getBody());
    }

    @Test
    void find() throws Exception {
        Pauta pauta = new Pauta();
        pauta.setNome("Pauta 1");

        pauta = repository.save(pauta);

        ResponseEntity<String> result = restTemplate
                .getForEntity(new URL("http://localhost:" + port + "/api/v1/pauta/" +
                        pauta.getId()).toString(), String.class);
        assertEquals(200, result.getStatusCodeValue());
        assertTrue(Objects.requireNonNull(result.getBody()).contains("Pauta 1"));

        repository.delete(pauta);
    }
}
