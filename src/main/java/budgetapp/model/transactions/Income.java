package budgetapp.model.transactions;

import budgetapp.model.categories.Category;

import java.sql.Date;
import java.time.YearMonth;

public class Income extends Transaction {


    public Income(Double sum, String annotation, Date date) {
        super(sum, annotation, date);
    }


}
