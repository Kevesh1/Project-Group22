package dataaccess.mongodb.dao.transactions;

import budgetapp.model.transactions.Income;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import dataaccess.mongodb.MongoDBService;
import dataaccess.mongodb.dto.transactions.IncomeDto;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class IncomeDao implements IIncomeDao {

    MongoCollection<IncomeDto> collection = MongoDBService.database.getCollection(
            Income.class.getSimpleName().toLowerCase(Locale.ROOT), IncomeDto.class);

    private ModelMapper modelMapper;
    public IncomeDao() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    @Override
    public Income addIncome(Income income, String budgetMonthId) {
        IncomeDto incomeDto = modelMapper.map(income, IncomeDto.class)
                .setBudgetMonth(new ObjectId(budgetMonthId));
        collection.insertOne(incomeDto);
        return modelMapper.map(incomeDto, Income.class);
    }

    @Override
    public Income deleteIncome(Income income) {
        collection.deleteOne(Filters.eq("_id", new ObjectId(income.getId())));
        return income;
    }

    @Override
    public List<Income> getAllIncomesByBudgetMonth(String budgetMonthId) {
        List<Income> incomes = new ArrayList<>();
        collection.find(Filters.eq("budgetMonth", new ObjectId(budgetMonthId)))
                .into(new ArrayList<>()).forEach(expenseDto -> incomes.add(modelMapper.map(expenseDto, Income.class)));
        return incomes;
    }
}
