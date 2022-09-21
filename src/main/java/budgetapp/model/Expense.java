package budgetapp.model;

import java.time.YearMonth;

public class Expense {
    private double cost;
    private String annotation;
    private int date;
    private YearMonth yearMonth;
    // Maybe not Category
    private Category category;

    public Expense() {

    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getDate() {
        return date;
    }
    public void setDate(int date) {
        this.date = date;
    }

    public String getAnnotation() {
        return annotation;
    }
    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }
    public YearMonth getYearMonth() {
        return yearMonth;
    }
    public void setYearMonth(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }
}