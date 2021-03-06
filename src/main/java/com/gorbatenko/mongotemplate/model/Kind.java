package com.gorbatenko.mongotemplate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gorbatenko.mongotemplate.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "kind")
public class Kind extends BaseEntity implements Comparable {
    @Indexed
    private String name;

    private Type type;

    @JsonIgnore
    private String userGroup;

    public Kind(Type type, String name, String userGroup) {
        this.type = type;
        this.name = name;
        this.userGroup = userGroup;
    }

    @Override
    public int compareTo(Object o) {
        Kind other = (Kind) o;
        return this.getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        return "Kind{" +
                "id=" + getId() +
                ", name='" + name +
                ", type=" + type +
                ", userGroup=" + userGroup +
                '}';
    }
}
