package budgetapp.model;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CategoryItem extends AbstractCategoryItem{
    private final Category category;
    private List<CategorySubItem> subCategories;


    public CategoryItem(double budget, Image icon, @NotNull Category category) {
        super(category.name(), budget, icon);
        this.category = category;
        this.subCategories = new ArrayList<CategorySubItem>();
    }

    public Category getCategory() {
        return category;
    }



    public List<CategorySubItem> getSubCategories() {
        return subCategories;
    }

    public void addSubCategory(CategorySubItem subCategory) {
        subCategories.add(subCategory);
    }
    public void removeSubcategory(CategorySubItem subcategory) {
        subCategories.remove(subcategory);
    }
}
