package Model;

import helper.AlertError;
import javafx.scene.control.Alert;

import java.util.Locale;

public class User {

    private int userId;

    private String userName;

    private static String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * Creates an error box if the username is blank
     */
    public static void noUsername() {
        if (Locale.getDefault().getLanguage().equals("fr")) {
            AlertError alert = new AlertError("(fr)No Username", "(fr)Your username is empty, please enter a username and try again");
        } else {
            AlertError alert = new AlertError("No Username", "Your username is empty, please enter a username and try again");
        }
    }

    /**
     * Creates an error box if the password is blank
     */
    public static void noPassword() {
        if (Locale.getDefault().getLanguage().equals("fr")) {
            AlertError alert = new AlertError("(fr)No Password",
                    "(fr)Your password is empty, please enter a password and try again");
        } else {
            AlertError alert = new AlertError("No Password",
                    "Your password is empty, please enter a password and try again");
        }
    }

    /**
     * Creates and error box of the username or password do not match the database.
     * Alert is returned to the french or english
     */
    public static void incorrectCredentials() {
        if (Locale.getDefault().getLanguage().equals("fr")) {
            AlertError alert = new AlertError("(fr)Incorrect username or Password",
                    "You have entered incorrect credentials. Please try again!");
        } else {
            AlertError alert = new AlertError("Incorrect username of password",
                    "You have entered incorrect login credentials. Please try again.");
        }
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }
}
