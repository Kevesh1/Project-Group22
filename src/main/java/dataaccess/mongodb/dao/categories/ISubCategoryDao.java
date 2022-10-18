package dataaccess.mongodb.dao.categories;

import budgetapp.model.BudgetMonth;
import budgetapp.model.categories.Category;
import budgetapp.model.categories.CategoryItem;
import budgetapp.model.categories.CategorySubItem;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface ISubCategoryDao {
    public Optional<CategorySubItem> getSubCategoryById(ObjectId id);

    List<CategorySubItem> getAllSubCategoriesByCategory(ObjectId id, Category category);

    List<CategorySubItem> getAllSubCategoriesByCategory(CategoryItem category);

    public List<CategorySubItem> getAllSubCategories();

    void addSubCategory(CategorySubItem subCategory, String category);

    public void updateSubCategory(CategorySubItem subCategory);

    public void deleteSubCategory(CategorySubItem subCategory);
}
