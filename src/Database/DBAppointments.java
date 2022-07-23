package Database;

import model.Appointment;
import model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
                LocalDateTime startDate = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endDate = rs.getTimestamp("End").toLocalDateTime();
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
                LocalDateTime startDate = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endDate = rs.getTimestamp("End").toLocalDateTime();
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

    public static ObservableList<Appointment> getAppointmentsByCustomerId(int customerId) {
        ObservableList<Appointment> list = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID from appointments WHERE Customer_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime startTime = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endTime = rs.getTimestamp("End").toLocalDateTime();
                int custId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                Appointment appointment = new Appointment(appointmentId, title, description, location, type, startTime, endTime, custId, userId, contactId);

                //Add the appointment to the list
                list.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<Appointment> getAllAppointments() {

        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID  FROM appointments";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime startTime = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endTime = rs.getTimestamp("End").toLocalDateTime();
                int custId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                Appointment appointment = new Appointment(appointmentId, title, description, location, type, startTime, endTime, custId, userId, contactId);

                //Add the appointment to the list
                appointmentList.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentList;
    }

    public static boolean updateAppointment(int appointmentId, String title, String description, String
            location, String type, LocalDateTime startDateAndTime, LocalDateTime endDateAndTime, int customerId, int userId,
                                            int contactId) throws SQLException {
        Contact contact = DBContact.getContactById(contactId);
        String sql = "UPDATE appointments SET Title=?, Description=?, Location=? , Type=?, startDateAndTime=?, endDateAndTime=?, Customer_ID=?, User_ID=?, Contact_ID=? WHERE Appointment_ID=?";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(startDateAndTime));
        ps.setTimestamp(6, Timestamp.valueOf(endDateAndTime));
        ps.setInt(7, customerId);
        ps.setInt(8, userId);
        ps.setInt(9, contactId);
        ps.setInt(10, appointmentId);

        try {
            ps.execute();
            if (ps.getUpdateCount() > 0) {
                System.out.println("Rows affected: " + ps.getUpdateCount());
            } else {
                System.out.println("No changes");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
