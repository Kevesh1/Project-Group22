package dataaccess.mongodb;

import budgetapp.model.BudgetMonth;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface IBudgetMonthDao {
    public Optional<BudgetMonth> getBudgetMonthById(ObjectId id);
    public List<BudgetMonth> getAllBudgetMonths();
    public void updateBudgetMonth(BudgetMonth budgetMonth);
    public void deleteBudgetMonth(BudgetMonth budgetMonth);
}
