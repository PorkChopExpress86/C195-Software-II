package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class JDBC {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost:3306/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcURL = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String userName = "sqlUser";
    private static final String password = "Passw0rd!";

    public static Connection connection;

    public static void openConnection() {
        try {
            // Locate the driver
            Class.forName(driver);

            // Create connection with the driver and the parameters created above
            connection = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection open successful");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed successful");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
