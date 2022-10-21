package budgetapp.model.transactions;

import budgetapp.model.categories.Category;
import budgetapp.model.categories.CategorySubItem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseTest {

    private Expense expenseT;


    @BeforeEach
    void setUp() {
        expenseT = new Expense(10.0, "L", null, null, null);
    }


    @Test
    void getSubCategory(){
        assertNull(expenseT.getSubCategory());
    }

    @Test
    void getCategory(){
        assertNull(expenseT.getCategory());
    }

    @Test
    void setCategory(){
        expenseT.setCategory(Category.Pet);
        assertEquals(Category.Pet,expenseT.getCategory());

    }



}