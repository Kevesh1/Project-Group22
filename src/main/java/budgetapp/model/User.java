package budgetapp.model;

import javafx.scene.image.Image;


public class User {
    private String firstName;
    private String lastName;
    private Image profilePicture;
    private String password;
    private int totalBudget;
    private int totalExpenses;
    private boolean familyShare;
    private boolean elderlyAdjusted;
    private final int userID;

    public User(int userID){
        this.userID = userID;
    }

    public void fullName(String firstName, String lastName){
        String fullName = firstName + lastName;
    }

}
