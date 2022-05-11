package main;

import Database.FruitsQuery;
import Database.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) throws SQLException {
//        DBConnection.startConnection();
//        launch(args);
//        DBConnection.closeConnection();

        // Test connection to the database
        JDBC.openConnection();

        // Try to insert a fruit
        int rowsAffected = FruitsQuery.insert("Cherries", 1);
        if (rowsAffected > 0) {
            System.out.println("Insert Successful!");
        }
        JDBC.closeConnection();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Login.fxml")));
        primaryStage.setTitle("First View");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

}
