package com.tinnova.teste.veiculos.mapper;

import com.tinnova.teste.veiculos.dto.input.VeiculoRequestDto;
import com.tinnova.teste.veiculos.dto.output.VeiculoResponseDto;
import com.tinnova.teste.veiculos.model.Veiculo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class VeiculoMapper {

    public abstract Veiculo toEntity(VeiculoRequestDto veiculoRequestDto);

    public abstract VeiculoResponseDto toResponseDto(Veiculo veiculo);
}
