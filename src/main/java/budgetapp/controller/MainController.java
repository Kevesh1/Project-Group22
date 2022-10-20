package budgetapp.controller;

import budgetapp.controller.categories.CategoryController;
import budgetapp.controller.categories.CategoryListController;
import budgetapp.controller.categories.SubCategoryController;
import budgetapp.controller.graphs.PieChartController;
import budgetapp.controller.graphs.StackedBarChartController;
import budgetapp.controller.transactions.TransactionController;
import budgetapp.model.BudgetMonth;
import budgetapp.model.account.User;
import budgetapp.model.categories.Category;
import budgetapp.model.categories.CategoryItem;
import budgetapp.model.categories.CategorySubItem;
import budgetapp.model.transactions.Expense;
import budgetapp.model.transactions.Income;
import budgetapp.model.transactions.Transaction;

import dataaccess.mongodb.dao.BudgetMonthDao;
import dataaccess.mongodb.dao.account.AccountDao;
import dataaccess.mongodb.dao.categories.CategoryDao;
import dataaccess.mongodb.dao.categories.SubCategoryDao;
import dataaccess.mongodb.dao.transactions.ExpenseDao;
import dataaccess.mongodb.dao.transactions.TransactionDao;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;


public class MainController extends AnchorPane{

    //<editor-fold desc="FXML Declarations">
    @FXML
    Button confirmButton;
    @FXML
    AnchorPane mainView;
    @FXML
    public FlowPane categoriesFlowPane;
    @FXML
    public PieChart pieChart;
    @FXML
    Button previousMonthButton;
    @FXML
    Button nextMonthButton;
    @FXML
    ComboBox<BudgetMonth> yearMonthComboBox;
    @FXML
    public StackedBarChart<String, Number> stackedBarChart;
    @FXML
    Label budgetLabel;
    @FXML
    Label budgetSpentLabel;
    @FXML
    ImageView profileImage;
    @FXML
    Label budgetRemainingLabel;
    @FXML
    public FlowPane latestPurchases;
    @FXML
    public
    AnchorPane confirmDeletePane;

    @FXML
    ComboBox<Category> categoryComboBox;
    @FXML
    ComboBox categoryComboBox2;

    @FXML
    Button addNewCategoryButton;
    @FXML
    AnchorPane addNewCategoryPane;
    @FXML
    Button addCategoryButton;
    @FXML
    Label newCategoryNameLabel;
    @FXML
    Label newCategoryBudgetLabel;
    @FXML
    Button updateCategoryButton;
    @FXML
    Button updateSubCategoryButton;

    @FXML
    AnchorPane addNewSubCategoryPane;
    @FXML
    TextField newSubCategoryName;
    @FXML
    TextField newSubCategoryBudget;
    @FXML
    Button justabutton;

    @FXML
    Button addNewSubCategoryButton;

    @FXML
    AnchorPane iEWindow;
    @FXML
    ComboBox<CategoryItem> newExpenseCategoryComboBox;
    @FXML
    TextField newExpenseAmount;
    @FXML
    DatePicker newExpenseDate;
    @FXML
    TextArea newExpenseNote;
    @FXML
    ComboBox newIncomeCategoryComboBox;
    @FXML
    TextField newIncomeAmount;
    @FXML
    DatePicker newIncomeDate;
    @FXML
    TextArea newIncomeNote;
    @FXML
    ComboBox<CategorySubItem> newExpenseSubCategoryComboBox;

    @FXML
    public BorderPane borderPane;

    @FXML
    public GridPane gridPane;
    //</editor-fold>

    @FXML
    private void openIE(){
        iEWindow.toFront();
    }

    @FXML
    private void addExpense(){
        Double cost = Double.valueOf(newExpenseAmount.getText());
        String note = newExpenseNote.getText();
        LocalDate tempDate = newExpenseDate.getValue();
        Date date = Date.valueOf(tempDate);
        Category category = Category.valueOf(newExpenseCategoryComboBox.getSelectionModel().getSelectedItem().getName());

        CategorySubItem subCategory = newExpenseSubCategoryComboBox.getSelectionModel().getSelectedItem();
        Expense expense = new Expense(cost, note, date, category, subCategory);
        selectedBudgetMonth.addTransaction(expenseDao.addExpense(expense, selectedBudgetMonth.getId()));
        subCategory.addExpense(expense);

        CategorySubItem subItem = newExpenseSubCategoryComboBox.getSelectionModel().getSelectedItem();
        subItem.incrementBudgetSpent(Integer.parseInt(newExpenseAmount.getText()));

        transactionController.updateTransactions();
        categoryListController.updateCategoryList();
        showMainView();
    }

