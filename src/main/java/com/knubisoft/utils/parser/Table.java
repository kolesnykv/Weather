package com.knubisoft.utils.parser;

import lombok.RequiredArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

@RequiredArgsConstructor
public class Table {
    private final Map<Integer, Map<String, String>> table;

    int size() {
        return table.size();
    }

    Map<String, String> getTableRowByIndex(int row) {
        Map<String, String> tableRow = table.get(row);
        return tableRow == null ? null : new LinkedHashMap<>(tableRow);
    }

}
