package main;

import Database.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) throws SQLException {
        JDBC.startConnection();
        launch(args);
        JDBC.closeConnection();
    }


        @Override
        public void start (Stage primaryStage) throws IOException {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Login.fxml")));
            primaryStage.setTitle("First View");
            primaryStage.setScene(new Scene(root, 363, 260));
            primaryStage.show();
        }

    }
