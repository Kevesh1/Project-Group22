package budgetapp.model;

import java.util.ArrayList;
import java.util.List;

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

    public static List<ProfileIcon> profileIconList(){
        List<ProfileIcon> allProfileIcons = new ArrayList<>();

        ProfileIcon profileIcon = new ProfileIcon();
        profileIcon.setProfilePicture("/budgetapp/img/Mario.png");
        allProfileIcons.add(profileIcon);

        return allProfileIcons;


    }
}
