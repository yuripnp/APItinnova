package com.tinnova.teste.veiculos.mapper;

import com.tinnova.teste.veiculos.dto.input.VeiculoRequestDto;
import com.tinnova.teste.veiculos.dto.output.VeiculoResponseDto;
import com.tinnova.teste.veiculos.model.Veiculo;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-21T14:34:57-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.13 (Ubuntu)"
)
@Component
public class VeiculoMapperImpl extends VeiculoMapper {

    @Override
    public Veiculo toEntity(VeiculoRequestDto veiculoRequestDto) {
        if ( veiculoRequestDto == null ) {
            return null;
        }

        Veiculo veiculo = new Veiculo();

        veiculo.setVeiculo( veiculoRequestDto.getVeiculo() );
        veiculo.setMarca( veiculoRequestDto.getMarca() );
        veiculo.setAno( veiculoRequestDto.getAno() );
        veiculo.setDescricao( veiculoRequestDto.getDescricao() );
        veiculo.setVendido( veiculoRequestDto.isVendido() );
        veiculo.setCor( veiculoRequestDto.getCor() );

        return veiculo;
    }

    @Override
    public VeiculoResponseDto toResponseDto(Veiculo veiculo) {
        if ( veiculo == null ) {
            return null;
        }

        VeiculoResponseDto veiculoResponseDto = new VeiculoResponseDto();

        veiculoResponseDto.setId( veiculo.getId() );
        veiculoResponseDto.setVeiculo( veiculo.getVeiculo() );
        veiculoResponseDto.setMarca( veiculo.getMarca() );
        veiculoResponseDto.setAno( veiculo.getAno() );
        veiculoResponseDto.setDescricao( veiculo.getDescricao() );
        veiculoResponseDto.setVendido( veiculo.isVendido() );
        veiculoResponseDto.setCreated( veiculo.getCreated() );
        veiculoResponseDto.setUpdated( veiculo.getUpdated() );
        veiculoResponseDto.setCor( veiculo.getCor() );

        return veiculoResponseDto;
    }
}
