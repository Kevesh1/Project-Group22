package budgetapp.controller.categories;

import budgetapp.controller.transactions.ExpenseController;
import budgetapp.controller.MainController;
import budgetapp.model.transactions.Expense;
import budgetapp.model.categories.CategoryItem;
import budgetapp.model.categories.CategorySubItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class CategoryController extends AnchorPane {


    @FXML
    ProgressBar progressBar;

    @FXML
    Label categoryName;

    @FXML
    Label categoryBudget;

    @FXML
    Button editCategory;

    @FXML
    Button removeCategoryButton;

    @FXML
    ImageView categoryImage;

    @FXML
    private Button addButton;

    @FXML
    private AnchorPane CategoryPane;

    @FXML
    Button addNewSubCategoryButton;

    //private void updateProgressBar(){
    //    progressBar.setProgress(this.budget - this.spentAmount);}

    private final MainController mainController;
    private final CategoryItem categoryItem;
    //private ArrayList<CategorySubItem> subCategories = new ArrayList<>();
    private int index;

    public CategoryController(MainController mainController, CategoryItem categoryItem, int i) {
        FXMLLoader root = new FXMLLoader(getClass().getResource("/budgetapp/fxml/category.fxml"));
        root.setRoot(this);
        root.setController(this);
        try {
            root.load();
        } catch (Exception ignored) {
        }
        this.categoryItem = categoryItem;
        this.mainController = mainController;
        this.index = i;
        setLabels();
    }

    public MainController getMainController(){
        return mainController;
    }

    public CategoryItem getCategoryItem(){
        return categoryItem;
    }

    @FXML
    public void initialize() {
        subCategoriesMock();
    }

    public void setLabels() {
        categoryName.setText(categoryItem.getName());
        categoryBudget.setText(categoryItem.getBudget() + " kr");
        progressBar.setProgress(categoryItem.getBudgetSpent() / categoryItem.getBudget());
        categoryImage.setImage(categoryItem.getIcon());
    }

    private void subCategoriesMock(){
        CategorySubItem subCategory1 = new CategorySubItem(20,"Food");
        CategorySubItem subCategory2 = new CategorySubItem(20,"Buzz");
        CategorySubItem subCategory3 = new CategorySubItem(20,"Food");

        categoryItem.addSubCategory(subCategory1);
        categoryItem.addSubCategory(subCategory2);
        categoryItem.addSubCategory(subCategory3);
    }

    @FXML
    public void updateInformation(){
        updateSubCategories();
        showMatchingPurchases();
    }
    public void showMatchingPurchases(){
        mainController.latestPurchases.getChildren().clear();
        for (Expense expense : mainController.selectedBudgetMonth.getExpenses()){
            if (expense.getCategory().equals(categoryItem.getCategory())){
                System.out.println("GOES IN IF STATEMENT");
                ExpenseController expenseController = new ExpenseController(mainController, expense);
                mainController.latestPurchases.getChildren().add(expenseController);
            }
        }
        System.out.println("SHOW PURCHASES");
    }

    public void updateSubCategories(){
        //System.out.println(categoryItem.getSubCategories());
        mainController.updateCategoryList();
        //System.out.println(index);
        for (CategorySubItem subCategory : categoryItem.getSubCategories()) {

            SubCategoryController subCategoryController = new SubCategoryController(this, subCategory);
            mainController.categoriesFlowPane.getChildren().add(index,subCategoryController);
            index += 1;
        }
    }

    @FXML
    private void getAddSubCategoryWindow(){
        mainController.showAddSubCategoryWindow(this);
    }

   /* @FXML
    public void addSubCategory(){
        subCategories.add(new CategorySubItem(Double.parseDouble(
                parentController.newSubCategoryBudget.getText()), parentController.newSubCategoryName.getText()));
    }*/

    @FXML
    public void removeCategoryCheck(){
        if (categoryItem.isBudgetEmpty()){
            confirmRemoveCategory();
        }
        else
            mainController.confirmRemoveCategoryWindow(this);
    }

    public void confirmRemoveCategory(){
        mainController.selectedBudgetMonth.getCategories().remove(categoryItem);
        mainController.updateCategoryList();
    }

    @FXML
    private void getEditCategoryWindow() {
        mainController.showEditCategoryWindow(this);
    }
    /*private void editCategory(){
        boolean edit = true;
        parentController.addNewCategoryButton.setText("Update");
        parentController.categoryComboBox.getSelectionModel().select(categoryItem.getCategory());
        parentController.newCategoryBudget.setText(String.valueOf(categoryItem.getBudget()));
        parentController.addNewCategoryPane.toFront();
    }*/


}
