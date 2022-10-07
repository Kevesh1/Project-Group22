package budgetapp.model;

import budgetapp.model.categories.Category;

import java.time.YearMonth;

public class Income extends Transaction {
    private double sum;
    private String annotation;
    private String date;
    private YearMonth yearMonth;
    // Maybe not Category
    private Category category;

    public Income(Double sum, String annotation, String date) {
        super(sum,annotation,date, null);
    }


}
