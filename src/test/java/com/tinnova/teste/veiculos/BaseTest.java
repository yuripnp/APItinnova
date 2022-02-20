package com.tinnova.teste.veiculos;

import com.tinnova.teste.veiculos.enums.MarcaType;
import com.tinnova.teste.veiculos.model.Veiculo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.tinnova.teste.veiculos.enums.CorType.*;
import static com.tinnova.teste.veiculos.enums.MarcaType.*;

public class BaseTest {

    private static List<Veiculo> veiculos;

    protected BaseTest() {
        veiculos = new ArrayList<>();
        Veiculo veiculo1 = new Veiculo(1L, "Express 3500", HONDA, 2002, "descricao1", AMARELO, false, LocalDateTime.now(), LocalDateTime.now());
        Veiculo veiculo2 = new Veiculo(2L, "Ram 3500", VOLKSWAGEN, 2002, "descricao1", VERDE, true, LocalDateTime.now(), LocalDateTime.now());
        Veiculo veiculo3 = new Veiculo(3L, "R8", CHEVROLET, 2008, "descricao1", PRETO, true, LocalDateTime.now(), LocalDateTime.now());
        Veiculo veiculo4 = new Veiculo(4L, "R32", CHEVROLET, 2008, "descricao1", AZUL, false, LocalDateTime.now(), LocalDateTime.now());
        Veiculo veiculo5 = new Veiculo(5L, "Transit Connect", FORD, 2006, "descricao1", VERDE, true, LocalDateTime.now(), LocalDateTime.now());
        Veiculo veiculo6 = new Veiculo(6L, "RSX", FORD, 2006, "descricao1", AZUL, false, LocalDateTime.now(), LocalDateTime.now());
        veiculos.add(veiculo1);
        veiculos.add(veiculo2);
        veiculos.add(veiculo3);
        veiculos.add(veiculo4);
        veiculos.add(veiculo5);
        veiculos.add(veiculo6);
    }

    public List<Veiculo> getAllData() {
        return veiculos;
    }

    public List<Veiculo> getAllVeiculosByMarcaTypeFord() {
        return getAllData()
                .stream()
                .filter(v -> v.getMarca().equals(FORD))
                .collect(Collectors.toList());
    }

    public List<Veiculo> getAllVeiculosNaoVendidos() {
        return getAllData()
                .stream()
                .filter(v -> !v.isVendido())
                .collect(Collectors.toList());
    }

    public List<Object[]> sampleVeiculosGroupByDecada() {
        List<Object[]>  list = new ArrayList<>();
        Object[] objects;

        objects = new Object[]{ 2002, 2L };
        list.add(objects);

        objects = new Object[]{ 2008, 2L };
        list.add(objects);

        objects = new Object[]{ 2006, 2L };
        list.add(objects);

        return list;
    }

    public List<Object[]> sampleVeiculosGroupByMarca() {
        List<Object[]>  list = new ArrayList<>();
        Object[] objects;

        objects = new Object[]{ "FORD", 2L };
        list.add(objects);

        objects = new Object[]{ "CHEVROLET", 2L };
        list.add(objects);

        objects = new Object[]{ "VOLKSWAGEN", 1L };
        list.add(objects);

        objects = new Object[]{ "HONDA", 1L };
        list.add(objects);

        return list;
    }

    public Map<Integer, Long> getAllGroupByDecada() {
        return getAllData()
                .stream()
                .collect(
                        Collectors.groupingBy(Veiculo::getAno, Collectors.counting())
                );
    }

    public Map<MarcaType, Long> getAllGroupByMarca() {
        return getAllData()
                .stream()
                .collect(
                        Collectors.groupingBy(Veiculo::getMarca, Collectors.counting())
                );
    }

