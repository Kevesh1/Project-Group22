package budgetapp.model.transactions;

import budgetapp.model.categories.Category;

import java.time.YearMonth;

public abstract class Transaction {

    private double sum;
    private String annotation;
    private String date;
    private YearMonth yearMonth;

    public Transaction(Double sum, String annotation, String date) {
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


}
