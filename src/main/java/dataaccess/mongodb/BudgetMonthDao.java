package dataaccess.mongodb;

import dataaccess.mongodb.dto.BudgetMonthDto;
import budgetapp.model.BudgetMonth;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class BudgetMonthDao implements IBudgetMonthDao {


    private final MongoCollection<BudgetMonthDto> collection = MongoDBService.database.getCollection(
        BudgetMonthDto.class.getSimpleName().toLowerCase(Locale.ROOT), BudgetMonthDto.class);

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public Optional<BudgetMonth> getBudgetMonthById(ObjectId id) {
        return Optional.empty();
    }

    @Override
    public List<BudgetMonth> getAllBudgetMonths() {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        List<BudgetMonth> budgetMonths = new ArrayList<>();
        collection.find(new Document(), BudgetMonth.class)
                .into(new ArrayList<>())
                .forEach(budgetMonthDto -> budgetMonths.add(
                modelMapper.map(budgetMonthDto, BudgetMonth.class)));
        return budgetMonths;
    }

    @Override
    public void updateBudgetMonth(BudgetMonth budgetMonth) {
        BudgetMonthDto budgetMonthDto = modelMapper.map(budgetMonth, BudgetMonthDto.class);
        collection.insertOne(budgetMonthDto);
    }

    @Override
    public void deleteBudgetMonth(BudgetMonth budgetMonth) {

    }
}
