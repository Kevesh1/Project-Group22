package dataaccess.mongodb.dto.transactions;

import org.bson.types.ObjectId;

import java.time.YearMonth;

public class IncomeDto {
    private ObjectId id;

    private double sum;

    private String annotation;

    private String date;

    private YearMonth yearmonth;

    public ObjectId getId() {
        return id;
    }

    public IncomeDto setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public double getSum() {
        return sum;
    }

    public IncomeDto setSum(double sum) {
        this.sum = sum;
        return this;
    }

    public String getAnnotation() {
        return annotation;
    }

    public IncomeDto setAnnotation(String annotation) {
        this.annotation = annotation;
        return this;
    }

    public String getDate() {
        return date;
    }

    public IncomeDto setDate(String date) {
        this.date = date;
        return this;
    }

    public YearMonth getYearmonth() {
        return yearmonth;
    }

    public IncomeDto setYearmonth(YearMonth yearmonth) {
        this.yearmonth = yearmonth;
        return this;
    }

    @Override
    public String toString() {
        return "IncomeDto{" +
                "id=" + id +
                ", sum=" + sum +
                ", annotation='" + annotation + '\'' +
                ", date='" + date + '\'' +
                ", yearmonth=" + yearmonth +
                '}';
    }
}
