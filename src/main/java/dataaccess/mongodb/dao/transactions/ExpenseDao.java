package dataaccess.mongodb.dao.transactions;

import budgetapp.model.categories.CategoryItem;
import budgetapp.model.transactions.Expense;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import dataaccess.mongodb.MongoDBService;
import dataaccess.mongodb.dto.transactions.ExpenseDto;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * @author Johannes
 */
public class ExpenseDao implements IExpenseDao {

    MongoCollection<ExpenseDto> collection = MongoDBService.database.getCollection(
            Expense.class.getSimpleName().toLowerCase(Locale.ROOT), ExpenseDto.class);

    private ModelMapper modelMapper;

    public ExpenseDao() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    @Override
    public Expense addExpense(Expense expense, String budgetMonthId) {
       ExpenseDto expenseDto = modelMapper.map(expense, ExpenseDto.class)
               .setBudgetMonth(new ObjectId(budgetMonthId));
       collection.insertOne(expenseDto);
       return modelMapper.map(expenseDto, Expense.class);
    }

    @Override
    public Optional<Expense> getExpenseById(ObjectId id) {
        return Optional.empty();
    }

    @Override
    public List<Expense> getAllExpensesByBudgetMonth(String budgetMonthId) {
        List<Expense> expenses = new ArrayList<>();
        collection.find(Filters.eq("budgetMonth", new ObjectId(budgetMonthId)))
                .into(new ArrayList<>()).forEach(expenseDto -> expenses.add(modelMapper.map(expenseDto, Expense.class)));
        return expenses;

    }

    @Override
    public List<Expense> getAllExpensesByCategory(CategoryItem categoryItem) {
        return null;
    }

    @Override
    public void updateExpense(Expense expense) {


    }

    @Override
    public Expense deleteExpense(Expense expense) {
        System.out.println(expense.getId());
        collection.deleteOne(Filters.eq("_id", new ObjectId(expense.getId())));
        return expense;
    }
}
