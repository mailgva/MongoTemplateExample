package com.gorbatenko.mongotemplate.model;

public enum Dictionary {
    KINDS("Виды Приходов/Расходов"), CURRENCIES("Валюты");

    private String value;

    Dictionary(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
