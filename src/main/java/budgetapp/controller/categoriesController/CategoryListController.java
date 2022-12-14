package budgetapp.controller.categoriesController;

import budgetapp.controller.MainController;
import budgetapp.model.categories.Category;
import budgetapp.model.categories.CategoryItem;
import budgetapp.model.categories.CategorySubItem;
import dataaccess.mongodb.dao.categories.CategoryDao;
import dataaccess.mongodb.dao.categories.SubCategoryDao;
import javafx.collections.ListChangeListener;

import java.util.List;

/**
 * @author Johannes
 * @author Keivan
 */
public class CategoryListController {

    final MainController mainController;

    private CategoryController categoryController;

    private SubCategoryController subCategoryController;

    //ObservableList<CategoryItem> categoryItemList;

    CategoryItem selectedCategoryItem;

    private final CategoryDao categoryDao;

    private final SubCategoryDao subCategoryDao;

    public CategoryListController(MainController mainController, List<CategoryItem> categoryItems) {
        this.mainController = mainController;

        this.categoryDao = new CategoryDao();
        this.subCategoryDao = new SubCategoryDao();

        //addListeners();
    }

    void CategoryChanged(CategoryController categoryController) {
        this.categoryController = categoryController;
        this.selectedCategoryItem = categoryController.categoryItem;
        updateCategoryList();
    }

    /*private void addListeners() {
        categoryItemList.addListener((ListChangeListener<CategoryItem>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    categoryItemAdded(change);
                } else if (change.wasRemoved()) {
                    System.out.println("REMOVED");
                    categoryItemRemoved(change);
                } else if (change.wasUpdated()) {
                    System.out.println("update");
                } else if (change.wasPermutated()) {
                    System.out.println("PERM");
                }
            }
        });
    }*/

    private void categoryItemAdded(ListChangeListener.Change<? extends CategoryItem> change) {
        mainController.selectedBudgetMonth.getCategoryItems()
                .add(categoryDao
                        .addCategory(change.getAddedSubList().get(0), mainController.selectedBudgetMonth.getId()));
        updateCategoryList();
        mainController.updateMainView();
    }

    private void categoryItemRemoved(ListChangeListener.Change<? extends CategoryItem> change) {
        mainController.selectedBudgetMonth.getCategoryItems()
                .remove(categoryDao
                        .deleteCategory(change.getRemoved().get(0)));
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

    void resetNewCategoryInputs(){
        mainController.categoryComboBox.getSelectionModel().selectFirst();
        mainController.newSubCategoryName.setText("");
        mainController.newSubCategoryBudget.setText("");
    }



    public void showEditSubCategoryWindow(SubCategoryController subCategoryController){
        this.subCategoryController = subCategoryController;
        selectedCategoryItem = subCategoryController.getCategoryItem();
        CategorySubItem subCategory = subCategoryController.getSubCategory();

        mainController.addNewSubCategoryPane.toFront();
        mainController.updateSubCategoryButton.setVisible(true);
        mainController.addNewSubCategoryButton.setVisible(false);
        mainController.newSubCategoryName.setText(subCategory.getName());
        mainController.newSubCategoryBudget.setText(String.valueOf(subCategory.getBudget()));
    }

    public void updateSubCategory() {
        CategorySubItem subCategory = subCategoryController.getSubCategory();
        selectedCategoryItem.getSubCategories().remove(subCategory);

        double amount = Double.parseDouble(mainController.newSubCategoryBudget.getText());
        subCategory.setName(mainController.newSubCategoryName.getText());
        subCategory.setBudget(Double.parseDouble(mainController.newSubCategoryBudget.getText()));
        selectedCategoryItem.getSubCategories().add(subCategory);
        /*try{
            if (Double.parseDouble(mainController.newSubCategoryBudget.getText()) < 0)
                throw new IllegalArgumentException();
            subCategory.setName(mainController.newSubCategoryName.getText());
            subCategory.setBudget(Double.parseDouble(mainController.newSubCategoryBudget.getText()));

        } catch (IllegalArgumentException exception){
            System.out.println("Not a valid number!");
        }*/

        subCategoryDao.updateSubCategory(subCategory);
        mainController.selectedBudgetMonth.updateCategoryListItem(selectedCategoryItem);
        resetNewCategoryInputs();
        mainController.showMainView();
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
        mainController.selectedBudgetMonth.addCategoryItem(categoryItem);
        updateMainView();
        mainController.showMainView();
    }

    /*void addNewSubCategory(CategorySubItem categorySubItem) {
        System.out.println("ADD");
        categoryItem.getSubCategories()
                .add(subCategoryDao.addSubCategory(categorySubItem, categoryItem.getId()));
        categoryItem = categoryListController.updateCategory(categoryItem);
        categoryListController.updateMainView();
    }*/

    public void removeCategory(CategoryItem categoryItem){
        mainController.selectedBudgetMonth.removeCategoryItem(categoryItem);
    }

    public void editCategory() {
        categoryController.getCategoryItem().setCategory(mainController.categoryComboBox.getSelectionModel().getSelectedItem());
        categoryController.getCategoryItem().setName(mainController.categoryComboBox.getSelectionModel().getSelectedItem().toString());
        categoryDao.updateCategory(categoryController.getCategoryItem());
        mainController.showMainView();
        updateMainView();
    }

    void updateCategory(CategoryItem categoryItem) {
        mainController.selectedBudgetMonth.updateCategoryListItem(categoryItem);
        updateMainView();
    }

    private CategorySubItem createCategorySubItem() {
        String name = mainController.newSubCategoryName.getText();
        double budget = Double.parseDouble(mainController.newSubCategoryBudget.getText());
        return new CategorySubItem(budget, name);
    }

    public void addNewSubCategory() {
        try {
            CategorySubItem categorySubItem = createCategorySubItem();
            CategoryItem categoryItem = categoryController.categoryItem;
            categoryItem.addSubCategory(subCategoryDao
                    .addSubCategory(categorySubItem, categoryItem.getId()));

            mainController.selectedBudgetMonth.updateCategoryListItem(categoryItem);
            resetNewCategoryInputs();
            updateMainView();
            mainController.showMainView();
        } catch (NumberFormatException exception){
            System.out.println("Not a valid number");
        }
    }

    void updateMainView() {
        mainController.updateMainView();
    }

    public void removeSubCategory(CategorySubItem categorySubItem) {

        categoryController.categoryItem.removeSubcategory(subCategoryDao
                .deleteSubCategory(categorySubItem));

        mainController.selectedBudgetMonth.updateCategoryListItem(categoryController.categoryItem);
        mainController.showMainView();
        updateMainView();
    }
}
