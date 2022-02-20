package com.tinnova.teste.veiculos.controller;

import com.tinnova.teste.veiculos.dto.input.VeiculoRequestDto;
import com.tinnova.teste.veiculos.dto.output.VeiculoResponseDto;
import com.tinnova.teste.veiculos.enums.CorType;
import com.tinnova.teste.veiculos.enums.MarcaType;
import com.tinnova.teste.veiculos.mapper.VeiculoMapper;
import com.tinnova.teste.veiculos.model.Veiculo;
import com.tinnova.teste.veiculos.service.VeiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService service;

    private final VeiculoMapper mapper;

    public VeiculoController(VeiculoService service, VeiculoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<VeiculoResponseDto> getAll(
        @RequestParam(value = "marca", defaultValue = "") MarcaType marca,
        @RequestParam(value = "ano", defaultValue = "0") Integer ano,
        @RequestParam(value = "cor", defaultValue = "") CorType cor
    ) {
        List<Veiculo> veiculoList;

        if (marca != null || ano > 0 || cor != null) {
            veiculoList = service.findAll(marca, ano, cor);
        } else {
            veiculoList = service.getAll();
        }

        return veiculoList
            .stream()
            .map(mapper::toResponseDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public VeiculoResponseDto getOne(@PathVariable("id") Long id) throws Exception {
        return mapper.toResponseDto(service.getOne(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody VeiculoRequestDto veiculoRequestDto) {
        service.save(mapper.toEntity(veiculoRequestDto));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateAllFields(
        @PathVariable("id") Long id,
        @RequestBody VeiculoRequestDto veiculoRequestDto
    ) throws Exception {
        service.updateAllFields(id, mapper.toEntity(veiculoRequestDto));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePartialFields(
        @PathVariable("id") Long id,
        @RequestBody Map<String, Object> veiculoRequestDto
    ) throws Exception {
        service.updatePartialFields(id, veiculoRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) throws Exception {
        service.delete(id);
    }

    @GetMapping("/qnt-veiculos-nao-vendidos")
    public Map<String, Object> getAllVeiculosNaoVendidos() {
        return service.qntVeiculosNaoVendidos();
    }

    @GetMapping("/veiculos-agrupados-por-decadas")
    public Map<Integer, Long> getVeiculosAgrupadosPorDecada() {
        return service.groupVeiculosByAno();
    }

    @GetMapping("/veiculos-agrupados-por-marca")
    public Map<MarcaType, Long> getVeiculosAgrupadosPorMarca() {
        return service.groupVeiculosByMarca();
    }

    @GetMapping("/veiculos-last-week")
    public List<VeiculoResponseDto> getVeiculosByLastWeek() {
        return service.getAllByLastWeek()
            .stream()
            .map(mapper::toResponseDto)
            .collect(Collectors.toList());
    }
}
