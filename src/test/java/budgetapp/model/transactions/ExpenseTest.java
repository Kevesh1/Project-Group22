package budgetapp.model.transactions;

import budgetapp.model.categories.Category;
import budgetapp.model.categories.CategorySubItem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseTest {

    private Expense expenseT;


    @BeforeEach
    void setUp() {
        expenseT = new Expense(10.0, "L", new Date(System.currentTimeMillis()), null, null);
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

    @Test
    void setSum(){
        expenseT.setSum(20);
        assertEquals(20, expenseT.getSum());
    }

    @Test
    void getDate(){
        assertEquals(new Date(System.currentTimeMillis()).toLocalDate().atStartOfDay(),expenseT.getDate().toLocalDate().atStartOfDay());
    }

    @Test
    void setDate(){
        assertEquals(new Date(System.currentTimeMillis()).toLocalDate().atStartOfDay(), expenseT.getDate().toLocalDate().atStartOfDay());
    }

    @Test
    void getAnnotation(){
        assertEquals("L",expenseT.getAnnotation());
    }

    @Test
    void setAnnotation(){
        expenseT.setAnnotation("A");
        assertEquals("A", expenseT.getAnnotation());
    }

    @Test
    void getId(){
        expenseT.setId("123");
        assertEquals("123", expenseT.getId());
    }

    @Test
    void setId(){
        expenseT.setId("123");
        assertEquals("123", expenseT.getId());
    }



}