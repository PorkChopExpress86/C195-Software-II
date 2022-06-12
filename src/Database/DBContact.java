package Database;

import Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBContact {

    public static ObservableList<Contact> getAllContacts() {
        ObservableList<Contact> contactList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Contact_ID, Contact_Name FROM contacts";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                Contact contact = new Contact(contactId, contactName);
                contactList.add(contact);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactList;
    }

    public static Contact getContactById(int contactId) throws SQLException {
        try {
            String sql = "SELECT Contact_ID, Contact_Name FROM contacts WHERE Contact_ID=?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, contactId);
            ps.execute();
            ResultSet rs = ps.getResultSet();

            rs.next();
            int id = rs.getInt("Contact_ID");
            String name = rs.getString("Contact_Name");
            Contact contact = new Contact(id, name);
            return contact;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
