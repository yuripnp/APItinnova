package com.tinnova.teste.veiculos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinnova.teste.veiculos.dto.input.VeiculoRequestDto;
import com.tinnova.teste.veiculos.enums.CorType;
import com.tinnova.teste.veiculos.enums.MarcaType;
import com.tinnova.teste.veiculos.mapper.VeiculoMapper;
import com.tinnova.teste.veiculos.model.Veiculo;
import com.tinnova.teste.veiculos.repository.VeiculoRepository;
import com.tinnova.teste.veiculos.service.VeiculoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:veiculodb"
})
@AutoConfigureMockMvc
class VeiculoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private VeiculoMapper veiculoMapper;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAll() throws Exception {
        String result = this.mockMvc.perform(get("/veiculos"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Veiculo> veiculoList = objectMapper.readValue(result, List.class);

        assertEquals(100, veiculoList.size());
    }

    @Test
    void findAll() throws Exception {
        this.mockMvc.perform(get("/veiculos?marca=HONDA&cor=&ano="))
                .andExpect(status().isOk());
    }

    @Test
    void getOne() throws Exception {
        String result = this.mockMvc.perform(get("/veiculos/1"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Veiculo veiculo = objectMapper.readValue(result, Veiculo.class);

        Veiculo veiculoExpected = new Veiculo(1L, "Century", MarcaType.HONDA, 1993, "", CorType.PRETO, false, LocalDateTime.now(), LocalDateTime.now());

        assertEquals(veiculoExpected.getId(), veiculo.getId());
        assertEquals(veiculoExpected.getVeiculo(), veiculo.getVeiculo());
        assertEquals(veiculoExpected.getMarca(), veiculo.getMarca());
        assertEquals(veiculoExpected.getAno(), veiculo.getAno());
        assertEquals(veiculoExpected.getDescricao(), veiculo.getDescricao());
        assertEquals(veiculoExpected.getCor(), veiculo.getCor());
        assertEquals(veiculoExpected.isVendido(), veiculo.isVendido());
    }

    @Test
    void save() throws Exception {
        VeiculoRequestDto veiculoRequestDto = new VeiculoRequestDto("Century 2", MarcaType.HONDA, 1993, "", CorType.PRETO, false);
        this.mockMvc.perform(post("/veiculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(veiculoRequestDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void updateAllFields() throws Exception {
        VeiculoRequestDto veiculoRequestDto = new VeiculoRequestDto("Century 2 atualizado", MarcaType.HONDA, 1993, "", CorType.PRETO, false);
        this.mockMvc.perform(put("/veiculos/101")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(veiculoRequestDto)))
                .andExpect(status().isOk());
    }

    @Test
    void updatePartialFields() throws Exception {
        this.mockMvc.perform(patch("/veiculos/101")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"marca\": \"FIAT\", \"cor\": \"BRANCO\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/veiculos/101"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllVeiculosNaoVendidos() throws Exception {
        this.mockMvc.perform(get("/veiculos/qnt-veiculos-nao-vendidos"))
                .andExpect(status().isOk());
    }

    @Test
    void getVeiculosAgrupadosPorDecada() throws Exception {
        this.mockMvc.perform(get("/veiculos/veiculos-agrupados-por-decadas"))
                .andExpect(status().isOk());
    }

    @Test
    void getVeiculosAgrupadosPorMarca() throws Exception {
        this.mockMvc.perform(get("/veiculos/veiculos-agrupados-por-marca"))
                .andExpect(status().isOk());
    }

    @Test
    void getVeiculosByLastWeek() throws Exception {
        this.mockMvc.perform(get("/veiculos/veiculos-last-week"))
                .andExpect(status().isOk());
    }
}