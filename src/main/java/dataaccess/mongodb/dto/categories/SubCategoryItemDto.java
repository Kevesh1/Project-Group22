package dataaccess.mongodb.dto.categories;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

/**
 * @author Johannes
 */
public final class SubCategoryItemDto {

    private ObjectId _id;

    private ObjectId category;

    private String name;

    private double budget;

    private double budgetSpent;

    public ObjectId getId() {
        return _id;
    }

    public SubCategoryItemDto setId(ObjectId _id) {
        this._id = _id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SubCategoryItemDto setName(String name) {
        this.name = name;
        return this;
    }

    public double getBudget() {
        return budget;
    }

    public SubCategoryItemDto setBudget(double budget) {
        this.budget = budget;
        return this;
    }

    public double getBudgetSpent() {
        return budgetSpent;
    }

    public SubCategoryItemDto setBudgetSpent(double budgetSpent) {
        this.budgetSpent = budgetSpent;
        return this;
    }

    @Override
    public String toString() {
        return "SubCategoryDto{" +
                "id=" + _id +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", budgetSpent=" + budgetSpent +
                '}';
    }

    public ObjectId getCategory() {
        return category;
    }

    public SubCategoryItemDto setCategory(ObjectId categoryId) {
        category = categoryId;
        return this;
    }
}
