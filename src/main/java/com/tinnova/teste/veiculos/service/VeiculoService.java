package com.tinnova.teste.veiculos.service;

import com.tinnova.teste.veiculos.enums.CorType;
import com.tinnova.teste.veiculos.enums.MarcaType;
import com.tinnova.teste.veiculos.model.Veiculo;
import com.tinnova.teste.veiculos.repository.VeiculoRepository;
import com.tinnova.teste.veiculos.util.Utils;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Service
public class VeiculoService {

    private final VeiculoRepository repository;

    private final EntityManager entityManager;

    public VeiculoService(VeiculoRepository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }


    public List<Veiculo> getAll() {
        return repository.findAll();
    }

    public Veiculo getOne(Long id) throws Exception {
        Veiculo veiculo = repository.getOne(id);
        if (veiculo == null) {
            throw new Exception("Produto não encontrado");
        }
        return veiculo;
    }

    public void save(Veiculo entity) {
        repository.save(entity);
    }

    public void updateAllFields(Long id, Veiculo entity) throws Exception {
        Veiculo veiculoStored = getOne(id);
        entity.setId(veiculoStored.getId());
        entity.setCreated(veiculoStored.getCreated());
        save(entity);
    }

    public void updatePartialFields(Long id, Map<String, Object> entity) throws Exception {
        Veiculo veiculo = (Veiculo) Hibernate.unproxy(repository.getOne(id));
        veiculo = Utils.difference(entity, veiculo);
        repository.save(veiculo);
    }

    public void delete(Long id) throws Exception {
        repository.delete(getOne(id));
    }

    public List<Veiculo> findAll(MarcaType marca, Integer ano, CorType cor) {
        return repository.findAllByMarcaOrAnoOrCor(marca, ano, cor);
    }

    public Map<String, Object> qntVeiculosNaoVendidos() {
        Map<String, Object> result = new HashMap<>();
        result.put("quantidade", repository.findAllByVendidoIs(false).size());
        return result;
    }

    public Map<Integer, Long> groupVeiculosByAno() {
        Map<Integer, Long> result = new HashMap<>();
        List<Object[]> anosAgrupados = entityManager.createNativeQuery("SELECT v.ano, count(v.id) FROM veiculo v group by v.ano;")
                .getResultList();
        anosAgrupados.forEach(o -> result.put(Integer.valueOf(o[0].toString()), Long.valueOf(o[1].toString())));
        return result;
    }

    public Map<MarcaType, Long> groupVeiculosByMarca() {
        Map<MarcaType, Long> result = new HashMap<>();
        List<Object[]> anosAgrupados = entityManager.createQuery("SELECT v.marca as marca, count(v.id) as qnt FROM Veiculo v group by v.marca")
                .getResultList();
        anosAgrupados.forEach(o -> result.put(MarcaType.valueOf(o[0].toString()), Long.valueOf(o[1].toString())));
        return result;
    }

    public List<Veiculo> getAllByLastWeek() {
        LocalDate dataInicial = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        LocalDate dataFinal = dataInicial.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));

        List<Veiculo> veiculoList = (List<Veiculo>) entityManager.createNativeQuery(
                        "SELECT v.* FROM veiculo v WHERE v.created between :dataInicial and :dataFinal", Veiculo.class
                )
                .setParameter("dataInicial", dataInicial + " 00:00:00")
                .setParameter("dataFinal", dataFinal + " 23:59:59")
                .getResultList();

        return veiculoList;
    }

}
