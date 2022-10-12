package budgetapp.model.transactions;

import budgetapp.model.categories.Category;
import budgetapp.model.categories.CategorySubItem;

import java.time.YearMonth;

public class Expense {

    private double sum;
    private String annotation;
    private String date;
    private YearMonth yearMonth;
    private Category category;
    private CategorySubItem subCategory;

    public Expense(Double sum, String annotation, String date, Category category, CategorySubItem subCategory) {
        this.sum = sum;
        this.annotation = annotation;
        this.date = date;
        this.category = category;
        this.subCategory = subCategory;
    }

    public CategorySubItem getSubCategory() {
        return subCategory;
    }


    public double getSum() {
        return sum;
    }
    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
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



    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
}
