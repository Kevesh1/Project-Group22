package dataaccess.mongodb.dao.transactions;

import budgetapp.model.transactions.Income;
import budgetapp.model.transactions.Transaction;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import dataaccess.mongodb.MongoDBService;
import dataaccess.mongodb.dto.transactions.ExpenseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Johannes
 */
public class TransactionDao implements ITransactionDao{

    MongoCollection<ExpenseDto> collection = MongoDBService.database.getCollection(
            Transaction.class.getSimpleName().toLowerCase(Locale.ROOT), ExpenseDto.class);

    private ModelMapper modelMapper;

    private ExpenseDao expenseDao;

    private IncomeDao incomeDao;
    public TransactionDao() {
        expenseDao = new ExpenseDao();
        incomeDao = new IncomeDao();

        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    @Override
    public List<Transaction> getAllTransactionsByBudgetMonth(String budgetMonthId) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.addAll(incomeDao.getAllIncomesByBudgetMonth(budgetMonthId));
        transactions.addAll(expenseDao.getAllExpensesByBudgetMonth(budgetMonthId));
        return transactions;
    }

}
