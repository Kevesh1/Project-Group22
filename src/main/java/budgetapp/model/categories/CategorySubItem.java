package budgetapp.model.categories;

import budgetapp.model.transactions.Expense;

import java.util.ArrayList;
import java.util.List;

public class CategorySubItem extends AbstractCategoryItem{

    private List<Expense> expenses;

    public CategorySubItem(double budget, String categorySubName) {
        super(categorySubName, budget);
        expenses = new ArrayList<>();
    }

    public void addExpense(Expense expense){
        expenses.add(expense);
    }

    public List<Expense> getExpenses(){
        return expenses;
    }
}