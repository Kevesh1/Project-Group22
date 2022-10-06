package budgetapp.model.categories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryItemTest {
    private CategoryItem categoryItem;


    @BeforeEach
    void setUp(){
        categoryItem = new CategoryItem(100, Category.Food);
    }



    @Test
    void getName(){
        assertEquals("Food",categoryItem.getName());
    }

    @Test
    void setName(){
        categoryItem.setName("Savings");
        assertEquals("Savings", categoryItem.getName());
    }

    @Test
    void getIcon() {
        assertNotNull(categoryItem.getIcon());
    }

    @Test
    void getBudget() {
        assertEquals(100,categoryItem.getBudget());
    }

    @Test
    void setBudget(){
        categoryItem.setBudget(200);
        assertEquals(200, categoryItem.getBudget());
    }

    @Test
    void isBudgetEmpty(){
        assertFalse(categoryItem.isBudgetEmpty());
    }

    @Test
    void getBudgetSpent(){
        assertEquals(0,categoryItem.getBudgetSpent());
    }

    @Test
    void setBudgetSpent(){
        categoryItem.setBudgetSpent(100);
        assertEquals(100,categoryItem.getBudgetSpent());
    }

    @Test
    void incrementBudget(){
        categoryItem.incrementBudget(100);
        assertEquals(200,categoryItem.getBudget());
    }

    @Test
    void decrementBudget(){
        categoryItem.decrementBudget(100);
        assertEquals(0,categoryItem.getBudget());
    }

    @Test
    void getSubCategories() {
    }


    @Test
    void addSubCategory() {
    }

    @Test
    void removeSubcategory() {

    }

    @Test
    void addSubcategoryBudget() {
    }

    @Test
    void removeSubcategoryBudget() {
    }

    @Test
    void getCategory() {
    }

    @Test
    void setCategory() {
    }
}