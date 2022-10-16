package dataaccess.mongodb.dao;

import com.mongodb.client.model.Filters;
import dataaccess.mongodb.MongoDBService;
import dataaccess.mongodb.dto.BudgetMonthDto;
import budgetapp.model.BudgetMonth;
import com.mongodb.client.MongoCollection;
import dataaccess.mongodb.dto.account.AccountDto;
import dataaccess.mongodb.dto.account.UserDto;
import dataaccess.mongodb.dto.categories.CategoryItemDto;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.config.Configuration;

import javax.swing.text.html.Option;
import java.time.Month;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BudgetMonthDao implements IBudgetMonthDao {


    private final MongoCollection<BudgetMonthDto> collection = MongoDBService.database.getCollection(
        BudgetMonth.class.getSimpleName().toLowerCase(Locale.ROOT), BudgetMonthDto.class);

    private final ModelMapper modelMapper;

    public BudgetMonthDao() {
        modelMapper = new ModelMapper();
        configureModelMapper(modelMapper);


    }

    private YearMonth mapYearMonth(int year, Month month) {
        return YearMonth.of(year, month);
    }

    private void configureModelMapper(ModelMapper modelMapper) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        /*modelMapper.createTypeMap(BudgetMonthDto.class, BudgetMonth.class)
                .addMappings(
                        new PropertyMap<BudgetMonthDto, BudgetMonth>() {
                            @Override
                            protected void configure() {
                                // define a converter that takes the whole "person"
                                using(ctx -> mapYearMonth(
                                        ((BudgetMonthDto) ctx.getSource()).getYear(),
                                        ((BudgetMonthDto) ctx.getSource()).getMonth())
                                )
                                        // Map the compliete source here
                                        .map(source, BudgetMonth.class);
                            }
                        });*/
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
    public Optional<List<BudgetMonth>> getAllBudgetMonthsByUserId(String id) {
        ObjectId oid = new ObjectId(id);
        List<BudgetMonth> budgetMonths = new ArrayList<BudgetMonth>();
        collection.find(Filters.eq("user", oid), BudgetMonth.class)
                .into(new ArrayList<>())
                .forEach(budgetMonthDto -> budgetMonths.add(
                modelMapper.map(budgetMonthDto, BudgetMonth.class)));
        Optional<List<BudgetMonth>> newBudgetMonths = Optional.empty();
        if (!budgetMonths.isEmpty()) {
            newBudgetMonths = Optional.of(budgetMonths);
        }
        return newBudgetMonths;
    }

    @Override
    public void updateBudgetMonth(BudgetMonth budgetMonth) {
        BudgetMonthDto budgetMonthDto = modelMapper.map(budgetMonth, BudgetMonthDto.class);
    }

    @Override
    public void addBudgetMonth(BudgetMonth budgetMonth) {

        BudgetMonthDto budgetMonthDto = modelMapper.map(budgetMonth, BudgetMonthDto.class);
        budgetMonthDto.setId(new ObjectId());
        List<CategoryItemDto> categoryItems = budgetMonth.getCategories()
                .stream()
                .map(categoryItem -> modelMapper.map(categoryItem, CategoryItemDto.class))
                .collect(Collectors.toList());
        //budgetMonthDto.setCategories(categoryItems);
        collection.insertOne(budgetMonthDto);

    }

    @Override
    public void addManyBudgetMonths(List<BudgetMonth> budgetMonths, String userId) {
        List<BudgetMonthDto> budgetMonthDtos = new ArrayList<>();
        budgetMonths.forEach(budgetMonth -> budgetMonthDtos
                .add(modelMapper.map(budgetMonth, BudgetMonthDto.class)));
        collection.insertMany(budgetMonthDtos);
    }

    @Override
    public void deleteBudgetMonth(BudgetMonth budgetMonth) {

    }
}
