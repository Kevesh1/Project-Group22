package dataaccess.mongodb.dto.transactions;

import budgetapp.model.categories.Category;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.YearMonth;
import java.util.Date;

/**
 * @author Johannes
 */
public final class ExpenseDto {

    private ObjectId _id;

    private ObjectId budgetMonth;

    private double sum;

    private Category category;

    private String annotation;

    private Date date;

    private YearMonth yearmonth;

    public ObjectId getId() {
        return _id;
    }

    public ExpenseDto setId(ObjectId _id) {
        this._id = _id;
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

    public Date getDate() {
        return date;
    }

    public ExpenseDto setDate(Date date) {
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
                "id=" + _id +
                ", sum=" + sum +
                ", annotation='" + annotation + '\'' +
                ", date='" + date + '\'' +
                ", yearmonth=" + yearmonth +
                '}';
    }

    public ObjectId getBudgetMonth() {
        return budgetMonth;
    }

    public ExpenseDto setBudgetMonth(ObjectId budgetMonth) {
        this.budgetMonth = budgetMonth;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public ExpenseDto setCategory(Category category) {
        this.category = category;
        return this;
    }
}
