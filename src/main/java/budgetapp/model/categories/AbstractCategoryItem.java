package budgetapp.model.categories;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;
 public class AbstractCategoryItem {

    private String id;
    private String name;
    private double budget;
    private double budgetSpent;


    public AbstractCategoryItem(String name, double budget) {
        this.name = name;
        this.budget = budget;
        this.budgetSpent = 0;
    }

    public AbstractCategoryItem() {

    }

     /**
      * this method gets name
      * @return name
      */
    public String getName() {
        return name;
    }

     /**
      * this method sets name
      * @param name
      */
    public void setName(String name) {
        if (name != null)
            this.name = name;
        else
            throw new IllegalArgumentException();
    }

     /**
      * this method gets budget
      * @return budget
      */
    public double getBudget() {
        return budget;
    }

     /**
      * this method sets budget
      * @param budget
      */
    public void setBudget(double budget) {

        this.budget = budget;
    }

     /**
      * this method checks if budget is empty
      * @return boolean
      */
    public boolean isBudgetEmpty(){
        return getBudget() == 0;
    }

     /**
      * this method gets budgetSpent
      * @return budgetSpent
      */

    public double getBudgetSpent() {
        return budgetSpent;
    }

     /**
      * this method sets budgetSpent
      * @param budgetSpent
      */
    public void setBudgetSpent(double budgetSpent) {
        this.budgetSpent = budgetSpent;
    }

     /**
      * this method increments budget
      * @param amount
      */

    public void incrementBudget(double amount) {
        setBudget(budget + amount);
    }

     /**
      * this method decrements budget
      * @param amount
      */

    public void decrementBudget(double amount) {
        setBudget(budget - amount);

    }

     /**
      * this method increments budgetSpent
      * @param amount
      */

    public void incrementBudgetSpent(double amount) {
        setBudgetSpent(budgetSpent + amount);
    }

     /**
      * this method decrements budgetSpent
      * @param amount
      */
    public void decrementBudgetSpent(double amount) {
        setBudgetSpent(budgetSpent - amount);
    }

     /**
      * this method gets id
      * @return id
      */

    public String getId() {
        return id;
    }

     /**
      * this method sets id
      * @param id
      */

    public void setId(String id) {
        this.id = id;
    }
}
