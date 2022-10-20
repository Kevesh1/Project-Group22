package budgetapp.controller.categories;

import budgetapp.controller.MainController;
import budgetapp.model.categories.CategoryItem;
import budgetapp.model.categories.CategorySubItem;
import dataaccess.mongodb.dao.categories.CategoryDao;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.List;

public class CategoryListController {

    private MainController mainController;

    private ObservableList<CategoryItem> categoryItemList;

    public CategoryItem selectedCategoryItem;

    private CategoryDao categoryDao;

    public CategoryListController(MainController mainController, List<CategoryItem> categoryItems) {
        this.categoryItemList = FXCollections.observableArrayList(categoryItems);
        this.mainController = mainController;
        this.categoryDao = new CategoryDao();
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

    public void confirmRemoveCategory(){
        mainController.selectedBudgetMonth.getCategoryItems().remove(
                categoryDao.deleteCategory(selectedCategoryItem)
        );
        updateCategoryList();
    }

    public void confirmRemoveCategoryWindow(CategoryController categoryController){
        mainController.confirmDeletePane.toFront();
        //this.categoryController = categoryController;
    }

    public void showEditCategoryWindow(CategoryController categoryController) {
        mainController.showEditCategoryWindow(categoryController);
    }

    public void showEditSubCategoryWindow(SubCategoryController subCategoryController) {
        mainController.showEditSubCategoryWindow(subCategoryController);
    }


    ChangeListener<ObservableList<CategoryItem>> categoryItemsChanged = (obs, wasFocused, isFocused) -> {
        System.out.println("OLD");
        wasFocused.forEach(categoryItem -> System.out.println(categoryItem.getCategory()));
        System.out.println("NEW");
        wasFocused.forEach(categoryItem -> System.out.println(categoryItem.getCategory()));
    };

    public void showAddSubCategoryWindow(CategoryController categoryController) {
        mainController.showAddSubCategoryWindow(categoryController);
    }
}
