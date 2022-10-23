package budgetapp.controller;

import budgetapp.controller.categories.CategoryController;
import budgetapp.controller.categories.CategoryListController;
import budgetapp.controller.graphs.PieChartController;
import budgetapp.controller.graphs.StackedBarChartController;
import budgetapp.controller.transactions.CreateTransactionController;
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
import dataaccess.mongodb.dao.IBudgetMonthDao;
import dataaccess.mongodb.dao.categories.CategoryDao;
import dataaccess.mongodb.dao.categories.ICategoryDao;
import dataaccess.mongodb.dao.categories.ISubCategoryDao;
import dataaccess.mongodb.dao.categories.SubCategoryDao;
import dataaccess.mongodb.dao.transactions.ExpenseDao;
import dataaccess.mongodb.dao.transactions.ITransactionDao;
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
import org.w3c.dom.events.Event;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
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
    public
    ComboBox<Category> categoryComboBox;
    @FXML
    ComboBox categoryComboBox2;

    @FXML
    public
    Button addNewCategoryButton;
    @FXML
    public
    AnchorPane addNewCategoryPane;
    @FXML
    Button addCategoryButton;
    @FXML
    Label newCategoryNameLabel;
    @FXML
    Label newCategoryBudgetLabel;
    @FXML
    public
    Button updateCategoryButton;
    @FXML
    public
    Button updateSubCategoryButton;

    @FXML
    public
    AnchorPane addNewSubCategoryPane;
    @FXML
    public
    TextField newSubCategoryName;
    @FXML
    public
    TextField newSubCategoryBudget;
    @FXML
    Button justabutton;

    @FXML
    public
    Button addNewSubCategoryButton;

    @FXML
    AnchorPane iEWindow;
    @FXML
    public
    ComboBox<CategoryItem> newExpenseCategoryComboBox;
    @FXML
    public
    TextField newExpenseAmount;
    @FXML
    public
    DatePicker newExpenseDate;
    @FXML
    public
    TextArea newExpenseNote;
    @FXML
    ComboBox newIncomeCategoryComboBox;
    @FXML
    public
    TextField newIncomeAmount;
    @FXML
    public
    DatePicker newIncomeDate;
    @FXML
    public
    TextArea newIncomeNote;
    @FXML
    public
    ComboBox<CategorySubItem> newExpenseSubCategoryComboBox;

    @FXML
    Label usernameLabel;

    //</editor-fold>

    @FXML
    private void openIE(){
        iEWindow.toFront();
    }

    @FXML
    private void addExpense(){
        transactionController.addExpense();
    }

    @FXML
    private void addIncome(){
        transactionController.addIncome();
    }

    @FXML
    public void closeNewCategoryWindow(){
        showMainView();
    }
    @FXML
    private void onClickPreviousMonth() {
        yearMonthComboBox.getSelectionModel().selectPrevious();
    }

    @FXML
    private void onClickNextMonth() {
        yearMonthComboBox.getSelectionModel().selectNext();
    }

    @FXML
    private void showAddCategoryWindow(){
        categoryListController.showAddCategoryWindow();
    }

    @FXML
    public void showMainView(){
        mainView.toFront();
        updateMainView();
    }

    @FXML
    private void updateSubCategory() {
        categoryListController.updateSubCategory();
    }

    @FXML
    private void updateCategory(){
        categoryListController.editCategory();
    }
    @FXML
    private void addNewCategory(){
        categoryListController.addNewCategory();
    }

    public void showAddSubCategoryWindow(CategoryController categoryController){
    }

    @FXML
    private void addNewSubCategory() {
        categoryListController.addNewSubCategory();
    }

    @FXML
    private void mainScreenClicked () {
        pieChartController.updatePieChart(selectedBudgetMonth.getCategoryItems());
        // transactionController.updateTransactions();
    }

    /*@FXML
    public void confirmRemoveCategoryWindow(CategoryController categoryController){
        System.out.println("CONFIRM");
        confirmDeletePane.toFront();
    }*/

    @FXML
    public void removeCategory(){
        /*categoryListController.removeCategory();
        showMainView();*/
    }

    @FXML
    private void loadSubCategoryComboBox() {
        categoryListController.loadSubCategoryComboBox();
    }


    private User user;

    private StackedBarChartController stackedBarChartController;

    public BudgetMonth selectedBudgetMonth;

    private ObservableList<BudgetMonth> budgetMonths;

    private IBudgetMonthDao budgetMonthDao;

    private ICategoryDao categoryDao;

    private ISubCategoryDao subCategoryDao;

    private ITransactionDao transactionDao;

    public PieChartController pieChartController;

    private CategoryListController categoryListController;

    private TransactionController transactionController;

    private CreateTransactionController createTransactionController;



    public MainController(User user) {
        this.user = user;
        this.budgetMonths = FXCollections.observableArrayList();

        loadDaos();

        budgetMonths.addAll(loadBudgetMonths());
        selectedBudgetMonth = budgetMonths.get(selectBudgetMonthIndex());
        selectedBudgetMonth.calculateBudget();


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
        categoryDao = new CategoryDao();
        subCategoryDao = new SubCategoryDao();
        transactionDao = new TransactionDao();
    }

    private void loadControllers() {
        stackedBarChartController = new StackedBarChartController(this, budgetMonths);
        pieChartController = new PieChartController(this);
        categoryListController = new CategoryListController(this, selectedBudgetMonth.getCategoryItems());
        transactionController = new TransactionController(this);
        createTransactionController = new CreateTransactionController(this);
    }

    private List<BudgetMonth> loadBudgetMonths() {
        Optional<List<BudgetMonth>> dbBudgetMonths = budgetMonthDao.getAllBudgetMonthsByUserId(user.getId());
        List<BudgetMonth> loadedBudgetMonths;

        if (dbBudgetMonths.isPresent()) {
            loadedBudgetMonths = dbBudgetMonths.get();
            loadedBudgetMonths.forEach(budgetMonth -> budgetMonth
                    .setCategoryItems(loadCategoryItems(budgetMonth.getId()))
                    .forEach(categoryItem -> categoryItem
                            .setSubCategories(loadSubCategoryItems(
                                    categoryItem.getId()))));
            loadedBudgetMonths.forEach(budgetMonth -> budgetMonth
                    .setTransactions(
                            loadTransactions(budgetMonth.getId())
                    ));

        } else {
            loadedBudgetMonths = budgetMonthDao
                    .initNewBudgetMonths(createDefaultBudgetMonths(), user.getId());
            loadedBudgetMonths.forEach(budgetMonth -> budgetMonth
                    .setCategoryItems(categoryDao.initCategoryItems(createDefaultCategoryItems(), budgetMonth.getId())));
        }
        return loadedBudgetMonths;
    }

    private List<CategoryItem> loadCategoryItems(String budgetMonthId) {
        return categoryDao.getAllCategoriesByBudgetMonth(budgetMonthId);
    }

    private List<CategorySubItem> loadSubCategoryItems(String categoryId) {
        return subCategoryDao.getAllSubCategoriesByCategory(categoryId);
    }

    public List<Transaction> loadTransactions(String budgetMonthId) {
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
        usernameLabel.setText(user.getUsername());
        initializeYearMonthComboBox();
        createTransactionController.initialize();
        initializeCharts();
        updateMainView();
    }

    private void initializeCharts() {
        pieChartController.initialize();
        stackedBarChartController.initialize();
    }

    private void initializeYearMonthComboBox() {
        yearMonthComboBox.getSelectionModel().selectedItemProperty()
                .addListener(selectedBudgetMonthChanged);
        yearMonthComboBox.setCellFactory(comboBoxCellFactory);
        yearMonthComboBox.setConverter(comboBoxStringConverter);
        yearMonthComboBox.setItems(FXCollections.observableArrayList(budgetMonths));

        initializeBudgetMonths();
    }

    private void initializeBudgetMonths() {
        yearMonthComboBox.getSelectionModel().select(selectBudgetMonthIndex());
        selectedBudgetMonth = yearMonthComboBox.getSelectionModel().getSelectedItem();
    }

    private void updateBudgetLabels() {
        budgetLabel.setText(String.valueOf(selectedBudgetMonth.getBudget()));
        budgetSpentLabel.setText(String.valueOf(selectedBudgetMonth.getBudgetSpent()));
        budgetRemainingLabel.setText(String.valueOf(selectedBudgetMonth.getBudgetRemaining()));
    }

    public void updateMainView() {
        updateBudgetLabels();
        pieChartController.updatePieChart(selectedBudgetMonth.getCategoryItems());
        stackedBarChartController.updateBarChart();
        transactionController.updateTransactions();
        categoryListController.updateCategoryList();
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

    private ChangeListener<BudgetMonth> selectedBudgetMonthChanged = (obs, wasFocused, isFocused) -> {
        if (wasFocused != null) {
            selectedBudgetMonth = isFocused;
            selectedBudgetMonth.calculateBudget();
            updateMainView();
        }

    };
}
