package budgetapp.controller.transactions;

import budgetapp.controller.MainController;
import budgetapp.model.categories.Category;
import budgetapp.model.categories.CategoryItem;
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
        mainController.newExpenseCategoryComboBox.setConverter(comboBoxCategoryStringConverter);
        mainController.newExpenseCategoryComboBox.getSelectionModel().selectFirst();

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
}
