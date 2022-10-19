package dataaccess.mongodb.dao;

import budgetapp.model.categories.CategoryItem;
import com.mongodb.client.model.Filters;
import dataaccess.mongodb.MongoDBService;
import dataaccess.mongodb.dao.categories.CategoryDao;
import dataaccess.mongodb.dao.categories.SubCategoryDao;
import dataaccess.mongodb.dto.BudgetMonthDto;
import budgetapp.model.BudgetMonth;
import com.mongodb.client.MongoCollection;
import dataaccess.mongodb.dto.categories.CategoryItemDto;
import org.bson.types.ObjectId;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.spi.MappingContext;

import java.time.Month;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

public class BudgetMonthDao implements IBudgetMonthDao {


    private final MongoCollection<BudgetMonthDto> collection = MongoDBService.database.getCollection(
        BudgetMonth.class.getSimpleName().toLowerCase(Locale.ROOT), BudgetMonthDto.class);

    private final ModelMapper modelMapper;

    private CategoryDao categoryDao;

    private SubCategoryDao subCategoryDao;

    public BudgetMonthDao() {
        categoryDao = new CategoryDao();
        subCategoryDao = new SubCategoryDao();
        modelMapper = new ModelMapper();
        configureModelMapper(modelMapper);
    }

    private void configureModelMapper(ModelMapper modelMapper) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        //modelMapper.addConverter(testConverter);


        /*modelMapper.createTypeMap(BudgetMonthDto.class, BudgetMonth.class)
                .addMappings(
                        new PropertyMap<BudgetMonthDto, BudgetMonth>() {
                            @Override
                            protected void configure() {
                                using(ctx -> mapYearMonth(
                                        ((BudgetMonthDto) ctx.getSource()).getYear(),
                                        ((BudgetMonthDto) ctx.getSource()).getMonth())
                                )
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
        List<BudgetMonth> budgetMonths = new ArrayList<>();

        collection.find(Filters.eq("user", oid), BudgetMonthDto.class)
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
        List<CategoryItemDto> categoryItems = budgetMonth.getCategoryItems()
                .stream()
                .map(categoryItem -> modelMapper.map(categoryItem, CategoryItemDto.class))
                .collect(Collectors.toList());
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

    @Override
    public List<BudgetMonth> initNewBudgetMonths(List<BudgetMonth> budgetMonths, String user) {
        List<BudgetMonthDto> budgetMonthDtos = new ArrayList<>();

        budgetMonths.forEach(budgetMonth -> budgetMonthDtos
                .add(modelMapper.map(budgetMonth, BudgetMonthDto.class)
                        .setUser(new ObjectId(user))));

        collection.insertMany(budgetMonthDtos);

        return budgetMonthDtos.stream().map(budgetMonthDto -> modelMapper.map(budgetMonthDto, BudgetMonth.class)).collect(Collectors.toList());

    }
}