    @FXML
    private void addIncome(){
        Double cost = Double.valueOf(newIncomeAmount.getText());
        String note = newIncomeNote.getText();
        Date date = Date.valueOf(newExpenseDate.getValue());
        Income income = new Income(cost, note, date);
        transactionController.addTransaction(income);
        transactionController.updateTransactions();
        updateMainView();
        showMainView();
    }

    @FXML
    public void closeNewCategoryWindow(){
        mainView.toFront();
        resetNewCategoryInputs();
    }
    @FXML
    private void onClickPreviousMonth() {
        yearMonthComboBox.getSelectionModel().selectPrevious();
    }

    @FXML
    private void onClickNextMonth() throws IOException {
        yearMonthComboBox.getSelectionModel().selectNext();
    }

    @FXML
    private void showAddCategoryWindow(){
        addNewCategoryPane.toFront();
        updateCategoryButton.setVisible(false);
        addNewCategoryButton.setVisible(true);
    }

    @FXML
    private void showMainView(){
        mainView.toFront();
        updateMainView();
    }


    public void showEditSubCategoryWindow(SubCategoryController subCategoryController){
        this.subCategoryController = subCategoryController;
        CategorySubItem subCategory = subCategoryController.getSubCategory();
        addNewSubCategoryPane.toFront();
        updateSubCategoryButton.setVisible(true);
        addNewSubCategoryButton.setVisible(false);
        newSubCategoryName.setText(subCategory.getName());
        newSubCategoryBudget.setText(String.valueOf(subCategory.getBudget()));
    }

    @FXML
    private void updateSubCategory() {
        // TODO MOVE TO SUBCATEGORY
        CategorySubItem subCategory = subCategoryController.getSubCategory();
        subCategoryController.getCategoryItem().removeSubcategoryBudget(subCategory);
        subCategory.setName(newSubCategoryName.getText());
        subCategory.setBudget(Double.parseDouble(newSubCategoryBudget.getText()));

        subCategoryController.getCategoryItem().addSubcategoryBudget(subCategory);
        categoryListController.updateCategoryList();
        showMainView();
    }

    public void showEditCategoryWindow(CategoryController categoryController){
        this.categoryController = categoryController;
        addNewCategoryPane.toFront();
        updateCategoryButton.setVisible(true);
        addNewCategoryButton.setVisible(false);
        categoryComboBox.getSelectionModel().select(categoryController.getCategoryItem().getCategory());
        //newCategoryBudget.setText(String.valueOf(categoryController.getCategoryItem().getBudget()));
    }

    @FXML
    private void updateCategory(){
        categoryController.getCategoryItem().setCategory(Category.valueOf(categoryComboBox.getSelectionModel().getSelectedItem().toString()));
        categoryDao.updateCategory(categoryController.getCategoryItem());
        categoryListController.updateCategoryList();
        showMainView();
    }

    @FXML
    private void addNewCategory(){
        // TODO MOVE TO CATEGORY
        Category category = Category.valueOf(categoryComboBox.getSelectionModel().getSelectedItem().toString());
        CategoryItem categoryItem = categoryDao.addCategory(new CategoryItem(category), selectedBudgetMonth.getId());
        selectedBudgetMonth.addCategoryItem(categoryItem);
        categoryListController.updateCategoryList();
        showMainView();
        resetNewCategoryInputs();
    }

    @FXML
    public void showAddSubCategoryWindow(CategoryController categoryController){
        addNewSubCategoryPane.toFront();
        updateSubCategoryButton.setVisible(false);
        addNewSubCategoryButton.setVisible(true);
        this.categoryController = categoryController;
    }

    @FXML
    private void addNewSubCategory() {
        String name = newSubCategoryName.getText();
        double budget = Double.parseDouble(newSubCategoryBudget.getText());
        CategorySubItem categorySubItem = new CategorySubItem(budget,name);
        System.out.println("NEW SUBCATEGORY");
        System.out.println(categoryController.getCategoryItem().getName());
        categoryController.getCategoryItem().addSubCategory(
                subCategoryDao.addSubCategory(categorySubItem, categoryController.getCategoryItem().getId())
        );
        categoryController.getCategoryItem().addSubcategoryBudget();
        //categoryController.subCategories.add(categorySubItem);

        //categoryListController.updateSubCategories();
        System.out.println("UPDATESUBCATEGORIES");
        showMainView();
        resetNewCategoryInputs();
    }

    @FXML
    public void confirmRemoveCategoryWindow(CategoryController categoryController){
        confirmDeletePane.toFront();
        this.categoryController = categoryController;
    }

    @FXML
    public void removeCategory(){
        categoryListController.confirmRemoveCategory();
        showMainView();
    }

