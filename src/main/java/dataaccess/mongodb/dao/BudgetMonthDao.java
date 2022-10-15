package dataaccess.mongodb;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import dataaccess.mongodb.dto.BudgetMonthDto;
import budgetapp.model.BudgetMonth;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;
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

    private final ModelMapper modelMapper;

    public BudgetMonthDao() {
        modelMapper = new ModelMapper();
        configureModelMapper(modelMapper);
    }

    private void configureModelMapper(ModelMapper modelMapper) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    @Override
    public Optional<BudgetMonth> getBudgetMonthById(ObjectId id) {
        return Optional.empty();
    }

    @Override
    public List<BudgetMonth> getAllBudgetMonths() {
        return null;
    }

    @Override
    public Optional<List<BudgetMonth>> getAllBudgetMonthsByUserId(ObjectId id) {

        List<BudgetMonth> budgetMonths = new ArrayList<>();
        collection.find(new Document(), BudgetMonth.class)
                .into(new ArrayList<>())
                .forEach(budgetMonthDto -> budgetMonths.add(
                modelMapper.map(budgetMonthDto, BudgetMonth.class)));
        return Optional.of(budgetMonths);
    }

    @Override
    public void updateBudgetMonth(BudgetMonth budgetMonth) {
        BudgetMonthDto budgetMonthDto = modelMapper.map(budgetMonth, BudgetMonthDto.class);
    }

    @Override
    public void addBudgetMonth(BudgetMonth budgetMonth) {
        BudgetMonthDto budgetMonthDto = modelMapper.map(budgetMonth, BudgetMonthDto.class);
        collection.insertOne(budgetMonthDto);

    }

    @Override
    public void addManyBudgetMonths(List<BudgetMonth> budgetMonths) {

    }

    @Override
    public void deleteBudgetMonth(BudgetMonth budgetMonth) {

    }
}
