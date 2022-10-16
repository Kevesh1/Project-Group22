package dataaccess.mongodb.dao;

import budgetapp.model.BudgetMonth;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface IBudgetMonthDao {

    public Optional<BudgetMonth> getBudgetMonthById(ObjectId id);

    public List<BudgetMonth> getAllBudgetMonths();

    Optional<List<BudgetMonth>> getAllBudgetMonthsByUserId(ObjectId id);

    public void updateBudgetMonth(BudgetMonth budgetMonth);

    void addBudgetMonth(BudgetMonth budgetMonth);

    void addManyBudgetMonths(List<BudgetMonth> budgetMonths);

    public void deleteBudgetMonth(BudgetMonth budgetMonth);
}