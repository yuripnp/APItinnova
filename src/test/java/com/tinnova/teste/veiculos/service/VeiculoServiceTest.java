package com.tinnova.teste.veiculos.service;

import com.tinnova.teste.veiculos.BaseTest;
import com.tinnova.teste.veiculos.enums.MarcaType;
import com.tinnova.teste.veiculos.model.Veiculo;
import com.tinnova.teste.veiculos.repository.VeiculoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VeiculoServiceTest extends BaseTest {

    private final VeiculoRepository repository = mock(VeiculoRepository.class);
    private final EntityManager entityManager = mock(EntityManager.class);

    private final VeiculoService veiculoService = new VeiculoService(repository, entityManager);

    public VeiculoServiceTest() {
        super();
    }

    @Test
    void getAll() {
        // Given - Dado
        when(repository.findAll()).thenReturn(getAllData());

        // When - Quando
        List<Veiculo> veiculoList = veiculoService.getAll();

        // Then - Então
        assertEquals(6, veiculoList.size());
    }

    @Test
    void getOne() throws Exception {
        // Given - Dado
        Veiculo veiculoBase = getAllData().get(1);

        when(repository.getOne(2L))
                .thenReturn(veiculoBase);

        // When - Quando
        Veiculo veiculo = veiculoService.getOne(2L);

        // Then - Então
        assertEquals(veiculoBase, veiculo);
    }

    @Test
    void getOneNotFound() {
        // Given - Dado
        when(repository.getOne(100L)).thenReturn(null);

        // When - Quando
        Exception e = assertThrows(Exception.class, () -> veiculoService.getOne(100L));

        // Then - Então
        assertEquals("Produto não encontrado", e.getMessage());
    }

    @Test
    void save() {
        // Given - Dado
        ArgumentCaptor<Veiculo> argumentCaptor = ArgumentCaptor.forClass(Veiculo.class);

        Veiculo veiculoInsertSample = getAllData().get(0);

        when(repository.save(Mockito.any(Veiculo.class)))
                .thenReturn(veiculoInsertSample);

        // When - Quando
        veiculoService.save(veiculoInsertSample);

        // Then - Então
        verify(repository, times(1))
                .save(argumentCaptor.capture());

        assertEquals(veiculoInsertSample, argumentCaptor.getValue());
    }

    @Test
    void updateAllFields() throws Exception {
        // Given - Dado
        ArgumentCaptor<Veiculo> captor = ArgumentCaptor.forClass(Veiculo.class);

        Veiculo veiculoSample = getAllData().get(0);
        veiculoSample.setAno(2021);
        veiculoSample.setMarca(MarcaType.FORD);

        when(repository.getOne(1L))
                .thenReturn(veiculoSample);

        when(repository.save(Mockito.any(Veiculo.class)))
                .thenReturn(veiculoSample);

        // When - Quando
        veiculoService.updateAllFields(1L, veiculoSample);

        // Then - Então
        verify(repository, times(1))
                .save(captor.capture());

        assertEquals(veiculoSample, captor.getValue());
    }

    @Test
    void updatePartialFields() throws Exception {
        // Given - Dado
        ArgumentCaptor<Veiculo> captor = ArgumentCaptor.forClass(Veiculo.class);

        Veiculo veiculoData = getAllData().get(0);

        Map<String, Object> veiculoSample = new HashMap<>();
        veiculoSample.put("ano", 2021);
        veiculoSample.put("marca", "FORD");

        when(repository.getOne(1L))
                .thenReturn(veiculoData);

        when(repository.save(Mockito.any(Veiculo.class)))
                .thenReturn(veiculoData);

        // When - Quando
        veiculoService.updatePartialFields(1L, veiculoSample);

        // Then - Então
        verify(repository, times(1))
                .save(captor.capture());

        assertEquals(veiculoData, captor.getValue());
    }

    @Test
    void delete() throws Exception {
        // Given - Dado
        Veiculo veiculoDeletedSample = getAllData().get(5);

        doNothing().when(repository)
                .delete(veiculoDeletedSample);

        when(repository.getOne(6L))
                .thenReturn(veiculoDeletedSample);

        // When - Quando
        veiculoService.delete(6L);

        // Then - Então
        verify(repository, times(1))
                .delete(veiculoDeletedSample);
    }

    @Test
    void findAll() {
        // Given - Dado
        // Pesquisar pro veiculos da Marca FORD
        when(repository.findAllByMarcaOrAnoOrCor(MarcaType.FORD, null, null))
                .thenReturn(getAllVeiculosByMarcaTypeFord());

        // When - Quando
        List<Veiculo> veiculoFiltradosList = veiculoService.findAll(MarcaType.FORD, null, null);

        // Then - Então
        assertEquals(2, veiculoFiltradosList.size());
    }

    @Test
    void qntVeiculosNaoVendidos() {
        // Given - Dado
        when(repository.findAllByVendidoIs(false))
                .thenReturn(getAllVeiculosNaoVendidos());

        // When - Quando
        Map<String, Object> filterMap = veiculoService.qntVeiculosNaoVendidos();

        // Then - Então
        assertEquals(3, filterMap.get("quantidade"));
    }

    @Test
    void groupVeiculosByAno() {
        // Given - Dado
        when(
                entityManager.createNativeQuery("SELECT v.ano, count(v.id) FROM veiculo v group by v.ano;")
        ).thenReturn(sampleQueryVeiculosByAno());

        Map<Integer, Long> veiculosFilteredGroup = getAllGroupByDecada();

        // When - Quando
        Map<Integer, Long> objectMap = veiculoService.groupVeiculosByAno();

        // Then - Então
        assertEquals(veiculosFilteredGroup, objectMap);
    }

    @Test
    void groupVeiculosByMarca() {
        // Given - Dado
        when(
                entityManager.createQuery("SELECT v.marca as marca, count(v.id) as qnt FROM Veiculo v group by v.marca")
        ).thenReturn(sampleQueryVeiculosByMarca());

        // When - Quando
        Map<MarcaType, Long> veiculosFilteredGroup = getAllGroupByMarca();

        Map<MarcaType, Long> objectMap = veiculoService.groupVeiculosByMarca();

        // Then - Então
        assertEquals(veiculosFilteredGroup, objectMap);
    }

    @Test
    void getAllByLastWeek() {
        // Given - Dado
        when(
                entityManager.createNativeQuery("SELECT v.* FROM veiculo v WHERE v.created between :dataInicial and :dataFinal", Veiculo.class)
        ).thenReturn(sampleQueryVeiculosInseridosUltimaSemana());

        // When - Quando
        List<Veiculo> veiculosList = veiculoService.getAllByLastWeek();

        // Then - Então
        assertEquals(getAllData().size(), veiculosList.size());
    }
}