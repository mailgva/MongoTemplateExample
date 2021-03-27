package com.gorbatenko.mongotemplate.repository;

import com.gorbatenko.mongotemplate.model.Budget;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends MongoRepository<Budget, String > {

    @Query("{$and : [{price : {$gte : 80}}, {price : {$lte : 100}}]}")
    List<Budget> getBudgetWherePriceLess100AndGreate80();

    @Query("{price : {$eq : 100}}")
    List<Budget> getBudgetWherePriceEqual100();

    @Query("{price : {$gte : ?0, $lte : ?1}}")
    List<Budget> getBudgetWherePriceBetween(double val1, double val2);
}
