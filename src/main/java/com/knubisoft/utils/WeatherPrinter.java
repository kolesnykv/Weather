package com.knubisoft.utils;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableInt;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherPrinter {
    public void printWeather(List<SimpleWeather> weatherList) {
        List<List<String>> result = new ArrayList<>();
        result.add(createHeaders(SimpleWeather.class));
        result.addAll(createBody(weatherList));
        printTable(result, getPreCalculatedStringFormat(result));
    }

    private List<String> createHeaders(Class clz) {
        List<String> header = new ArrayList<>();
        for (Field f : clz.getDeclaredFields()) {
            header.add(f.getName());
        }
        return header;
    }

    private List<List<String>> createBody(List<SimpleWeather> args) {
        List<List<String>> body = new ArrayList<>();
        for (SimpleWeather inst : args) {
            body.add(buildRow(inst));
        }
        return body;
    }

    @SneakyThrows
    private List<String> buildRow(SimpleWeather inst) {
        List<String> row = new ArrayList<>();
        for (Field f : inst.getClass().getDeclaredFields()) {
            row.add(String.valueOf(getGetterMethod(f.getName(), inst).invoke(inst)));
        }
        return row;
    }

    @SneakyThrows
    private Method getGetterMethod(String name, Object instance) {
        return instance.getClass().getMethod("get" + StringUtils.capitalize(name));
    }

    public String getPreCalculatedStringFormat(List<List<String>> columns) {
        int columnsCount = columns.get(0).size();
        List<Integer> result = new ArrayList<>();
        for (MutableInt column = new MutableInt(0);
             column.getValue() < columnsCount; column.increment()) {
            result.add(columns.stream().
                    map(row -> row.get(column.getValue())).
                    map(String::length).
                    max(Integer::compare).get());
        }
        return "| " + result.stream().map(v -> "%-" + v + "s").collect(Collectors.joining(" | ")) + " |";
    }

    private static void printTable(List<List<String>> result, String format) {
        result.forEach(row -> System.out.printf((format) + "%n", row.toArray()));
    }
}
