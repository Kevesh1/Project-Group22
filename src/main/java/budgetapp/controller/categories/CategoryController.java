package budgetapp.controller.categories;

import budgetapp.model.categories.CategoryItem;
import dataaccess.mongodb.dao.categories.CategoryDao;
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

    private final CategoryListController categoryListController;

    final CategoryItem categoryItem;
    private final CategoryDao categoryDao;

    //private ArrayList<CategorySubItem> subCategories = new ArrayList<>();

    public CategoryController(CategoryListController categoryListController, CategoryItem categoryItem) {

        categoryDao = new CategoryDao();
        this.categoryItem = categoryItem;
        this.categoryListController = categoryListController;

        FXMLLoader root = new FXMLLoader(getClass().getResource("/budgetapp/fxml/category.fxml"));
        root.setRoot(this);
        root.setController(this);
        try {
            root.load();
        } catch (Exception ignored) {
        }

        setLabels();
    }

    public CategoryItem getCategoryItem(){
        return categoryItem;
    }


    public void setLabels() {
        categoryName.setText(categoryItem.getName());
        categoryBudget.setText(categoryItem.getBudget() + " kr");
        progressBar.setProgress(categoryItem.getBudgetSpent() / categoryItem.getBudget());
        categoryImage.setImage(categoryItem.applyIcon());
    }

    @FXML
    public void categorySelected(){
        categoryListController.selectedCategoryItem = this.categoryItem;
        categoryListController.updateCategoryList();
        //showMatchingPurchases();
    }

    /*public void showMatchingPurchases(){
        mainController.latestPurchases.getChildren().clear();
        for (Expense expense : mainController.selectedBudgetMonth.getExpenses()){
            if (expense.getCategory().equals(categoryItem.getCategory())){
                System.out.println("GOES IN IF STATEMENT");
                ExpenseController expenseController = new ExpenseController(mainController, expense);
                mainController.latestPurchases.getChildren().add(expenseController);

            }
        }
    }*/



    @FXML
    private void showAddSubCategoryWindow(){
        categoryListController.showAddSubCategoryWindow(this);
    }

    /*@FXML
    public void addSubCategory(){
        subCategories.add(new CategorySubItem(Double.parseDouble(
                parentController.newSubCategoryBudget.getText()), parentController.newSubCategoryName.getText()));
    }*/

    @FXML
    public void removeCategoryCheck(){
        System.out.println(categoryItem.getName());
        System.out.println(categoryItem.getId());
        /*if (categoryItem.isBudgetEmpty()){
            System.out.println("Empty");
            categoryListController.removeCategory(this);
        } else {
            System.out.println("ELSE");
            categoryListController.confirmRemoveCategoryWindow(this);
        }*/
        categoryListController.removeCategory(this);
    }

    /*public void confirmRemoveCategory(){
        mai.selectedBudgetMonth.getCategoryItems().remove(
                categoryDao.deleteCategory(categoryItem)
        );
        mainController.updateCategoryList();
    }*/

    @FXML
    private void getEditCategoryWindow() {
        categoryListController.showEditCategoryWindow(this);
    }
    /*private void editCategory(){
        boolean edit = true;
        parentController.addNewCategoryButton.setText("Update");
        parentController.categoryComboBox.getSelectionModel().select(categoryItem.getCategory());
        parentController.newCategoryBudget.setText(String.valueOf(categoryItem.getBudget()));
        parentController.addNewCategoryPane.toFront();
    }*/


}
