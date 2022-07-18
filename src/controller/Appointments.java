package controller;

import Database.DBAppointments;
import Model.Appointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class Appointments implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnReports;

    @FXML
    private TableColumn<Appointment, Integer> mAppointmentId;

    @FXML
    private TableColumn<Appointment, Integer> mContact;

    @FXML
    private TableColumn<Appointment, Integer> mCustomerId;

    @FXML
    private TableColumn<Appointment, String> mDescription;

    @FXML
    private TableColumn<Appointment, Timestamp> mEnd;

    @FXML
    private TableColumn<Appointment, String> mLocation;

    @FXML
    private TableColumn<Appointment, Timestamp> mStart;

    @FXML
    private TableColumn<Appointment, String> mTitle;

    @FXML
    private TableColumn<Appointment, String> mType;

    @FXML
    private TableColumn<Appointment, Integer> mUserId;

    @FXML
    private TableView<Appointment> monthlyTableView;

    @FXML
    private Tab tableViewWeekly;

    @FXML
    private TableColumn<Appointment, Integer> wAppointmentId;

    @FXML
    private TableColumn<Appointment, Integer> wContact;

    @FXML
    private TableColumn<Appointment, Integer> wCustomerId;

    @FXML
    private TableColumn<Appointment, String> wDescription;

    @FXML
    private TableColumn<Appointment, Timestamp> wEnd;

    @FXML
    private TableColumn<Appointment, String> wLocation;

    @FXML
    private TableColumn<Appointment, Timestamp> wStart;

    @FXML
    private TableColumn<Appointment, String> wTitle;

    @FXML
    private TableColumn<Appointment, String> wType;

    @FXML
    private TableColumn<Appointment, Integer> wUserId;

    @FXML
    private TableView<Appointment> weeklyTableView;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @Override
    public void initialize(URL url, ResourceBundle rs) {

//        Weekly table
        weeklyTableView.setItems(DBAppointments.weeklyAppointments());
        wAppointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        wTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        wDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        wLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        wType.setCellValueFactory(new PropertyValueFactory<>("type"));
        wStart.setCellValueFactory(new PropertyValueFactory<>("startDateAndTime"));
        wEnd.setCellValueFactory(new PropertyValueFactory<>("endDateAndTime"));
        wCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        wUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        wContact.setCellValueFactory(new PropertyValueFactory<>("contactId"));

//        Monthly Table
        monthlyTableView.setItems(DBAppointments.monthlyAppointments());
        mAppointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        mTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        mDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        mLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        mType.setCellValueFactory(new PropertyValueFactory<>("type"));
        mStart.setCellValueFactory(new PropertyValueFactory<>("startDateAndTime"));
        mEnd.setCellValueFactory(new PropertyValueFactory<>("endDateAndTime"));
        mCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        mUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        mContact.setCellValueFactory(new PropertyValueFactory<>("contactId"));

    }

    public void onActionEditAppointment(ActionEvent actionEvent) throws IOException, SQLException {

        // Weekly
        if (weeklyTableView.getSelectionModel().getSelectedItem() != null) {
            EditTable(weeklyTableView, actionEvent);
        }

        //Monthly
        else if (monthlyTableView.getSelectionModel().getSelectedItem() != null) {
            EditTable(monthlyTableView, actionEvent);
        }
    }

    private void EditTable(TableView<Appointment> tv, ActionEvent actionEvent) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/EditAppointments.fxml"));
        loader.load();

        EditAppointments AddEditController = loader.getController();
        AddEditController.loadAppointmentData(tv.getSelectionModel().getSelectedItem());

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionAddAppointment(ActionEvent event) {

    }

    @FXML
    void onActionDeleteAppointment(ActionEvent event) {

    }

    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onActionGoToCustomers(ActionEvent event) {

    }

    @FXML
    void onActionGoToReports(ActionEvent event) {

    }


}
