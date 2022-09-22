package budgetapp.model;

import javafx.scene.image.Image;


final class User {
    private String firstName;
    private String lastName;
    private Image profilePicture;
    private String password;
    private int totalBudget;
    private int totalExpenses;
    private boolean familyShare;
    private boolean elderlyAdjusted;

    private final ProfileSlot profileSlot;

    private User(ProfileSlot profileSlot) {
        this.profileSlot = profileSlot;
    }


}
