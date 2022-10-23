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

    /**
     * this method gets Icon
     * @return Image
     */

    //TODO - MOVE FROM MODEL
    public Image applyIcon() {
        return (new Image((Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                "budgetapp/img/categories/" + getCategory().toString().toLowerCase() + ".png")))));
    }

    /**
     * this method gets subCategories
     * @return list
     */

    public List<CategorySubItem> getSubCategories() {
        return subCategories;
    }

    /**
     * this method adds subCategory
     * @param subCategory
     */
    public void addSubCategory(CategorySubItem subCategory) {
        subCategories.add(subCategory);
        incrementBudget(subCategory.getBudget());
    }

    /**
     * this method updates the budget
     * @param subCategories
     */
    public void setSubCategories(List<CategorySubItem> subCategories) {
        setBudget(0);
        subCategories.forEach(this::addSubCategory);
    }

    /**
     * this method removes a subCategory
     * @param subCategory
     */

    public void removeSubcategory(CategorySubItem subCategory) {
        decrementBudget(subCategory.getBudget());
        System.out.println("nPre");
        subCategories.forEach(subCategory1 -> System.out.println(subCategory1.getName()));
        subCategories.remove(subCategory);
        System.out.println("nAFT");
        subCategories.forEach(subCategory1 -> System.out.println(subCategory1.getName()));
    }


    //Overload
    /*public void addSubcategoryBudget(){
        incrementBudget((int) subCategories.get(subCategories.size() - 1).getBudget());

    }*/
    //Overload
  /*  public void addSubcategoryBudget(double amo){
        incrementBudget((int)subCategory.getBudget());

    }*/

    /**
     * this method removes subCategory budget
     * @param subCategory
     */

    public void removeSubcategoryBudget(CategorySubItem subCategory){
        decrementBudget((int)subCategory.getBudget());
    }

    /**
     * this method gets category
     * @return category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * this method sets category
     * @param category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * this method calculates the budget
     * @return budget
     */
    public double calculateBudget() {
        int budget = 0;
        for (CategorySubItem subCategory : subCategories) {
            budget += subCategory.getBudget();
        }
        setBudget(budget);
        return budget;
    }
}
