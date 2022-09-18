package budgetapp.model;

import javafx.scene.image.Image;

abstract public class AbstractCategoryItem {
    private String name;
    private double budget;
    private double spentAmount;
    private Image icon;

    public AbstractCategoryItem(String name, double budget, Image icon) {
        this.budget = budget;
        this.spentAmount = 0;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }
    protected void setBudget(double budget) {
        this.budget = budget;
    }

    public double getSpentAmount() {
        return spentAmount;
    }
    private void setSpentAmount(double spentAmount) {
        this.spentAmount = spentAmount;
    }

    public Image getIcon() {
        return icon;
    }
    public void setIcon(Image icon) {
        this.icon = icon;
    }

    protected void incrementBudget(int amount) {
        setBudget(budget + amount);
    }
    protected void decrementBudget(int amount) {
        setBudget(budget - amount);
    }

    protected void incrementSpentAmount(int amount) {
        setSpentAmount(spentAmount + amount);
    }
    protected void decrementSpentAmount(int amount) {
        setSpentAmount(spentAmount - amount);
    }
}
