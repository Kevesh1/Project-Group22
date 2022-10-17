package dataaccess.mongodb.dao.categories;

import budgetapp.model.BudgetMonth;
import budgetapp.model.categories.CategoryItem;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import dataaccess.mongodb.MongoDBService;
import dataaccess.mongodb.dto.categories.CategoryItemDto;
import org.bson.types.ObjectId;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.spi.MappingContext;

import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CategoryDao implements ICategoryDao{

    MongoCollection<CategoryItemDto> collection = MongoDBService.database.getCollection(
            CategoryItem.class.getSimpleName().toLowerCase(Locale.ROOT), CategoryItemDto.class);

    private ModelMapper modelMapper;

    public CategoryDao() {
        modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }



    @Override
    public List<CategoryItem> getAllCategoriesByBudgetMonth(String budgetMonthId) {
        List<CategoryItem> categoryItems = new ArrayList<>();
        collection.find(Filters.eq("budgetMonth", budgetMonthId)).into(new ArrayList<>())
                .forEach(categoryItemDto -> categoryItems
                        .add(modelMapper.map(categoryItemDto, CategoryItem.class)));
        return categoryItems;
    }

    @Override
    public CategoryItem addCategory(CategoryItem categoryItem, String budgetMonth) {
        CategoryItemDto categoryItemDto = modelMapper.map(categoryItem, CategoryItemDto.class);
        categoryItemDto.setBudgetMonth(new ObjectId(budgetMonth));
        collection.insertOne(categoryItemDto);
        return modelMapper.map(categoryItemDto, CategoryItem.class);
    }

    @Override
    public List<CategoryItem> addCategories(List<CategoryItem> categoryItem, String budgetMonth) {
        List<CategoryItemDto> categoryItemDto = new ArrayList<>();
        categoryItem.forEach(categoryItem1 -> categoryItemDto
                .add(modelMapper.map(categoryItem1, CategoryItemDto.class)
                        .setBudgetMonth(new ObjectId(budgetMonth))));
        collection.insertMany(categoryItemDto);
        return categoryItemDto.stream()
                .map(categoryItemDto1 -> modelMapper.map(categoryItemDto1, CategoryItem.class))
                .collect(Collectors.toList());
    }

    @Override
    public void setCategoryItems(List<CategoryItem> categoryItems, List<BudgetMonth> budgetMonths) {
        budgetMonths.forEach(budgetMonth -> budgetMonth
                .setCategoryItems(
                        addCategories(categoryItems, budgetMonth.getId())));
    }

}
