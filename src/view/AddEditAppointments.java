package view;

import Database.DBContact;
import Model.Contact;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEditAppointments implements Initializable {

    Parent scene;
    @FXML
    private ComboBox<Contact> cbContact;

    @FXML
    private TextField tbAppointmentId;

    @FXML
    private TextField tbCustomerId;

    @FXML
    private TextField tbDescription;

    @FXML
    private TextField tbLocation;

    @FXML
    private TextField tbTitle;

    @FXML
    private TextField tbType;

    @FXML
    private TextField tbUserId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Contact> contactList = DBContact.getAllContacts();
        cbContact.setItems(contactList);
        cbContact.setVisibleRowCount(5);
    }

}
