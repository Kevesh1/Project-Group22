package dataaccess.mongodb.dao.categories;

import budgetapp.model.categories.Category;
import budgetapp.model.categories.CategoryItem;
import budgetapp.model.categories.CategorySubItem;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import dataaccess.mongodb.MongoDBService;
import dataaccess.mongodb.dto.categories.SubCategoryItemDto;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class SubCategoryDao implements ISubCategoryDao {

    MongoCollection<SubCategoryItemDto> collection = MongoDBService.database.getCollection(
            CategorySubItem.class.getSimpleName().toLowerCase(Locale.ROOT), SubCategoryItemDto.class);

    private ModelMapper modelMapper;

    public SubCategoryDao() {
        modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    @Override
    public Optional<CategorySubItem> getSubCategoryById(ObjectId id) {
        SubCategoryItemDto subCategoryItemDto = collection.find(Filters.eq("_id", id), SubCategoryItemDto.class).first();
        return Optional.of(modelMapper.map(subCategoryItemDto, CategorySubItem.class));
    }

    @Override
    public List<CategorySubItem> getAllSubCategoriesByCategory(ObjectId id, Category category) {
        return null;
    }

    @Override
    public List<CategorySubItem> getAllSubCategoriesByCategory(CategoryItem category) {
        /*modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        List<CategorySubItem> subCategories = new ArrayList<>();
        collection.find(
                Filters.eq("_id", category.)new Document(), CategorySubItem.class)
                .into(new ArrayList<>())
                .forEach(subCategoryDto -> subCategories.add(modelMapper.map(subCategoryDto, CategorySubItem.class)));*/
        return null;
    }

    @Override
    public List<CategorySubItem> getAllSubCategories() {

        List<CategorySubItem> subCategories = new ArrayList<>();
        collection.find(new Document(), CategorySubItem.class)
                .into(new ArrayList<>())
                .forEach(subCategoryDto -> subCategories.add(modelMapper.map(subCategoryDto, CategorySubItem.class)));
        return subCategories;
    }
    
    @Override
    public void addSubCategory(CategorySubItem subCategory, String category) {
        SubCategoryItemDto subCategoryItemDto = modelMapper.map(subCategory, SubCategoryItemDto.class);
        subCategoryItemDto.setCategory(new ObjectId(category));
        collection.insertOne(subCategoryItemDto);

    }

    @Override
    public void updateSubCategory(CategorySubItem subCategory) {

    }

    @Override
    public void deleteSubCategory(CategorySubItem subCategory) {

    }
}
