package budgetapp.model.transactions;

import budgetapp.model.categories.Category;

import java.time.YearMonth;

public class Expense extends Transaction {
    private double cost;
    private String annotation;
    private String date;
    private YearMonth yearMonth;
    // Maybe not Category
    private Category category;

    public Expense(Double cost, String annotation, String date, Category category) {
        super(cost,annotation,date, category);
        this.category = category;
    }


}
