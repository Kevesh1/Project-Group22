package dataaccess.mongodb.dto.categories;

import budgetapp.model.categories.Category;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public final class CategoryItemDto {

    private ObjectId _id;

    private ObjectId budgetMonth;

    private Category category;

    private String name;

    private double budget;

    private double budgetSpent;

    public ObjectId getId() {
        return _id;
    }

    public CategoryItemDto setId(ObjectId _id) {
        this._id = _id;
        return this;
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

    public ObjectId getBudgetMonth() {
        return budgetMonth;
    }

    public CategoryItemDto setBudgetMonth(ObjectId budgetMonth) {
        this.budgetMonth = budgetMonth;
        return this;
    }

    public String getName() {
        return name;
    }

    public CategoryItemDto setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "id=" + _id +
                ", category=" + category +
                ", budget=" + budget +
                ", budgetSpent=" + budgetSpent +
                '}';
    }
}
