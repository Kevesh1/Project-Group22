package DAO.MongoDB;

import budgetapp.model.account.Account;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface IBudgetMonthDao {
    public Optional<Account> getBudgetMonthById(ObjectId id);
    public List<Account> getAllBudgetMonths();
    public void updateBudgetMonth(Account account);
    public void deleteBudgetMonth(Account account);
}
