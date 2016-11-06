package com.odde.bbuddy.common.view;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class Params {
    private static final String QUERY_HEAD = "?";
    private static final String PARAM_DELIMITER = "&";
    private static final String PARAM_CONNECTOR = "=";
    private final Map<String, String> allParams = new HashMap<>();

    public void add(String name, String value) {
        allParams.put(name, value);
    }

    public String getQuery() {
        if (allParams.isEmpty())
            return "";

        return QUERY_HEAD + allParams.entrySet().stream()
                .map(this::paramInQuery)
                .collect(joining(PARAM_DELIMITER));
    }

    private String paramInQuery(Map.Entry<String, String> param) {
        return param.getKey() + PARAM_CONNECTOR + param.getValue();
    }
}
