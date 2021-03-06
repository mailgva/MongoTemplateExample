package com.gorbatenko.mongotemplate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gorbatenko.mongotemplate.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@Document(collection = "budget")
public class Budget extends BaseEntity {
    private User user;

    private String userGroup;

    private Kind kind;

    private Currency currency;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;


    private LocalDateTime createDateTime;

    private String description;

    private Double price;

    public Budget(User user, Kind kind, LocalDateTime date, String description, Double price, Currency currency) {
        this.user = user;
        this.kind = kind;
        this.date = date;
        this.createDateTime = LocalDateTime.now();
        this.description = description;
        this.price = price;
        this.currency = currency;
    }

    public Budget(User user, String userGroup, Kind kind, LocalDateTime date, String description, Double price, Currency currency) {
        this.user = user;
        this.userGroup = userGroup;
        this.kind = kind;
        this.date = date;
        this.createDateTime = LocalDateTime.now();
        this.description = description;
        this.price = price;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "id=" + getId() +
                ", user=" + user +
                ", userGroup=" + userGroup +
                ", kind=" + kind +
                ", date=" + date +
                ", createDateTime=" + createDateTime +
                ", description='" + description + '\'' +
                ", price=" + price + '\'' +
                ", currency=" + currency.getName() +
                '}';
    }

    @JsonIgnore
    public String getStrDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return date.format(formatter);
    }

    @JsonIgnore
    public String getStrDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }

    @JsonIgnore
    public String getStrYearMonth() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        return date.format(formatter);
    }
}
