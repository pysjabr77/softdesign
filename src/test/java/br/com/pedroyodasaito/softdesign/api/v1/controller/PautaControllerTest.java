package br.com.pedroyodasaito.softdesign.api.v1.controller;

import br.com.pedroyodasaito.softdesign.entity.Pauta;
import br.com.pedroyodasaito.softdesign.repository.PautaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PautaController.class)
class PautaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private PautaRepository repository;

    @Test
    void save() throws Exception {
        Pauta pauta = new Pauta();
        pauta.setNome("Pauta 1");

        when(repository.save(any(Pauta.class))).thenReturn(pauta);

        this.mockMvc.perform(post("/v1/pauta")
                .content(mapper.writeValueAsString(pauta))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(pauta.getNome()));
    }

    @Test
    void find() throws Exception {
        Pauta pauta = new Pauta();
        pauta.setNome("Pauta 1");

        List<Pauta> list = new ArrayList<>();
        list.add(pauta);

        when(repository.findAll()).thenReturn(list);

        this.mockMvc.perform(get("/v1/pauta"))
                        .andExpect(status().isOk())
                        .andExpect(content().string(containsString("Pauta 1")));
    }
}
