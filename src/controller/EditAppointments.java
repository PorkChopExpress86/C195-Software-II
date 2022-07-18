package controller;

import Database.DBAppointments;
import Database.DBContact;
import Model.Appointment;
import Model.Contact;
import helper.AlertError;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class EditAppointments implements Initializable {

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
    private ComboBox<String> cbStartHour;

    @FXML
    private ComboBox<String> cbStartMinute;

    @FXML
    private DatePicker meetingDatePicker;

    @FXML
    private ComboBox<String> cbEndHour;

    @FXML
    private ComboBox<String> cbEndMinute;


    ObservableList<String> appointmentHour = FXCollections.observableArrayList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
    ObservableList<String> appointmentMinute = FXCollections.observableArrayList("00", "15", "30", "45");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Contact> contactList = DBContact.getAllContacts();
        cbContact.setItems(contactList);
        cbContact.setVisibleRowCount(5);
    }

    /**
     * Load the appointment that is to be edited
     *
     * @param appointment
     * @throws SQLException
     */
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
        meetingDatePicker.setValue(appointment.getStartDate());

        // Start Hour
        cbStartHour.setItems(appointmentHour);
        cbStartHour.setValue(appointment.getStartHour());

        //Start Minute
        cbStartMinute.setItems(appointmentMinute);
        cbStartMinute.setValue(appointment.getStartMinute());

        //End Hour
        cbEndHour.setItems(appointmentHour);
        cbEndHour.setValue(appointment.getEndHour());

        //End Minute
        cbEndMinute.setItems(appointmentMinute);
        cbEndMinute.setValue(appointment.getEndMinute());

    }

    public void onActionConfirm(ActionEvent actionEvent) throws IOException {

        try {
            //Check that all fields are filled out

            if (!tbAppointmentId.getText().isEmpty() && !tbTitle.getText().isEmpty() && !tbDescription.getText().isEmpty()
                    && cbContact.getValue().getContactId() > 0 && !tbType.getText().isEmpty() && !tbCustomerId.getText().isEmpty()
                    && !tbUserId.getText().isEmpty() && meetingDatePicker.getValue() != null && !cbStartHour.getValue().isBlank()
                    && !cbStartMinute.getValue().isBlank() && !cbEndHour.getValue().isBlank() && !cbEndMinute.getValue().isBlank()) {
                //Update the appointment
                int appointmentId = Integer.parseInt(tbAppointmentId.getText().trim());
                String title = tbTitle.getText().trim();
                String description = tbDescription.getText().trim();
                String location = tbLocation.getText().trim();
                int contactId = cbContact.getValue().getContactId();
                String type = tbType.getText().trim();
                int customerId = Integer.parseInt(tbCustomerId.getText().trim());
                int userId = Integer.parseInt(tbUserId.getText().trim());
                LocalDate meetingDate = meetingDatePicker.getValue();
                String startHour = cbStartHour.getValue();
                String startMinute = cbStartMinute.getValue();
                String endHour = cbEndHour.getValue();
                String endMinute = cbEndMinute.getValue();

                //Convert the startTime to a localTime
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh-mm");
                LocalTime startTime = LocalTime.parse(startHour + "-" + startMinute, timeFormatter);
                //Convert endTime to a localTime
                LocalTime endTime = LocalTime.parse(endHour + "-" + endMinute, timeFormatter);

                LocalDateTime startDateTime = LocalDateTime.of(meetingDate, startTime);
                LocalDateTime endDateTime = LocalDateTime.of(meetingDate, endTime);

                //Check that the time is not outside business hours
                //If it is true then convert the start and end datetime to UTC and update database
                if (AppointmentTimeTest(startDateTime, endDateTime)) {

                } else {

                }

                //Convert to UTC time
                ZoneId zoneId = ZoneId.of(TimeZone.getDefault().getID());

                //ZonedDateTime
//                ZonedDateTime zdtStart = ZonedDateTime.of(localStartDate, localStartTime, zoneId);
//                ZonedDateTime zdtEnd = ZonedDateTime.of(localEndDate, localEndTime, zoneId);


                ObservableList<Appointment> appointmentList = DBAppointments.getAppointmentsByCustomerId(Integer.parseInt(tbCustomerId.getText()));


            } else {
                //Alert that a cell is empty
                AlertError alert = new AlertError("Empty field", "Cannot update appointment with an empry field");
            }


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

    public ZonedDateTime AppointmentZdt2Utc(LocalDateTime dateTime) {
        ZonedDateTime timeZdt = ZonedDateTime.of(dateTime, ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));
        return timeZdt;
    }

    public ZonedDateTime AppointmentUtc2Zdt(LocalDateTime dateTime) {
        ZonedDateTime timeZdt = ZonedDateTime.of(dateTime, ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault());
        return timeZdt;
    }

    public boolean AppointmentTimeTest(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        //Check start time
        ZonedDateTime estStartTime = ZonedDateTime.of(startDateTime, ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New York"));
        LocalTime checkStartTime = estStartTime.toLocalTime();
        int startDOW = estStartTime.toLocalDate().getDayOfWeek().getValue();

        //Check end time
        ZonedDateTime estEndTime = ZonedDateTime.of(endDateTime, ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New York"));
        LocalTime checkEndTime = estStartTime.toLocalTime();
        int endDOW = estStartTime.toLocalDate().getDayOfWeek().getValue();

        //Business days and hours
        LocalTime startBusinessHour = LocalTime.of(8, 0, 0);
        LocalTime endBusinessHour = LocalTime.of(22, 0, 0);
        int startBusinessDOW = DayOfWeek.MONDAY.getValue();
        int endBusinessDOW = DayOfWeek.FRIDAY.getValue();

        //Check that the time is in the
        if (checkStartTime.isBefore(startBusinessHour) ||
                checkStartTime.isAfter(endBusinessHour) ||
                checkEndTime.isBefore(startBusinessHour) ||
                checkEndTime.isAfter(endBusinessHour)) {
            AlertError alert = new AlertError("Appointment Error", "Start or End time is outside of business hours. Please schedule beteen 8:00 and 22:00 EST");
            return false;
        }

        // Check that the day of the week is between Monday and Friday
        if (startDOW < startBusinessDOW || startDOW > endBusinessDOW ||
                endDOW < startBusinessDOW || endDOW > endBusinessDOW) {
            AlertError alert = new AlertError("Appointment Error", "The date is outside of business days. Please schedule between Monday and Friday");
            return false;
        }
        return true;
    }

    public boolean CheckOverlappingAppointments(int customerIdToAdd, LocalDateTime start, LocalDateTime end) throws SQLException {
        ObservableList<Appointment> allAppointments = DBAppointments.getAllAppointments();

        for (Appointment appointment : allAppointments) {
            LocalDateTime checkStartDateTime = appointment.getStartDateAndTime();
            LocalDateTime checkEndDateTime = appointment.getEndDateAndTime();

            //Looking at row 376 in AppointmentForms.java
            int customerIdToCheck = appointment.getCustomerId();
            if (customerIdToAdd == customerIdToCheck &&
                    (start.isAfter(checkStartDateTime) && start.isBefore(checkEndDateTime)) ||
                    (end.isAfter(checkEndDateTime) && end.isBefore(checkEndDateTime))) {
                AlertError alert = new AlertError("Conflicting meeting", "This meeting has a conflict with another meeting. Please change the start time.");
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
