package DAO.MongoDB.DTO;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.Month;
import java.util.List;

public class BudgetMonthDto {
    @BsonId
    private ObjectId id;

    private String userId;

    private int year;

    private Month month;

    private List<CategoryDto> categories;

    public ObjectId getId() {
        return id;
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

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public BudgetMonthDto setCategories(List<CategoryDto> categories) {
        this.categories = categories;
        return this;
    }
}
