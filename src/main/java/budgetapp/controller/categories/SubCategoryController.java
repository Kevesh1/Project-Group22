package budgetapp.controller.categories;

import budgetapp.controller.MainController;
import budgetapp.model.categories.CategoryItem;
import budgetapp.model.categories.CategorySubItem;
import budgetapp.model.transactions.Expense;
import budgetapp.model.transactions.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;


public class SubCategoryController extends AnchorPane {

    private final CategoryController categoryController;
    private final CategorySubItem subCategory;
    private final MainController mainController;
    private final CategoryItem categoryItem;

    @FXML
    private Label subCategoryName;

    @FXML
    private ProgressBar subCategoryProgressBar;

    @FXML
    private Label subCategoryBudget;



    public SubCategoryController(CategoryController categoryController, CategorySubItem subCategory) {
        FXMLLoader root = new FXMLLoader(getClass().getResource("/budgetapp/fxml/subCategory.fxml"));
        root.setRoot(this);
        root.setController(this);
        try {
            root.load();
        } catch (Exception ignored) {
        }

        this.categoryController = categoryController;
        this.subCategory = subCategory;
        mainController = categoryController.getMainController();
        categoryItem = categoryController.getCategoryItem();

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
        int i = 0;
        for (Transaction transaction : mainController.selectedBudgetMonth.getTransactions()){
            System.out.println(transaction.getClass());
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
        categoryItem.removeSubcategory(this.subCategory);
        categoryItem.removeSubcategoryBudget(subCategory);
        categoryController.updateSubCategories();
        mainController.updateLatestTransaction();
        mainController.updateMainView();

    }

    //try and fix this method
    @FXML
    private void getEditSubCategoryWindow(){
        mainController.showEditSubCategoryWindow(this);
    }
}
