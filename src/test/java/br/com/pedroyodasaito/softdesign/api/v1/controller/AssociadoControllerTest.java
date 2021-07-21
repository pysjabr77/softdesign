package br.com.pedroyodasaito.softdesign.api.v1.controller;

import br.com.pedroyodasaito.softdesign.entity.Associado;
import br.com.pedroyodasaito.softdesign.repository.AssociadoRepository;
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
@WebMvcTest(controllers = AssociadoController.class)
class AssociadoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private AssociadoRepository repository;

    @Test
    void save() throws Exception {
        Associado associado = new Associado();
        associado.setNome("Associado 1");
        associado.setCpf("373.761.410-57");

        when(repository.save(any(Associado.class))).thenReturn(associado);

        this.mockMvc.perform(post("/v1/associado")
                .content(mapper.writeValueAsString(associado))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(associado.getNome()));
    }

    @Test
    void find() throws Exception {
        Associado associado = new Associado();
        associado.setNome("Associado 1");
        associado.setCpf("373.761.410-57");

        List<Associado> list = new ArrayList<>();
        list.add(associado);

        when(repository.findAll()).thenReturn(list);

        this.mockMvc.perform(get("/v1/associado"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Associado 1")));
    }
}
