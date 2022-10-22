package dataaccess.mongodb.dao.categories;

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
    public List<CategorySubItem> getAllSubCategoriesByCategory(String category) {
        List<CategorySubItem> categorySubItems = new ArrayList<>();
        collection.find(Filters.eq("category", new ObjectId(category))).into(new ArrayList<>())
                .forEach(categorySubItem -> categorySubItems.add(modelMapper.map(categorySubItem, CategorySubItem.class)));
        return categorySubItems;
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
    public CategorySubItem addSubCategory(CategorySubItem subCategory, String category) {
        SubCategoryItemDto subCategoryItemDto = modelMapper.map(subCategory, SubCategoryItemDto.class);
        subCategoryItemDto.setCategory(new ObjectId(category));
        collection.insertOne(subCategoryItemDto);
        return modelMapper.map(subCategoryItemDto, CategorySubItem.class);

    }

    @Override
    public CategorySubItem updateSubCategory(CategorySubItem subCategory) {
         SubCategoryItemDto subCategoryItemDto = collection.findOneAndReplace(Filters
                 .eq("_id", new ObjectId(subCategory.getId())), modelMapper.map(subCategory, SubCategoryItemDto.class));
         return modelMapper.map(subCategoryItemDto, CategorySubItem.class);
    }

    @Override
    public CategorySubItem deleteSubCategory(CategorySubItem categorySubItem) {
        collection.findOneAndDelete(Filters
                .eq("_id", new ObjectId(categorySubItem.getId()) ));
        return categorySubItem;

    }
}
