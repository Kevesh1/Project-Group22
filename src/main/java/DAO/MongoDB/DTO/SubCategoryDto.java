package DAO.MongoDB.DTO;

import org.bson.types.ObjectId;

public final class SubCategoryDto {
    private ObjectId id;

    private String name;

    private double budget;

    private double budgetSpent;

    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SubCategoryDto setName(String name) {
        this.name = name;
        return this;
    }

    public double getBudget() {
        return budget;
    }

    public SubCategoryDto setBudget(double budget) {
        this.budget = budget;
        return this;
    }

    public double getBudgetSpent() {
        return budgetSpent;
    }

    public SubCategoryDto setBudgetSpent(double budgetSpent) {
        this.budgetSpent = budgetSpent;
        return this;
    }
}
