package dataaccess.mongodb.dao.categories;

import budgetapp.model.categories.Category;
import budgetapp.model.categories.CategoryItem;
import budgetapp.model.categories.CategorySubItem;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface ISubCategoryDao {
    public Optional<CategorySubItem> getSubCategoryById(ObjectId id);

    List<CategorySubItem> getAllSubCategoriesByCategory(String category);

    public List<CategorySubItem> getAllSubCategories();

    CategorySubItem addSubCategory(CategorySubItem subCategory, String category);

    public void updateSubCategory(CategorySubItem subCategory);

    public void deleteSubCategory(CategorySubItem subCategory);
}
