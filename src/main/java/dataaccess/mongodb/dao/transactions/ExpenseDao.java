package dataaccess.mongodb.dao.transactions;

import budgetapp.model.BudgetMonth;
import budgetapp.model.categories.CategoryItem;
import budgetapp.model.transactions.Expense;
import com.mongodb.client.MongoCollection;
import dataaccess.mongodb.MongoDBService;
import dataaccess.mongodb.dto.transactions.ExpenseDto;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class ExpenseDao implements IExpenseDao {

    MongoCollection<ExpenseDto> collection = MongoDBService.database.getCollection(
            ExpenseDto.class.getSimpleName().toLowerCase(Locale.ROOT), ExpenseDto.class);

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public Optional<Expense> getExpenseById(ObjectId id) {
        return Optional.empty();
    }

    @Override
    public List<Expense> getAllExpensesByBudgetMonth(BudgetMonth budgetMonth) {
        return null;
    }

    @Override
    public List<Expense> getAllExpensesByCategory(CategoryItem categoryItem) {
        return null;
    }

    @Override
    public void updateExpense(Expense expense) {
        ExpenseDto expenseDto = modelMapper.map(expense, ExpenseDto.class);
        collection.insertOne(expenseDto);
    }

    @Override
    public void deleteExpense(Expense expense) {

    }
}
