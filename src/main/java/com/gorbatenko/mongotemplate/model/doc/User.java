package com.gorbatenko.mongotemplate.model.doc;

import com.gorbatenko.mongotemplate.BaseEntity;
import lombok.Data;

@Data
public class User extends BaseEntity {
    public User(String id, String name) {
        super(id);
        this.name = name;
    }

    private String name;

}
