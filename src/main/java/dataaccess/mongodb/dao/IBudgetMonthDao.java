package dataaccess.mongodb.dao;

import budgetapp.model.BudgetMonth;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface IBudgetMonthDao {

    public Optional<BudgetMonth> getBudgetMonthById(ObjectId id);

    public List<BudgetMonth> getAllBudgetMonths();

    Optional<List<BudgetMonth>> getAllBudgetMonthsByUserId(String id);

    public BudgetMonth updateBudgetMonth(BudgetMonth budgetMonth);

    BudgetMonth addBudgetMonth(BudgetMonth budgetMonth);


    void addManyBudgetMonths(List<BudgetMonth> budgetMonths, String userId);

    public void deleteBudgetMonth(BudgetMonth budgetMonth);

    List<BudgetMonth> initNewBudgetMonths(List<BudgetMonth> budgetMonths, String user);
}
