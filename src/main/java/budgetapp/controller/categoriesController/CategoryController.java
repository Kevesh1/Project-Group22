package budgetapp.controller.categoriesController;

import budgetapp.model.categories.CategoryItem;
import budgetapp.model.categories.CategorySubItem;
import dataaccess.mongodb.dao.categories.CategoryDao;
import dataaccess.mongodb.dao.categories.SubCategoryDao;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.List;

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

    private final SubCategoryDao subCategoryDao;




    public CategoryController(CategoryListController categoryListController, CategoryItem categoryItem) {
        categoryDao = new CategoryDao();
        subCategoryDao = new SubCategoryDao();
        this.categoryItem = categoryItem;
        this.categoryListController = categoryListController;

        //loadListeners();

        FXMLLoader root = new FXMLLoader(getClass().getResource("/budgetapp/fxml/CategoriesView/category.fxml"));
        root.setRoot(this);
        root.setController(this);
        try {
            root.load();
        } catch (Exception ignored) {
        }
    }

    /*private void loadListeners() {
        categoryItem.addListener((ListChangeListener<CategorySubItem>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    subCategoryAdded(change);
                } else if (change.wasRemoved()) {
                    subCategoryRemoved(change);
                } else if (change.wasUpdated()) {
                    System.out.println("CHANGE");
                } else if (change.wasPermutated()) {
                    System.out.println("PERM");
                }

            }
        });
    }*/

    private void subCategoryAdded(ListChangeListener.Change<? extends CategorySubItem> change) {
        try {
            categoryItem.addSubCategory(
                    subCategoryDao.addSubCategory(change.getAddedSubList().get(0), categoryItem.getId()));
            categoryListController.updateCategoryList();
            categoryListController.resetNewCategoryInputs();
        } catch (IllegalArgumentException exception){
            System.out.println("Invalid number");
        }
        categoryListController.updateMainView();

    }

    private void subCategoryRemoved(ListChangeListener.Change<? extends CategorySubItem> change) {;
        categoryItem.removeSubcategory(
                subCategoryDao.deleteSubCategory(change.getRemoved().get(0))
        );
        categoryListController.updateMainView();
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
    public void initialize() {
        setLabels();
    }

    @FXML
    public void categorySelected(){
        categoryListController.CategoryChanged(this);
        if(!categoryItem.getSubCategories().isEmpty()) {
            categoryListController.mainController.pieChartController
                    .updatePieChart(categoryItem.getSubCategories());
        }
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
        /*if (categoryItem.isBudgetEmpty()){
            System.out.println("Empty");
            categoryListController.removeCategory(this);
        } else {
            System.out.println("ELSE");
            categoryListController.confirmRemoveCategoryWindow(this);
        }*/
        categoryListController.removeCategory(categoryItem);
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



    void removeSubCategory(CategorySubItem categorySubItem) {
        categoryItem.getSubCategories().remove(
                subCategoryDao.deleteSubCategory(categorySubItem));
        categoryListController.updateMainView();
    }

    public void updateCategorySubItemList(List<CategorySubItem> b) {
    }
}