    @FXML
    private void LoadSubCategoryComboBox() {
        ObservableList<CategorySubItem> subCategories = FXCollections.observableArrayList();
        subCategories.addAll(newExpenseCategoryComboBox.getSelectionModel().getSelectedItem().getSubCategories());

        newExpenseSubCategoryComboBox.setItems(subCategories);
        newExpenseSubCategoryComboBox.setConverter(comboBoxSubCategoryStringConverter);
    }

    private void resetNewCategoryInputs(){
        categoryComboBox.getSelectionModel().selectFirst();
        newSubCategoryName.setText("");
        newSubCategoryBudget.setText("");
    }


    private User user;

    private CategoryController categoryController;

    private SubCategoryController subCategoryController;

    private StackedBarChartController stackedBarChartController;

    public BudgetMonth selectedBudgetMonth;

    private ObservableList<BudgetMonth> budgetMonths;

    private BudgetMonthDao budgetMonthDao;

    private CategoryDao categoryDao;

    private SubCategoryDao subCategoryDao;

    private TransactionDao transactionDao;

    private ExpenseDao expenseDao;

    private PieChartController pieChartController;

    private CategoryListController categoryListController;

    private budgetapp.controller.transactions.TransactionController transactionController;


    public MainController(User user) {
        this.user = user;
        this.budgetMonths = FXCollections.observableArrayList();

        loadDaos();

        budgetMonths.addAll(loadBudgetMonths());
        selectedBudgetMonth = budgetMonths.get(selectBudgetMonthIndex());

        loadControllers();


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/budgetapp/fxml/MainView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        } catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }
    }

    private void loadDaos() {
        budgetMonthDao = new BudgetMonthDao();
        transactionDao = new TransactionDao();
        categoryDao = new CategoryDao();
        subCategoryDao = new SubCategoryDao();
        expenseDao = new ExpenseDao();
    }

    private void loadControllers() {
        stackedBarChartController = new StackedBarChartController(this, budgetMonths);
        pieChartController = new PieChartController(this);
        categoryListController = new CategoryListController(this, selectedBudgetMonth.getCategoryItems());
        transactionController = new TransactionController(this);
    }

    private List<BudgetMonth> loadBudgetMonths() {
        Optional<List<BudgetMonth>> dbBudgetMonths = budgetMonthDao.getAllBudgetMonthsByUserId(user.getId());
        List<BudgetMonth> loadedBudgetMonths;

        if (dbBudgetMonths.isPresent()) {
            loadedBudgetMonths = dbBudgetMonths.get();
            loadedBudgetMonths.forEach(budgetMonth -> budgetMonth
                    .setCategoryItems(
                            loadCategoryItems(budgetMonth.getId()))
                    .forEach(categoryItem -> categoryItem
                            .setSubCategories(
                                    loadSubCategoryItems(
                                            categoryItem.getId()))));
            loadedBudgetMonths.forEach(budgetMonth -> budgetMonth
                    .setTransactions(
                            loadTransactions(budgetMonth.getId())
                    ));

        } else {
            loadedBudgetMonths = budgetMonthDao
                    .initNewBudgetMonths(createDefaultBudgetMonths(), user.getUserID());
            loadedBudgetMonths.forEach(budgetMonth -> budgetMonth
                    .setCategoryItems(categoryDao.initCategoryItems(createDefaultCategoryItems(), budgetMonth.getId())));
        }
        loadedBudgetMonths.forEach(budgetMonth -> budgetMonth.getCategoryItems().forEach(categoryItem -> categoryItem.getSubCategories().forEach(categorySubItem -> System.out.printf("SUB %s ", categorySubItem.getName()))));
        return loadedBudgetMonths;
    }

    private List<CategoryItem> loadCategoryItems(String budgetMonthId) {
        return categoryDao.getAllCategoriesByBudgetMonth(budgetMonthId);
    }

    private List<CategorySubItem> loadSubCategoryItems(String categoryId) {
        return subCategoryDao.getAllSubCategoriesByCategory(categoryId);
    }

    private List<Transaction> loadTransactions(String budgetMonthId) {
        return transactionDao.getAllTransactionsByBudgetMonth(budgetMonthId);
    }

    private int selectBudgetMonthIndex() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);

        return ((currentYear)-(currentYear-1))*12 + currentMonth;
    }

    private List<BudgetMonth> createDefaultBudgetMonths() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        List<BudgetMonth> budgetMonths = new ArrayList<>();

        for (int i = year -1; i < year +2; i++){
            for (Month month : Month.values()){
                BudgetMonth budgetMonth = new BudgetMonth(i, month);
                budgetMonths.add(budgetMonth);
            }
        }
        return budgetMonths;
    }

    private List<CategoryItem> createDefaultCategoryItems() {
        List<CategoryItem> categoryItems = new ArrayList<>();

        for (Category category : Category.values()){
            if (category != Category.Hobbies) {
                categoryItems.add(new CategoryItem(category));
            } else {
                break;
            }
        }
        return categoryItems;
    }

    @FXML
    public void initialize() {
        initializeComboBox();
        initializeBudgetMonths();
        updateMainView();
        updateLists();
        initializeExpenseView();
        stackedBarChartController.initialize();
    }

    private void initializeComboBox() {
        yearMonthComboBox.getSelectionModel().selectedItemProperty()
                .addListener(selectedBudgetMonthChanged);
        yearMonthComboBox.setCellFactory(comboBoxCellFactory);
        yearMonthComboBox.setConverter(comboBoxStringConverter);
        yearMonthComboBox.setItems(FXCollections.observableArrayList(budgetMonths));
    }

    public void initializeBudgetMonths() {
        yearMonthComboBox.getSelectionModel().select(selectBudgetMonthIndex());
        selectedBudgetMonth = yearMonthComboBox.getSelectionModel().getSelectedItem();
    }

    private void initializeExpenseView() {
        initializeCategoryComboBox();
        LoadExpenseCategoriesComboBox();
    }

    private void updateLists(){
        categoryListController.updateCategoryList();
        transactionController.updateTransactions();
    }
    public void updateMainView() {
        budgetLabel.setText(String.valueOf(selectedBudgetMonth.getBudget()));
        budgetSpentLabel.setText(String.valueOf(selectedBudgetMonth.getBudgetSpent()));
        budgetRemainingLabel.setText(String.valueOf(selectedBudgetMonth.getBudgetRemaining()));
    }

    private void initializeCategoryComboBox(){
        ObservableList<Category> categories = FXCollections.observableArrayList();
        categories.addAll(List.of(Category.values()));
        categoryComboBox.setItems(categories);
        categoryComboBox.getSelectionModel().selectFirst();

    }

    private void LoadExpenseCategoriesComboBox(){
        newExpenseDate.setValue(LocalDate.now());
        newIncomeDate.setValue(LocalDate.now());
        ObservableList<CategoryItem> categories = FXCollections.observableArrayList(
                selectedBudgetMonth.getCategoryItems());
        newExpenseCategoryComboBox.setItems(categories);
        newExpenseCategoryComboBox.setConverter(comboBoxCategoryStringConverter);
        newExpenseCategoryComboBox.getSelectionModel().selectFirst();

    }
    private Callback<ListView<BudgetMonth>, ListCell<BudgetMonth>> comboBoxCellFactory = new Callback<ListView<BudgetMonth>, ListCell<BudgetMonth>>() {
        @Override
        public ListCell<BudgetMonth> call(ListView<BudgetMonth> budgetMonthListView) {
            return new ListCell<BudgetMonth>() {
                @Override
                protected void updateItem(BudgetMonth budgetMonth, boolean empty) {
                    super.updateItem(budgetMonth, empty);
                    if (budgetMonth == null || empty) {
                        setGraphic(null);
                    } else {
                        setText(yearMonthComboBox.getConverter().toString(budgetMonth));
                    }
                }
            };
        }
    };

    private StringConverter<BudgetMonth> comboBoxStringConverter = new StringConverter<BudgetMonth>() {

        @Override
        public String toString(BudgetMonth budgetMonth) {
            return String.format("%s %d", budgetMonth.getMonth().toString(), budgetMonth.getYear());
        }

        @Override
        public BudgetMonth fromString(String string) {
            return null;
        }
    };

    private StringConverter<CategoryItem> comboBoxCategoryStringConverter = new StringConverter<CategoryItem>() {

        @Override
        public String toString(CategoryItem categoryItem) {
            return categoryItem.getName();
        }

        @Override
        public CategoryItem fromString(String string) {
            return null;
        }

    };

    private StringConverter<CategorySubItem> comboBoxSubCategoryStringConverter = new StringConverter<CategorySubItem>() {

        @Override
        public String toString(CategorySubItem categorySubItem) {
            return categorySubItem.getName();
        }

        @Override
        public CategorySubItem fromString(String string) {
            return null;
        }
    };

    ChangeListener<BudgetMonth> selectedBudgetMonthChanged = (obs, wasFocused, isFocused) -> {
        selectedBudgetMonth = isFocused;
        System.out.println("selected");
        System.out.println(selectedBudgetMonth.getMonth());
        pieChartController.updatePieChart(selectedBudgetMonth.getCategoryItems());
        transactionController.updateTransactions();
        categoryListController.updateCategoryList();
    };
}
