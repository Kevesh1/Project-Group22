package budgetapp.model.transactions;

import budgetapp.model.categories.Category;

import java.sql.Date;
import java.time.YearMonth;

public abstract class Transaction {

    private double sum;
    private String annotation;
    private Date date;
    private YearMonth yearMonth;
    private Category category;

    public Transaction(Double sum, String annotation, Date date) {
        this.sum = sum;
        this.annotation = annotation;
        this.date = date;
    }


    public double getSum() {
        return sum;
    }
    public void setSum(double sum) {
        this.sum = sum;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
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
