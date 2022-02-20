package com.tinnova.teste.veiculos.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utils {

    public static <T> T difference(Map<String, Object> source, T target) throws IllegalAccessException, ClassNotFoundException {

        List<String> sourceFieldsNames = new ArrayList<>(source.keySet());

        for (Field field : target.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            if (sourceFieldsNames.contains(field.getName())) {
                if (field.getType().isEnum()) {
                    Class _tempClass = Class.forName(field.getType().getName());
                    Object o = Enum.valueOf(_tempClass, source.get(field.getName()).toString());
                    field.set(target, o);
                } else {
                    field.set(target, source.get(field.getName()));
                }
            }

        }
        return target;
    }

}
