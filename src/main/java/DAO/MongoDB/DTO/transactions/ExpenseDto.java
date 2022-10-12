package DAO.MongoDB.DTO.transactions;

import org.bson.types.ObjectId;

import java.time.YearMonth;

public final class ExpenseDto {

    private ObjectId id;

    private double sum;

    private String annotation;

    private String date;

    private YearMonth yearmonth;

    public ObjectId getId() {
        return id;
    }

    public ExpenseDto setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public double getSum() {
        return sum;
    }

    public ExpenseDto setSum(double sum) {
        this.sum = sum;
        return this;
    }

    public String getAnnotation() {
        return annotation;
    }

    public ExpenseDto setAnnotation(String annotation) {
        this.annotation = annotation;
        return this;
    }

    public String getDate() {
        return date;
    }

    public ExpenseDto setDate(String date) {
        this.date = date;
        return this;
    }

    public YearMonth getYearmonth() {
        return yearmonth;
    }

    public ExpenseDto setYearmonth(YearMonth yearmonth) {
        this.yearmonth = yearmonth;
        return this;
    }

    @Override
    public String toString() {
        return "ExpenseDto{" +
                "id=" + id +
                ", sum=" + sum +
                ", annotation='" + annotation + '\'' +
                ", date='" + date + '\'' +
                ", yearmonth=" + yearmonth +
                '}';
    }
}
