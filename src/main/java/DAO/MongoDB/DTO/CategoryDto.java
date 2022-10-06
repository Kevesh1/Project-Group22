package DAO.MongoDB.DTO;

import budgetapp.model.categories.Category;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public final class CategoryDto {

    private ObjectId id;

    private Category category;

    private double budget;

    private double budgetSpent;

    public ObjectId getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public CategoryDto setCategory(Category category) {
        this.category = category;
        return this;
    }

    public double getBudget() {
        return budget;
    }

    public CategoryDto setBudget(double budget) {
        this.budget = budget;
        return this;
    }

    public double getBudgetSpent() {
        return budgetSpent;
    }

    public CategoryDto setBudgetSpent(double budgetSpent) {
        this.budgetSpent = budgetSpent;
        return this;
    }
}
