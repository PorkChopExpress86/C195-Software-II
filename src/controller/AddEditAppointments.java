package controller;

import Database.DBContact;
import Model.Appointment;
import Model.Contact;
import helper.AlertError;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
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

    @FXML
    private TextField tbStartDate;

    @FXML
    private TextField tbStartTime;

    @FXML
    private TextField tbEndDate;

    @FXML
    private TextField tbEndTime;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Contact> contactList = DBContact.getAllContacts();
        cbContact.setItems(contactList);
        cbContact.setVisibleRowCount(5);
    }

    public void loadAppointmentData(Appointment appointment) throws SQLException {
        tbAppointmentId.setText(String.valueOf(appointment.getAppointmentId()));
        tbTitle.setText(appointment.getTitle());
        tbDescription.setText(appointment.getDescription());
        tbLocation.setText(appointment.getLocation());
        //skipping contact
        tbType.setText(appointment.getType());
        tbCustomerId.setText(String.valueOf(appointment.getCustomerId()));
        tbUserId.setText(String.valueOf(appointment.getUserId()));


        //Get contact data
        Contact contact = DBContact.getContactById(appointment.getContactId());
        cbContact.setValue(contact);

        //Start and end date and times
        tbStartDate.setText(String.valueOf(appointment.getStartDate()));
        tbStartTime.setText(String.valueOf(appointment.getStartTime()));

        tbEndDate.setText(String.valueOf(appointment.getEndDate()));
        tbEndTime.setText(String.valueOf(appointment.getEndTime()));

    }

    public void onActionConfirm(ActionEvent actionEvent) throws IOException {

        try {
            //Check that all fields are filled out

            //Appointment Id
            if (!tbAppointmentId.getText().isEmpty()) {
                int appointmentId = Integer.parseInt(tbAppointmentId.getText().trim());
            } else {
                AlertError alert = new AlertError("Empty Field", "Appointment Id is empty!");
            }

            //Title
            if (!tbTitle.getText().isEmpty()) {
                String title = tbTitle.getText().trim();
            } else {
                AlertError alert = new AlertError("Empty Field", "Title is empty!");
            }

            //Description
            if (!tbDescription.getText().isEmpty()) {
                String description = tbDescription.getText().trim();
            } else {
                AlertError alert = new AlertError("Empty Field", "Description is empty!");
            }

            //Location
            if (!tbLocation.getText().isEmpty()) {
                String location = tbLocation.getText().trim();
            } else {
                AlertError alert = new AlertError("Empty Field", "Location is empty!");
            }

            //Contact Id: check that the contact ID is greater than 0
            if (cbContact.getValue().getContactId() > 0) {
                int contactId = cbContact.getValue().getContactId();
            } else {
                AlertError alert = new AlertError("Empty Field", "Description is empty!");
            }

            //Type
            if (!tbType.getText().isEmpty()) {
                String type = tbType.getText().trim();
            } else {
                AlertError alert = new AlertError("Empty Field", "Type is empty!");
            }

            //Customer ID
            if (!tbCustomerId.getText().isEmpty()) {
                int customerId = Integer.parseInt(tbCustomerId.getText().trim());
            } else {
                AlertError alert = new AlertError("Empty Field", "Customer Id is empty!");
            }

            //User Id
            if (!tbUserId.getText().isEmpty()) {
                int userId = Integer.parseInt(tbUserId.getText().trim());
            } else {
                AlertError alert = new AlertError("Empty Field", "User Id is empty!");
            }

            //Start Date
            String startDate = null;
            if (!tbStartDate.getText().isEmpty()) {
                startDate = tbStartDate.getText().trim();
            } else {
                AlertError alert = new AlertError("Empty Field", "Start date is empty!");
            }

            //Start Time
            String startTime = null;
            if (!tbStartTime.getText().isEmpty()) {
                startTime = tbStartTime.getText().trim();
            } else {
                AlertError alert = new AlertError("Empty Field", "Start time is empty!");
            }

            //End Date
            String endDate = null;
            if (!tbEndDate.getText().isEmpty()) {
                endDate = tbEndDate.getText().trim();
            } else {
                AlertError alert = new AlertError("Empty Field", "End date is empty!");
            }

            //End Time
            String endTime = null;
            if (!tbEndTime.getText().isEmpty()) {
                endTime = tbEndTime.getText().trim();
            } else {
                AlertError alert = new AlertError("Empty Field", "End time is empty!");
            }

            //Start datetime
            Timestamp startDateTime = Timestamp.valueOf((startDate + " " + startTime));
            Timestamp endDateTime = Timestamp.valueOf((endDate + " " + endTime));

            LocalDate localStartDate = LocalDate.parse(startDate);
            LocalTime localStartTime = LocalTime.parse(startTime);
            LocalDate localEndDate = LocalDate.parse(endDate);
            LocalTime localEndTime = LocalTime.parse(endTime);




        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private String testTextFields(TextField textField, String string, String label) {
        if (!textField.getText().isEmpty()) {
            string = textField.getText().trim();
            return string;
        } else {
            AlertError alert = new AlertError("Empty Field", label + " is empty!");
            return null;
        }
    }

    private int testTextFields(TextField textField, int id, String label) {
        if (!textField.getText().isEmpty()) {
            id = Integer.parseInt(textField.getText().trim());
            return id;
        } else {
            AlertError alert = new AlertError("Empty Field", label + " is empty!");
            return -1;
        }
    }

    public void onActionBack(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Appointments.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }


}
