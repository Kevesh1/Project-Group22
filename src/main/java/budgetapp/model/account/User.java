package budgetapp.model.account;

import java.util.ArrayList;
import java.util.List;

public class User implements ILocked{
    private String username;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private String password;
    private String userId;
    private boolean familyShare;
    private boolean elderlyAdjusted;

    private boolean enablePassword;

    private static List<User> allUsers = new ArrayList<>();

    //expand
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        allUsers.add(this);
    }

    public User() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFamilyShare() {
        return familyShare;
    }

    public void setFamilyShare(boolean familyShare) {
        this.familyShare = familyShare;
    }

    public boolean isElderlyAdjusted() {
        return elderlyAdjusted;
    }

    public void setElderlyAdjusted(boolean elderlyAdjusted) {
        this.elderlyAdjusted = elderlyAdjusted;
    }

    public String getUserID() {
        return userId;
    }

    public void setUserID(String userID) {
        this.userId = userID;
    }

    public boolean isEnablePassword() {
        return enablePassword;
    }

    public void setEnablePassword(boolean enablePassword) {
        this.enablePassword = enablePassword;
    }

    public static List<User> userList(){

        User user = new User("Sven Svensson", "omg");
        user.setProfilePicture("/budgetapp/img/BlankProfilePicture.png");
        user.setUserID("1");

        user = new User("Jacob West","Di1");
        user.setProfilePicture("/budgetapp/img/BlankProfilePicture.png");
        user.setUserID("2");

        user = new User("Sigfrid Bort","2d");
        user.setProfilePicture("/budgetapp/img/BlankProfilePicture.png");
        user.setUserID("3");

        return allUsers;

    }


    @Override
    public void lock() {

    }

    @Override
    public void unlock() {

    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }
}
