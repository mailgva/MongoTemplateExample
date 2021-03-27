package com.gorbatenko.mongotemplate.controller;

import com.gorbatenko.mongotemplate.model.Budget;
import com.gorbatenko.mongotemplate.model.Type;
import com.gorbatenko.mongotemplate.repository.BudgetRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class Web {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping
    public List<Budget> index() {
        return budgetRepository.getBudgetWherePriceLess100AndGreate80();
    }

    @GetMapping("p100")
    public List<Budget> price100() {
        return budgetRepository.getBudgetWherePriceEqual100();
    }

    @GetMapping("between")
    public List<Budget> between() {
        return budgetRepository.getBudgetWherePriceBetween(40, 60);
    }

    @GetMapping("kindid")
    public List<Budget> kindid() {
        return getBudgetKindById("5d29b885cffca1000444acbe");
    }

    @GetMapping("sum")
    public List<TypeTotal> sum() {
        return getBudgetTypeSum(Type.SPENDING).getMappedResults();
    }

    private AggregationResults<TypeTotal> getBudgetTypeSum(Type type) {
        MatchOperation match = Aggregation.match(new Criteria("kind.type").is(type));
        GroupOperation group = Aggregation.group("kind.name").sum("price").as("total");
        SortOperation sort = Aggregation.sort(Sort.by(Sort.Direction.ASC, "_id"));
        ProjectionOperation projections = Aggregation.project().and("_id").as("name").andExclude("_id").andInclude("total");

        Aggregation aggregation = Aggregation.newAggregation(match, group, sort, projections);


        /* // Short variant
        Aggregation aggregation = newAggregation(
                match(new Criteria("kind.type").is(type)),
                group("kind.name").sum("price").as("total"),
                sort(Sort.Direction.ASC, "_id"),
                project().and("_id").as("name").andExclude("_id").andInclude("total")
        );
        */

        AggregationResults<TypeTotal> result = mongoTemplate.aggregate(
                aggregation, "budget", TypeTotal.class);

        return result;
    }

     private List<Budget> getBudgetKindById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("kind.id").is(id));
        return mongoTemplate.find(query, Budget.class);
    }

    @Data
    class TypeTotal {
        String name;
        Double total;
    }

}
