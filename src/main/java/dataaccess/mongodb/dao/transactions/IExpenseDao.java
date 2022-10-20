package dataaccess.mongodb.dao.transactions;

import budgetapp.model.BudgetMonth;
import budgetapp.model.categories.CategoryItem;
import budgetapp.model.transactions.Expense;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface IExpenseDao {

    Expense addExpense(Expense expense, String budgetMonthId);

    public Optional<Expense> getExpenseById(ObjectId id);

    List<Expense> getAllExpensesByBudgetMonth(String budgetMonthId);

    List<Expense> getAllExpensesByCategory(CategoryItem categoryItem);

    public void updateExpense(Expense expense);

    public void deleteExpense(Expense expense);
}
