package dataaccess.mongodb.dto.transactions;

import org.bson.types.ObjectId;

import java.sql.Date;
import java.time.YearMonth;

public class IncomeDto {

    private ObjectId id;

    private ObjectId budgetMonth;

    private double sum;

    private String annotation;

    private Date date;

    public Date getDate() {
        return date;
    }

    public IncomeDto setDate(Date date) {
        this.date = date;
        return this;
    }

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

    public ObjectId getBudgetMonth() {
        return budgetMonth;
    }

    public IncomeDto setBudgetMonth(ObjectId budgetMonth) {
        this.budgetMonth = budgetMonth;
        return this;
    }

    @Override
    public String toString() {
        return "IncomeDto{" +
                "id=" + id +
                ", budgetMonth=" + budgetMonth +
                ", sum=" + sum +
                ", annotation='" + annotation + '\'' +
                ", date=" + date +
                '}';
    }
}
