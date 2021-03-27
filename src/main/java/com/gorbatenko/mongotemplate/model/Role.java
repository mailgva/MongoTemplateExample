package com.gorbatenko.mongotemplate.model;


public enum Role  {
    ROLE_USER,
    ROLE_ADMIN;

    public String getAuthority() {
        return name();
    }
}