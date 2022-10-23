package budgetapp.controller.categories;

import budgetapp.model.categories.CategoryItem;
import budgetapp.model.categories.CategorySubItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;


public class SubCategoryController extends AnchorPane {

    final CategorySubItem subCategory;
    final CategoryItem categoryItem;
    private final CategoryListController categoryListController;

    private final CategoryController categoryController;

    @FXML
    private Label subCategoryName;

    @FXML
    private ProgressBar subCategoryProgressBar;

    @FXML
    private Label subCategoryBudget;

    public SubCategoryController(CategoryListController categoryListController, CategoryController categoryController, CategorySubItem subCategory) {

        this.categoryController = categoryController;
        this.categoryListController = categoryListController;
        this.subCategory = subCategory;
        categoryItem = categoryController.getCategoryItem();

        FXMLLoader root = new FXMLLoader(getClass().getResource("/budgetapp/fxml/categories/subCategory.fxml"));
        root.setRoot(this);
        root.setController(this);
        try {
            root.load();
        } catch (Exception ignored) {
        }

    }

    @FXML
    public void initialize() {
        setLabels();
    }

    public CategorySubItem getSubCategory(){
        return subCategory;
    }

    public CategoryItem getCategoryItem(){
        return categoryItem;
    }

    private void setLabels(){
        subCategoryName.setText(subCategory.getName());
        subCategoryBudget.setText(subCategory.getBudget() + " kr");
        subCategoryProgressBar.setProgress(subCategory.getBudgetSpent()/subCategory.getBudget());
    }

    @FXML
    private void removeSubCategory(){
        categoryListController.removeSubCategory(subCategory);

        /*int i = 0;
        if (selectedBudgetMonth.getTransactions() != null) {
            for (Transaction transaction : mainController.selectedBudgetMonth.getTransactions()){

                if (!(transaction instanceof Expense)) {
                    System.out.println("GOES IN ANTI IF");
                    continue;
                }
                if (subCategory.getExpenses().get(i) == transaction){
                    mainController.selectedBudgetMonth.removeTransaction(transaction);
                    System.out.println("INSIDE FOR");
                }
                i+=1;

                System.out.println("AFTER FOR LOOP");
            }
            categoryItem.removeSubcategory(subCategory);
            categoryItem.removeSubcategoryBudget(subCategory);
            //categoryController.updateSubCategories();
            mainController.updateTransactions();
            mainController.updateMainView();
        }*/
    }

    @FXML
    private void getEditSubCategoryWindow(){
        categoryListController.showEditSubCategoryWindow(this);
    }
}
