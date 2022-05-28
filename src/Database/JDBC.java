package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//localhost:3306/";
    private static final String dbName = "client_schedule";

    private static final String jdbcURL = protocol + vendorName + ipAddress + dbName;

    private static final String MYSQLJBCDriver = "com.mysql.cj.jdbc.Driver";

    private static final String username = "sqlUser";

    private static final String password = "Passw0rd!";

    private static Connection conn = null;

    /**
     * Method that will start the connection to the database
     *
     * @return Connection object
     */
    public static Connection startConnection() {
        try {
            Class.forName(MYSQLJBCDriver);
            conn = DriverManager.getConnection(jdbcURL, username, password);

            System.out.println("Connection successful");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Close the connection method
     */
    public static void closeConnection() {
        try {
            conn.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection() {
        return conn;
    }

}
