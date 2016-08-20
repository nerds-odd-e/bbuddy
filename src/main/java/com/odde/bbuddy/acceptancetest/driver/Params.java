package com.odde.bbuddy.acceptancetest.driver;

public class Params {
    private String name;
    private String value;

    public void add(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getQuery() {
        if (name == null)
            return "";

        return String.format("?%s=%s", name, value);
    }
}
