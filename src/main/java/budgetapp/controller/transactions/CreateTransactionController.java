package budgetapp.controller.transactions;

import budgetapp.controller.MainController;
import budgetapp.model.BudgetMonth;
import budgetapp.model.categories.Category;
import budgetapp.model.categories.CategoryItem;
import budgetapp.model.categories.CategorySubItem;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.util.List;

public class CreateTransactionController {
    private MainController mainController;

    public CreateTransactionController(MainController mainController) {
        this.mainController = mainController;
    }

    public void initialize() {
        loadExpenseCategoriesComboBox();
        initializeCategoryComboBox();
    }

    private void loadExpenseCategoriesComboBox(){
        mainController.newExpenseDate.setValue(LocalDate.now());
        mainController.newIncomeDate.setValue(LocalDate.now());
        ObservableList<CategoryItem> categories = FXCollections.observableArrayList(mainController.selectedBudgetMonth.getCategoryItems());
        mainController.newExpenseCategoryComboBox.setItems(categories);
        mainController.newExpenseCategoryComboBox.getSelectionModel().selectedItemProperty()
                .addListener(selectedCategoryChanged);
        mainController.newExpenseCategoryComboBox.setConverter(comboBoxCategoryStringConverter);
        mainController.newExpenseCategoryComboBox.getSelectionModel().selectFirst();
    }

    public void loadSubCategoryComboBox() {
        ObservableList<CategorySubItem> subCategories = FXCollections.observableArrayList();
        subCategories.addAll(mainController.newExpenseCategoryComboBox.getSelectionModel().getSelectedItem().getSubCategories());
        mainController.newExpenseSubCategoryComboBox.setItems(subCategories);
        mainController.newExpenseSubCategoryComboBox.setConverter(comboBoxSubCategoryStringConverter);
    }

    private void initializeCategoryComboBox(){
        ObservableList<Category> categories = FXCollections.observableArrayList();
        categories.addAll(List.of(Category.values()));
        mainController.categoryComboBox.setItems(categories);
        mainController.categoryComboBox.getSelectionModel().selectFirst();
    }



    private final StringConverter<CategoryItem> comboBoxCategoryStringConverter = new StringConverter<CategoryItem>() {

        @Override
        public String toString(CategoryItem categoryItem) {
            return categoryItem.getName();
        }

        @Override
        public CategoryItem fromString(String string) {
            return null;
        }

    };

    private final StringConverter<CategorySubItem> comboBoxSubCategoryStringConverter = new StringConverter<CategorySubItem>() {

        @Override
        public String toString(CategorySubItem categorySubItem) {
            return categorySubItem == null ? "" : categorySubItem.getName();
        }

        @Override
        public CategorySubItem fromString(String string) {
            return null;
        }
    };

    ChangeListener<CategoryItem> selectedCategoryChanged = (obs, wasFocused, isFocused) -> {
        if (!mainController.newExpenseCategoryComboBox.getSelectionModel().getSelectedItem().getSubCategories().isEmpty())
        {
                mainController.newExpenseSubCategoryComboBox.setItems(FXCollections.observableArrayList(isFocused.getSubCategories()));
                mainController.newExpenseSubCategoryComboBox.setDisable(false);
                mainController.newExpenseSubCategoryComboBox.getSelectionModel().selectFirst();
        } else {
            mainController.newExpenseSubCategoryComboBox.getSelectionModel().clearSelection();
            mainController.newExpenseSubCategoryComboBox.setDisable(true);
        }
    };



}
