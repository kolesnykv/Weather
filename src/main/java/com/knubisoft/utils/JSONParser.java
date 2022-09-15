package com.knubisoft.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;

public class JSONParser {
    public <T> List<T> readAll(String inputSource, Class<T> cls) {
        Table table = parseToTable(inputSource);
        return convertTableToList(table, cls);
    }
    private <T> List<T> convertTableToList(Table table, Class<T> cls) {
        List<T> resultList = new ArrayList<>();
        for (int i = 0; i < table.size(); i++) {
            Map<String, String> row = table.getTableRowByIndex(i);
            T rowToType = reflectTableRowToClass(row, cls);
            resultList.add(rowToType);
        }
        return resultList;
    }
    @SneakyThrows
    private <T> T reflectTableRowToClass(Map<String, String> row, Class<T> cls) {
        T toType = cls.getDeclaredConstructor().newInstance();
        for (Field field : cls.getDeclaredFields()) {
            field.setAccessible(true);
            String rowValue = row.get(field.getName());
            if (rowValue != null) {
                field.set(toType, transformValueToFieldType(field, rowValue.trim()));
            }
        }
        return toType;
    }

    private Object transformValueToFieldType(Field field, String value) {
        Map<Class<?>, Function<String, Object>> typeToFunction = new LinkedHashMap<>();
        typeToFunction.put(String.class, s -> s);
        typeToFunction.put(int.class, Integer::parseInt);
        typeToFunction.put(Float.class, Float::parseFloat);
        typeToFunction.put(LocalDate.class, s -> LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        typeToFunction.put(Character.class, s -> s.charAt(0));
        typeToFunction.put(Long.class, Long::parseLong);
        typeToFunction.put(double.class, Double::parseDouble);
        typeToFunction.put(LocalDateTime.class, s -> Instant.ofEpochMilli(Long.parseLong(s))
                .atZone(ZoneId.systemDefault()) // default zone
                .toLocalDateTime());
        typeToFunction.put(LocalTime.class, s -> LocalTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(s)), ZoneId.systemDefault()));
        return typeToFunction.getOrDefault(field.getType(), type -> {
            throw new UnsupportedOperationException("Type  is not supported" + type);
        }).apply(value);
    }

    @SneakyThrows
    public Table parseToTable(String content) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode treeNode = objectMapper.readTree(content);
        Map<Integer, Map<String, String>> mapFromTreeNode = buildTable(treeNode);
        return new Table(mapFromTreeNode);
    }

    private Map<Integer, Map<String, String>> buildTable(JsonNode treeNode) {
        Map<Integer, Map<String, String>> map = new LinkedHashMap<>();
        int index = 0;
        for (JsonNode node : treeNode) {
            Map<String, String> rowMap = buildRow(node);
            map.put(index, rowMap);
            index++;
        }
        return map;
    }

    private Map<String, String> buildRow(JsonNode node) {
        Map<String, String> rowMap = new LinkedHashMap<>();
        Iterator<Map.Entry<String, JsonNode>> iterator = node.fields();
        while (iterator.hasNext()) {
            Map.Entry<String, JsonNode> nextNode = iterator.next();
            rowMap.put(nextNode.getKey(), nextNode.getValue().asText());
        }
        return rowMap;
    }
}
