package dataaccess.mongodb.dto;

import budgetapp.model.transactions.Expense;
import dataaccess.mongodb.dto.categories.CategoryItemDto;
import org.bson.types.ObjectId;

import java.time.Month;
import java.util.List;

public class BudgetMonthDto {

    private ObjectId _id;

    private ObjectId user;

    private int year;

    private Month month;

    //private List<CategoryItemDto> categoryItems;

    public ObjectId getId() {
        return _id;
    }

    public ObjectId getUser() {
        return user;
    }

    public BudgetMonthDto setUser(ObjectId user) {
        this.user = user;
        return this;
    }

    public int getYear() {
        return year;
    }

    public BudgetMonthDto setYear(int year) {
        this.year = year;
        return this;
    }

    public Month getMonth() {
        return month;
    }

    public BudgetMonthDto setMonth(Month month) {
        this.month = month;
        return this;
    }

    /*public List<CategoryItemDto> getCategories() {
        return categoryItems;
    }

    public BudgetMonthDto setCategories(List<CategoryItemDto> categories) {
        this.categoryItems = categories;
        return this;
    }*/

    public BudgetMonthDto setId(ObjectId _id) {
        this._id = _id;
        return this;
    }

    @Override
    public String toString() {
        return "BudgetMonthDto{" +
                "_id=" + _id +
                ", user=" + user +
                ", year=" + year +
                ", month=" + month +
                '}';
    }
}
