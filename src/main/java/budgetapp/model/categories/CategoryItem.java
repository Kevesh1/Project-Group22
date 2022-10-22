package budgetapp.model.categories;

import javafx.scene.image.Image;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CategoryItem extends AbstractCategoryItem{

    private List<CategorySubItem> subCategories = new ArrayList<>();

    private Category category;


    public CategoryItem(Category category) {
        super(category.toString(), 0);
        this.category = category;
    }

    public CategoryItem() {
    }

    //TODO - MOVE FROM MODEL
    public Image applyIcon() {
        return (new Image((Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                "budgetapp/img/categories/" + getCategory().toString().toLowerCase() + ".png")))));
    }

    public List<CategorySubItem> getSubCategories() {
        return subCategories;
    }

    public void addSubCategory(CategorySubItem subCategory) {
        subCategories.add(subCategory);
        incrementBudget(subCategory.getBudget());
    }

    public void setSubCategories(List<CategorySubItem> subCategories) {
        this.subCategories.addAll(subCategories);
    }

    public void removeSubcategory(CategorySubItem subCategory) {
        decrementBudget(subCategory.getBudget());
        subCategories.remove(subCategory);
    }


    //Overload
    /*public void addSubcategoryBudget(){
        incrementBudget((int) subCategories.get(subCategories.size() - 1).getBudget());

    }*/
    //Overload
  /*  public void addSubcategoryBudget(double amo){
        incrementBudget((int)subCategory.getBudget());

    }*/

    public void removeSubcategoryBudget(CategorySubItem subCategory){
        decrementBudget((int)subCategory.getBudget());
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;

    }

    public void calculateBudget() {
        int budget = 0;
        for (CategorySubItem subCategory : subCategories) {
            budget += subCategory.getBudget();
        }
        setBudget(budget);
    }
}
