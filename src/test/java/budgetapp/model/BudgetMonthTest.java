package budgetapp.model;

import budgetapp.model.categories.Category;
import budgetapp.model.categories.CategoryItem;
import budgetapp.model.transactions.Expense;
import budgetapp.model.transactions.Income;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.time.Month;

public class BudgetMonthTest {

    private BudgetMonth budgetMonth;
    /*

    @BeforeEach
    void setUp(){
        budgetMonth = new BudgetMonth(2022, Month.AUGUST);

    }

    @Test
    void getYear(){
        assertEquals(2022, budgetMonth.getYear());
    }

    @Test
    void getMonth() {
        assertEquals(Month.AUGUST, budgetMonth.getMonth());
    }

    @Test
    void getBudgetRemaining() {
        budgetMonth.addTransaction(new Income(200.0,"Test","01-01-01"));
        budgetMonth.addTransaction(new Expense(100.0,"Test","01-01-01", null,null));
        assertEquals(100,budgetMonth.getBudgetRemaining());
    }

    @Test
    void getBudget() {
        budgetMonth.addTransaction(new Income(200.0,"Test","01-01-01"));
        assertEquals(200, budgetMonth.getBudget());
    }


    @Test
    void getBudgetSpent() {
        budgetMonth.addTransaction(new Expense(100.0,"Test","01-01-01", null,null));
        assertEquals(100,budgetMonth.getBudgetSpent());
    }

    @Test
    void getCategories() {
        CategoryItem categoryItem = new CategoryItem(Category.Food);
        budgetMonth.addCategoryItem(categoryItem);
        assertEquals(categoryItem, budgetMonth.getCategories().get(0));
    }

    @Test
    void addCategoryItem() {
        CategoryItem categoryItem = new CategoryItem(Category.Food);
        budgetMonth.addCategoryItem(categoryItem);
        assertEquals(categoryItem, budgetMonth.getCategories().get(0));
    }

    @Test
    void addTransaction() {
        Income income = new Income(200.0, null, null);
        budgetMonth.addTransaction(income);
        assertEquals(income, budgetMonth.getTransactions().get(0));
    }

    @Test
    void removeTransaction() {
        Income income = new Income(200.0, null, null);
        budgetMonth.addTransaction(income);
        budgetMonth.removeTransaction(income);
        assertTrue(budgetMonth.getTransactions().isEmpty());
    }

    @Test
    void getTransactions() {
        Income income = new Income(200.0, null, null);
        budgetMonth.addTransaction(income);
        assertEquals(income, budgetMonth.getTransactions().get(0));
    }

    @Test
    void getExpenses() {
        Expense expense = new Expense(200.0, null, null, null, null);
        budgetMonth.addTransaction(expense);
        assertEquals(expense, budgetMonth.getExpenses().get(0));
    }

     */
}
