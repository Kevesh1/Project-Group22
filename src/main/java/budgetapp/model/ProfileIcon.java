package budgetapp.model;

import java.util.ArrayList;
import java.util.List;

public class ProfileIcon {
    private String ProfilePicture;
    //Better having an expandable class than having a String[] with links to pictures
    private String profileGif;
    private boolean locked;

    private static final List<ProfileIcon> allProfileIcons = new ArrayList<>();

    public ProfileIcon(){
        allProfileIcons.add(this);
    }


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

        ProfileIcon profileIcon1 = new ProfileIcon();
        profileIcon1.setProfilePicture("/budgetapp/img/Mario.jpg");

        ProfileIcon profileIcon2 = new ProfileIcon();
        profileIcon2.setProfilePicture("/budgetapp/img/luigi.jpg");

        ProfileIcon profileIcon3 = new ProfileIcon();
        profileIcon3.setProfilePicture("/budgetapp/img/peach.jpg");

        ProfileIcon profileIcon4 = new ProfileIcon();
        profileIcon4.setProfilePicture("/budgetapp/img/donkey.png");

        ProfileIcon profileIcon5 = new ProfileIcon();
        profileIcon5.setProfilePicture("/budgetapp/img/Wario.png");

        ProfileIcon profileIcon6 = new ProfileIcon();
        profileIcon6.setProfilePicture("/budgetapp/img/waluigi.jpg");

        return allProfileIcons;


    }
}
