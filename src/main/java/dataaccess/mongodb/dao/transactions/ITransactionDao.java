package dataaccess.mongodb.dao.transactions;

import budgetapp.model.transactions.Transaction;

import java.util.List;

/**
 * @author Johannes
 */
public interface ITransactionDao {
    List<Transaction> getAllTransactionsByBudgetMonth(String budgetMonthId);
}
