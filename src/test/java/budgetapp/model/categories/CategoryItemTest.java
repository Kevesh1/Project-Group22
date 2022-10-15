package budgetapp.model.categories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CategoryItemTest {

    @Mock
    private CategoryItem categoryItem;


    @BeforeEach
    void setUp(){
        categoryItem = new CategoryItem(Category.Food);
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
        assertEquals(0,categoryItem.getBudget());
    }

    @Test
    void setBudget(){
        categoryItem.setBudget(200);
        assertEquals(200, categoryItem.getBudget());
    }

    @Test
    void isBudgetEmpty(){
        assertTrue(categoryItem.isBudgetEmpty());
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
        assertEquals(100,categoryItem.getBudget());
    }

    @Test
    void decrementBudget(){
        categoryItem.decrementBudget(100);
        assertEquals(-100,categoryItem.getBudget());
    }

    @Test
    void getSubCategories() {
        CategorySubItem subCategory = new CategorySubItem(20.0,"Test");
        categoryItem.getSubCategories().add(subCategory);
        assertEquals(subCategory,categoryItem.getSubCategories().get(0));
    }


    @Test
    void addSubCategory() {
        CategorySubItem subCategory = new CategorySubItem(20.0, "Test");
        categoryItem.addSubCategory(subCategory);
        assertEquals(subCategory, categoryItem.getSubCategories().get(0));
    }

    @Test
    void removeSubcategory() {
        CategorySubItem subCategory = new CategorySubItem(20.0, "Test");
        categoryItem.addSubCategory(subCategory);
        categoryItem.removeSubcategory(subCategory);
        assertTrue(categoryItem.getSubCategories().isEmpty());
    }

    @Test
    void addSubcategoryBudget() {
        CategorySubItem subCategory = new CategorySubItem(50.0, "Test");
        CategorySubItem subCategory2 = new CategorySubItem(100.0, "Test");
        categoryItem.addSubCategory(subCategory);
        categoryItem.addSubCategory(subCategory2);

        categoryItem.addSubcategoryBudget(subCategory);
        categoryItem.addSubcategoryBudget();

        assertEquals(150, categoryItem.getBudget());

    }

    @Test
    void removeSubcategoryBudget() {
        CategorySubItem subCategory = new CategorySubItem(50.0, "Test");
        categoryItem.removeSubcategoryBudget(subCategory);

        assertEquals(-50, categoryItem.getBudget());

    }

    @Test
    void getCategory() {

        assertEquals(Category.Food, categoryItem.getCategory());
    }

    @Test
    void setCategory() {
        categoryItem.setCategory(Category.Home);
        assertEquals(Category.Home, categoryItem.getCategory());
    }

    @Test
    void incrementBudgetSpent(){
        categoryItem.incrementBudgetSpent(100);
        assertEquals(100, categoryItem.getBudgetSpent());
    }

    @Test
    void decrementBudgetSpent(){
        categoryItem.decrementBudgetSpent(100);
        assertEquals(-100,categoryItem.getBudgetSpent());

    }
}