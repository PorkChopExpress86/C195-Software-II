package helper;

import javafx.scene.control.Alert;

public class AlertError {
    private String alertTitle;

    private String alertText;

    public AlertError(String alertTitle, String alertText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(alertTitle);
        alert.setContentText(alertText);
        alert.showAndWait();
    }

}
