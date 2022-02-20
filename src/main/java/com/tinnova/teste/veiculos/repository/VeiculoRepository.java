package com.tinnova.teste.veiculos.repository;

import com.tinnova.teste.veiculos.enums.CorType;
import com.tinnova.teste.veiculos.enums.MarcaType;
import com.tinnova.teste.veiculos.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    List<Veiculo> findAllByMarcaOrAnoOrCor(MarcaType marcaType, Integer ano, CorType corType);

    List<Veiculo> findAllByVendidoIs(boolean vendido);

}
