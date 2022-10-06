package budgetapp.model;

public class ProfileIcon {
    private String ProfilePicture;
    //Better having an expandable class than having a String[] with links to pictures
    private String profileGif;

    public String getProfilePicture() {
        return ProfilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        ProfilePicture = profilePicture;
    }

    public String getProfileGif() {
        return profileGif;
    }

    public void setProfileGif(String profileGif) {
        this.profileGif = profileGif;
    }
}
