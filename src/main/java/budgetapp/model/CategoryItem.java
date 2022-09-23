package budgetapp.model;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class CategoryItem extends AbstractCategoryItem{
    private List<CategorySubItem> subCategories;


    public CategoryItem(double budget, @NotNull Category category) {
        super(category.toString(), budget, category);
        this.subCategories = new ArrayList<CategorySubItem>();
    }

    @Override
    public Image getIcon() {
        return (new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                "budgetapp/img/categories/" + getCategory().toString().toLowerCase() + ".png"))));
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
