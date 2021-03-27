package com.gorbatenko.mongotemplate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gorbatenko.mongotemplate.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "currency")
public class Currency extends BaseEntity implements Comparable {
  @Indexed
  private String name;

  @JsonIgnore
  private String userGroup;

  public Currency(String name, String userGroup) {
    this.name = name;
    this.userGroup = userGroup;
  }

  @Override
  public int compareTo(Object o) {
    Currency other = (Currency) o;
    return this.getName().compareTo(other.getName());
  }

  @Override
  public String toString() {
    return "Currency{" +
            "id='" + getId() + '\'' +
            ", name='" + name + '\'' +
            ", userGroup='" + userGroup + '\'' +
            '}';
  }
}
