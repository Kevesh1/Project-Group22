package DAO.MongoDB.DTO.categories;

import budgetapp.model.categories.Category;
import org.bson.types.ObjectId;

public final class CategoryItemDto {

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

    public CategoryItemDto setCategory(Category category) {
        this.category = category;
        return this;
    }

    public double getBudget() {
        return budget;
    }

    public CategoryItemDto setBudget(double budget) {
        this.budget = budget;
        return this;
    }

    public double getBudgetSpent() {
        return budgetSpent;
    }

    public CategoryItemDto setBudgetSpent(double budgetSpent) {
        this.budgetSpent = budgetSpent;
        return this;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "id=" + id +
                ", category=" + category +
                ", budget=" + budget +
                ", budgetSpent=" + budgetSpent +
                '}';
    }
}
