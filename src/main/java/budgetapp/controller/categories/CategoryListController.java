package budgetapp.controller.categories;

import budgetapp.controller.MainController;
import budgetapp.model.categories.Category;
import budgetapp.model.categories.CategoryItem;
import budgetapp.model.categories.CategorySubItem;
import dataaccess.mongodb.dao.categories.CategoryDao;
import dataaccess.mongodb.dao.categories.SubCategoryDao;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;

import java.util.List;

public class CategoryListController {

    private final MainController mainController;

    private CategoryController categoryController;

    private SubCategoryController subCategoryController;

    private ObservableList<CategoryItem> categoryItemList;

    CategoryItem selectedCategoryItem;

    private final CategoryDao categoryDao;

    private final SubCategoryDao subCategoryDao;

    public CategoryListController(MainController mainController, List<CategoryItem> categoryItems) {
        this.categoryItemList = FXCollections.observableArrayList(categoryItems);
        this.mainController = mainController;
        this.categoryDao = new CategoryDao();
        this.subCategoryDao = new SubCategoryDao();

        addListeners();
    }

    private void addListeners() {
        categoryItemList.addListener((ListChangeListener<CategoryItem>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    categoryItemAdded(change);
                } else if (change.wasRemoved()) {
                    categoryItemRemoved(change);
                } else if (change.wasUpdated()) {
                    System.out.println("update");
                } else if (change.wasPermutated()) {
                    System.out.println("PERM");
                }
            }
        });
    }

    private void categoryItemAdded(ListChangeListener.Change<? extends CategoryItem> change) {
        mainController.selectedBudgetMonth.getCategoryItems()
                .add(categoryDao
                        .addCategory(change.getAddedSubList().get(0), mainController.selectedBudgetMonth.getId()));
        updateCategoryList();
        mainController.updateMainView();
    }

    private void categoryItemRemoved(ListChangeListener.Change<? extends CategoryItem> change) {
        mainController.selectedBudgetMonth.getCategoryItems()
                .remove(categoryDao.deleteCategory(change.getRemoved().get(0)));
        updateCategoryList();
        mainController.updateMainView();
    }

    /*public void updateCategoryList() {
        categoriesFlowPane.getChildren().clear();
        int i = 0;
        for (CategoryItem categoryItem : selectedBudgetMonth.getCategoryItems()) {
            i++;
            CategoryController categoryController = new CategoryController(this, categoryItem, i);
            categoriesFlowPane.getChildren().add(categoryController);
            i++;
        }
    }*/

    public void updateCategoryList() {
        mainController.categoriesFlowPane.getChildren().clear();
        for (CategoryItem categoryItem : mainController.selectedBudgetMonth.getCategoryItems()) {
            CategoryController categoryController = new CategoryController(this, categoryItem);
            mainController.categoriesFlowPane.getChildren().add(categoryController);
            if (categoryItem.equals(selectedCategoryItem)) {
                for (CategorySubItem categorySubItem : categoryItem.getSubCategories()) {
                    mainController.categoriesFlowPane.getChildren().add(new SubCategoryController(this, categoryController, categorySubItem));
                }
            }
        }
    }

    /*public void updateSubCategories(){

        mainController.updateCategoryList();
        int i = index;

        for (CategorySubItem subCategory : categoryItem.getSubCategories()) {
            i += 1;
            SubCategoryController subCategoryController = new SubCategoryController(this, subCategory);
            mainController.categoriesFlowPane.getChildren().add(i,subCategoryController);
        }
    }*/

    /*public void updateSubCategories() {

    }*/

    void confirmRemoveCategoryWindow(CategoryController categoryController){
        this.categoryController = categoryController;
        mainController.confirmDeletePane.toFront();
    }

    void showEditCategoryWindow(CategoryController categoryController) {
        this.categoryController = categoryController;
        mainController.addNewCategoryPane.toFront();
        mainController.updateCategoryButton.setVisible(true);
        mainController.addNewCategoryButton.setVisible(false);
        mainController.categoryComboBox.getSelectionModel().select(categoryController.getCategoryItem().getCategory());
        //mainController.newCategoryBudget.setText(String.valueOf(categoryController.getCategoryItem().getBudget()));
    }

    public void showAddSubCategoryWindow(CategoryController categoryController) {
        this.categoryController = categoryController;
        mainController.addNewSubCategoryPane.toFront();
        mainController.updateSubCategoryButton.setVisible(false);
        mainController.addNewSubCategoryButton.setVisible(true);
    }

    public void loadSubCategoryComboBox() {
        ObservableList<CategorySubItem> subCategories = FXCollections.observableArrayList();
        subCategories.addAll(mainController.newExpenseCategoryComboBox.getSelectionModel().getSelectedItem().getSubCategories());
        mainController.newExpenseSubCategoryComboBox.setItems(subCategories);
        mainController.newExpenseSubCategoryComboBox.setConverter(comboBoxSubCategoryStringConverter);
    }

    void resetNewCategoryInputs(){
        mainController.categoryComboBox.getSelectionModel().selectFirst();
        mainController.newSubCategoryName.setText("");
        mainController.newSubCategoryBudget.setText("");
    }



    public void showEditSubCategoryWindow(SubCategoryController subCategoryController){
        this.subCategoryController = subCategoryController;
        CategorySubItem subCategory = subCategoryController.getSubCategory();
        mainController.addNewSubCategoryPane.toFront();
        mainController.updateSubCategoryButton.setVisible(true);
        mainController.addNewSubCategoryButton.setVisible(false);
        mainController.newSubCategoryName.setText(subCategory.getName());
        mainController.newSubCategoryBudget.setText(String.valueOf(subCategory.getBudget()));
    }

    public void updateSubCategory() {
        CategorySubItem subCategory = subCategoryController.getSubCategory();
        subCategoryController.getCategoryItem().decrementBudget(subCategory.getBudget());
        System.out.println("BEFORE TRY");
        System.out.println(mainController.newSubCategoryBudget.getText());

        try{
            if (Double.parseDouble(mainController.newSubCategoryBudget.getText()) < 0)
                throw new IllegalArgumentException();
            subCategory.setName(mainController.newSubCategoryName.getText());
            subCategory.setBudget(Double.parseDouble(mainController.newSubCategoryBudget.getText()));

        } catch (IllegalArgumentException exception){
            System.out.println("Not a valid number!");
        }
        subCategoryController.getCategoryItem().incrementBudget(subCategory.getBudget());
        mainController.showMainView();
        System.out.println("upd. sub");
        updateMainView();
    }



    public void showAddCategoryWindow() {
        mainController.addNewCategoryPane.toFront();
        mainController.updateCategoryButton.setVisible(false);
        mainController.addNewCategoryButton.setVisible(true);
    }

    public void addNewCategory() {
        Category category = mainController.categoryComboBox.getSelectionModel().getSelectedItem();
        CategoryItem categoryItem = new CategoryItem(category);
        categoryItemList.add(categoryItem);
    }

    public void removeCategory(CategoryController categoryController){
        categoryItemList.remove(categoryController.categoryItem);
    }

    public void updateCategory() {
        categoryController.getCategoryItem().setCategory(mainController.categoryComboBox.getSelectionModel().getSelectedItem());
        categoryDao.updateCategory(categoryController.getCategoryItem());
        mainController.showMainView();
        System.out.println("upd. cat");
        updateMainView();
    }

    private final StringConverter<CategorySubItem> comboBoxSubCategoryStringConverter = new StringConverter<CategorySubItem>() {

        @Override
        public String toString(CategorySubItem categorySubItem) {
            return categorySubItem.getName();
        }

        @Override
        public CategorySubItem fromString(String string) {
            return null;
        }
    };


    public void addNewSubCategory(CategorySubItem categorySubItem) {
        categoryController.addNewSubCategory(categorySubItem);
    }

    void updateMainView() {
        mainController.updateMainView();
    }
}