    public Query sampleQueryVeiculosByAno() {
        return new Query() {

            @Override
            public List<Object[]> getResultList() {
                return sampleVeiculosGroupByDecada();
            }

            @Override
            public Object getSingleResult() {
                return null;
            }

            @Override
            public int executeUpdate() {
                return 0;
            }

            @Override
            public Query setMaxResults(int i) {
                return null;
            }

            @Override
            public int getMaxResults() {
                return 0;
            }

            @Override
            public Query setFirstResult(int i) {
                return null;
            }

            @Override
            public int getFirstResult() {
                return 0;
            }

            @Override
            public Query setHint(String s, Object o) {
                return null;
            }

            @Override
            public Map<String, Object> getHints() {
                return null;
            }

            @Override
            public <T> Query setParameter(Parameter<T> parameter, T t) {
                return null;
            }

            @Override
            public Query setParameter(Parameter<Calendar> parameter, Calendar calendar, TemporalType temporalType) {
                return null;
            }

            @Override
            public Query setParameter(Parameter<Date> parameter, Date date, TemporalType temporalType) {
                return null;
            }

            @Override
            public Query setParameter(String s, Object o) {
                return null;
            }

            @Override
            public Query setParameter(String s, Calendar calendar, TemporalType temporalType) {
                return null;
            }

            @Override
            public Query setParameter(String s, Date date, TemporalType temporalType) {
                return null;
            }

            @Override
            public Query setParameter(int i, Object o) {
                return null;
            }

            @Override
            public Query setParameter(int i, Calendar calendar, TemporalType temporalType) {
                return null;
            }

            @Override
            public Query setParameter(int i, Date date, TemporalType temporalType) {
                return null;
            }

            @Override
            public Set<Parameter<?>> getParameters() {
                return null;
            }

            @Override
            public Parameter<?> getParameter(String s) {
                return null;
            }

            @Override
            public <T> Parameter<T> getParameter(String s, Class<T> aClass) {
                return null;
            }

            @Override
            public Parameter<?> getParameter(int i) {
                return null;
            }

            @Override
            public <T> Parameter<T> getParameter(int i, Class<T> aClass) {
                return null;
            }

            @Override
            public boolean isBound(Parameter<?> parameter) {
                return false;
            }

            @Override
            public <T> T getParameterValue(Parameter<T> parameter) {
                return null;
            }

            @Override
            public Object getParameterValue(String s) {
                return null;
            }

            @Override
            public Object getParameterValue(int i) {
                return null;
            }

            @Override
            public Query setFlushMode(FlushModeType flushModeType) {
                return null;
            }

            @Override
            public FlushModeType getFlushMode() {
                return null;
            }

            @Override
            public Query setLockMode(LockModeType lockModeType) {
                return null;
            }

            @Override
            public LockModeType getLockMode() {
                return null;
            }

            @Override
            public <T> T unwrap(Class<T> aClass) {
                return null;
            }
        };
    }

    public Query sampleQueryVeiculosByMarca() {
        return new Query() {

            @Override
            public List<Object[]> getResultList() {
                return sampleVeiculosGroupByMarca();
            }

            @Override
            public Object getSingleResult() {
                return null;
            }

            @Override
            public int executeUpdate() {
                return 0;
            }

            @Override
            public Query setMaxResults(int i) {
                return null;
            }

            @Override
            public int getMaxResults() {
                return 0;
            }

            @Override
            public Query setFirstResult(int i) {
                return null;
            }

            @Override
            public int getFirstResult() {
                return 0;
            }

            @Override
            public Query setHint(String s, Object o) {
                return null;
            }

            @Override
            public Map<String, Object> getHints() {
                return null;
            }

            @Override
            public <T> Query setParameter(Parameter<T> parameter, T t) {
                return null;
            }

            @Override
            public Query setParameter(Parameter<Calendar> parameter, Calendar calendar, TemporalType temporalType) {
                return null;
            }

            @Override
            public Query setParameter(Parameter<Date> parameter, Date date, TemporalType temporalType) {
                return null;
            }

            @Override
            public Query setParameter(String s, Object o) {
                return null;
            }

            @Override
            public Query setParameter(String s, Calendar calendar, TemporalType temporalType) {
                return null;
            }

            @Override
            public Query setParameter(String s, Date date, TemporalType temporalType) {
                return null;
            }

            @Override
            public Query setParameter(int i, Object o) {
                return null;
            }

            @Override
            public Query setParameter(int i, Calendar calendar, TemporalType temporalType) {
                return null;
            }

            @Override
            public Query setParameter(int i, Date date, TemporalType temporalType) {
                return null;
            }

            @Override
            public Set<Parameter<?>> getParameters() {
                return null;
            }

            @Override
            public Parameter<?> getParameter(String s) {
                return null;
            }

            @Override
            public <T> Parameter<T> getParameter(String s, Class<T> aClass) {
                return null;
            }

            @Override
            public Parameter<?> getParameter(int i) {
                return null;
            }

            @Override
            public <T> Parameter<T> getParameter(int i, Class<T> aClass) {
                return null;
            }

            @Override
            public boolean isBound(Parameter<?> parameter) {
                return false;
            }

            @Override
            public <T> T getParameterValue(Parameter<T> parameter) {
                return null;
            }

            @Override
            public Object getParameterValue(String s) {
                return null;
            }

            @Override
            public Object getParameterValue(int i) {
                return null;
            }

            @Override
            public Query setFlushMode(FlushModeType flushModeType) {
                return null;
            }

            @Override
            public FlushModeType getFlushMode() {
                return null;
            }

            @Override
            public Query setLockMode(LockModeType lockModeType) {
                return null;
            }

            @Override
            public LockModeType getLockMode() {
                return null;
            }

            @Override
            public <T> T unwrap(Class<T> aClass) {
                return null;
            }
        };
    }

