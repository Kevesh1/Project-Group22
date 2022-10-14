package budgetapp.model.categories;

import budgetapp.model.transactions.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CategorySubItemTest {
    private CategorySubItem subCategory;


    @BeforeEach
    void setUp() {
        subCategory = new CategorySubItem(100,"Test");
    }

    @Test
    void addExpense() {
        Expense expense = new Expense(20.0,"Test","01-01-01",Category.Food,subCategory);
        subCategory.addExpense(expense);
        assertEquals(expense, subCategory.getExpenses().get(0));
    }

    @Test
    void getExpenses(){
        Expense expense = new Expense(20.0,"Test","01-01-01",Category.Food,subCategory);
        subCategory.addExpense(expense);
        assertEquals(expense, subCategory.getExpenses().get(0));
    }


}
