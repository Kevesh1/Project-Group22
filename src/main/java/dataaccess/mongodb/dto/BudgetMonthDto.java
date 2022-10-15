package dataaccess.mongodb.dto;

import budgetapp.model.transactions.Expense;
import dataaccess.mongodb.dto.categories.CategoryItemDto;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.time.Month;
import java.util.List;

public class BudgetMonthDto {

    private ObjectId _id;

    private String userId;

    private int year;

    private Month month;

    private List<CategoryItemDto> categories;

    private List<Expense> expenses;

    public ObjectId getId() {
        return _id;
    }

    public String getUserId() {
        return userId;
    }

    public BudgetMonthDto setUserId(String userId) {
        this.userId = userId;
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

    public List<CategoryItemDto> getCategories() {
        return categories;
    }

    public BudgetMonthDto setCategories(List<CategoryItemDto> categories) {
        this.categories = categories;
        return this;
    }

    @Override
    public String toString() {
        return "BudgetMonthDto{" +
                "id=" + _id +
                ", userId='" + userId + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", categories=" + categories +
                '}';
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public BudgetMonthDto setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
        return this;
    }

    public BudgetMonthDto setId(ObjectId _id) {
        this._id = _id;
        return this;
    }
}
