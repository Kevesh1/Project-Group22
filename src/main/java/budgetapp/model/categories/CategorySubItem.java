package budgetapp.model.categories;

import budgetapp.model.transactions.Expense;

import java.util.ArrayList;
import java.util.List;

public class CategorySubItem extends AbstractCategoryItem{

    private List<Expense> expenses = new ArrayList<>();

    public CategorySubItem(double budget, String categorySubName) {
        super(categorySubName, budget);
    }

    public CategorySubItem() {

    };

    public void addExpense(Expense expense){
        expenses.add(expense);
    }

    public List<Expense> getExpenses(){
        return expenses;
    }


}