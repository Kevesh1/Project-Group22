package budgetapp.model.transactions;

import budgetapp.model.categories.Category;

import java.time.YearMonth;
import java.sql.Date;

public class Transaction {

    /**
     * this class is an abstract parent class for Expense and Income
     */
    private String id;
    private double sum;
    private String annotation;
    private Date date;
    private YearMonth yearMonth;

    /**
     * Constructor is called when child class is initialized called
     * @param sum
     * @param annotation
     * @param date
     */
    public Transaction(double sum, String annotation, Date date) {
        this.sum = sum;
        this.annotation = annotation;
        this.date = date;
    }

    public Transaction() {

    }


    /**
     * This method gets sum
     * @return double
     */

    public double getSum() {
        return sum;
    }

    /**
     * This method sets sum
     * @param sum
     *
     */

    public void setSum(double sum) {
        this.sum = sum;
    }

    /**
     * this method gets Date
     * @return Date
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method sets date
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * this method gets annotation
     * @return annotation
     */
    public String getAnnotation() {
        return annotation;
    }

    /**
     * this method sets annotation
     * @param annotation
     */
    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    /**
     * this method gets id
     * @return id
     */

    public String getId() {
        return id;
    }

    /**
     * this method sets id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }
}