    public Query sampleQueryVeiculosInseridosUltimaSemana() {
        return new Query() {
            @Override
            public List<Veiculo> getResultList() {
                return getAllData();
            }

            @Override
            public Object getSingleResult() {
                return null;
            }

            @Override
            public int executeUpdate() {
                return 0;
            }

            @Override
            public Query setMaxResults(int i) {
                return null;
            }

            @Override
            public int getMaxResults() {
                return 0;
            }

            @Override
            public Query setFirstResult(int i) {
                return null;
            }

            @Override
            public int getFirstResult() {
                return 0;
            }

            @Override
            public Query setHint(String s, Object o) {
                return null;
            }

            @Override
            public Map<String, Object> getHints() {
                return null;
            }

            @Override
            public <T> Query setParameter(Parameter<T> parameter, T t) {
                return null;
            }

            @Override
            public Query setParameter(Parameter<Calendar> parameter, Calendar calendar, TemporalType temporalType) {
                return null;
            }

            @Override
            public Query setParameter(Parameter<Date> parameter, Date date, TemporalType temporalType) {
                return null;
            }

            @Override
            public Query setParameter(String s, Object o) {
                return this;
            }

            @Override
            public Query setParameter(String s, Calendar calendar, TemporalType temporalType) {
                return null;
            }

            @Override
            public Query setParameter(String s, Date date, TemporalType temporalType) {
                return null;
            }

            @Override
            public Query setParameter(int i, Object o) {
                return null;
            }

            @Override
            public Query setParameter(int i, Calendar calendar, TemporalType temporalType) {
                return null;
            }

            @Override
            public Query setParameter(int i, Date date, TemporalType temporalType) {
                return null;
            }

            @Override
            public Set<Parameter<?>> getParameters() {
                return null;
            }

            @Override
            public Parameter<?> getParameter(String s) {
                return null;
            }

            @Override
            public <T> Parameter<T> getParameter(String s, Class<T> aClass) {
                return null;
            }

            @Override
            public Parameter<?> getParameter(int i) {
                return null;
            }

            @Override
            public <T> Parameter<T> getParameter(int i, Class<T> aClass) {
                return null;
            }

            @Override
            public boolean isBound(Parameter<?> parameter) {
                return false;
            }

            @Override
            public <T> T getParameterValue(Parameter<T> parameter) {
                return null;
            }

            @Override
            public Object getParameterValue(String s) {
                return null;
            }

            @Override
            public Object getParameterValue(int i) {
                return null;
            }

            @Override
            public Query setFlushMode(FlushModeType flushModeType) {
                return null;
            }

            @Override
            public FlushModeType getFlushMode() {
                return null;
            }

            @Override
            public Query setLockMode(LockModeType lockModeType) {
                return null;
            }

            @Override
            public LockModeType getLockMode() {
                return null;
            }

            @Override
            public <T> T unwrap(Class<T> aClass) {
                return null;
            }
        };
    }

}
