package dataaccess.mongodb.dao.transactions;

import budgetapp.model.transactions.Expense;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface IExpenseDao {
    public Optional<Expense> getExpenseById(ObjectId id);
    public List<Expense> getAllExpenses();
    public void updateExpense(Expense expense);
    public void deleteExpense(Expense expense);
}