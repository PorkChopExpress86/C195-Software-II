package controller;

import Database.DBUser;
import Model.User;
import helper.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML
    private Button btnCancel;

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblPassword;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblUsername;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private Label regionCode;

    @FXML
    private TextField tbUsername;

    private DBUser dbUser;

    interface getTimeZoneLamdba {
        String getTimeZone(ZoneId zoneId);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getTimeZoneLamdba myLambda = (ZoneId zoneId) -> zoneId.toString();
        regionCode.setText(myLambda.getTimeZone(ZoneId.systemDefault()));

        ResourceBundle rb = ResourceBundle.getBundle("properties/Lang", Locale.getDefault());

        if (Locale.getDefault().getLanguage().equals("fr")) {
            lblTitle.setText(rb.getString("title"));
            lblUsername.setText(rb.getString("username"));
            lblPassword.setText(rb.getString("password"));
            btnLogin.setText(rb.getString("login"));
            btnCancel.setText(rb.getString("cancel"));
        }

    }

    @FXML
    void onActionCancel(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onActionLogin(ActionEvent event) throws IOException {
        if (tbUsername.getText().isEmpty()) {
            User.noUsername();
            return;
        }

        if (pfPassword.getText().isEmpty()) {
            User.noPassword();
            return;
        }

        String userName = tbUsername.getText();
        String password = pfPassword.getText();

        boolean login = DBUser.userLogin(userName, password);

        if (!login) {
            Logger.logAttempts(userName, password);
            User.incorrectCredentials();
        } else {
            Logger.logAttempts(userName, password);

            // TODO add check to see if there is an appointment in the next 15 minutes, then display it as an alarm
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Appointments.fxml")));
            stage.setScene(new Scene((Parent) scene));
            stage.show();
        }

    }

}
