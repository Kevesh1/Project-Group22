package dataaccess.mongodb.dao.categories;

import budgetapp.model.categories.Category;
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

public class subCategoryDao implements ISubCategoryDao {

    MongoCollection<SubCategoryItemDto> collection = MongoDBService.database.getCollection(
            SubCategoryItemDto.class.getSimpleName().toLowerCase(Locale.ROOT), SubCategoryItemDto.class);

    ModelMapper modelMapper = new ModelMapper();

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
    public List<CategorySubItem> getAllSubCategories() {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        List<CategorySubItem> subCategories = new ArrayList<>();
        collection.find(new Document(), CategorySubItem.class)
                .into(new ArrayList<>())
                .forEach(subCategoryDto -> subCategories.add(modelMapper.map(subCategoryDto, CategorySubItem.class)));
        return subCategories;
    }

    @Override
    public void updateSubCategory(CategorySubItem subCategory) {

    }

    @Override
    public void deleteSubCategory(CategorySubItem subCategory) {

    }
}
