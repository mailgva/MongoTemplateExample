package com.gorbatenko.mongotemplate.model;

public enum Type {
    PROFIT("Приход"), SPENDING("Расход");

    private String value;


    Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
