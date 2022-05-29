package Database;

import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DBAppointments {

    public static ObservableList<Appointment> weeklyAppointments() {
        ObservableList<Appointment> weeklyList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments WHERE WEEK(Start) = WEEK(NOW())";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Setting the variables from the database
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp startDate = rs.getTimestamp("Start");
                Timestamp endDate = rs.getTimestamp("End");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");

                Appointment appointment = new Appointment(appointmentId, title, description, location, type, startDate, endDate, customerId, userId, contactId);
                weeklyList.add(appointment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weeklyList;

    }


    public static ObservableList<Appointment> monthlyAppointments() {
        ObservableList<Appointment> monthlyList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments WHERE MONTH(Start) = MONTH(NOW())";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Setting the variables from the database
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp startDate = rs.getTimestamp("Start");
                Timestamp endDate = rs.getTimestamp("End");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");

                Appointment appointment = new Appointment(appointmentId, title, description, location, type, startDate, endDate, customerId, userId, contactId);
                monthlyList.add(appointment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monthlyList;

    }
}
