package dataaccess.mongodb.dto;

import dataaccess.mongodb.dto.categories.CategoryItemDto;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.time.Month;
import java.util.List;

public class BudgetMonthDto {
    @BsonId
    private ObjectId id;

    private String userId;

    private int year;

    private Month month;

    private List<CategoryItemDto> categories;

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
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", categories=" + categories +
                '}';
    }
}
