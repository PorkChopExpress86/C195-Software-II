package helper;

import Database.DBUser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;

public class Logger {

    public static void logAttempts(String userName, String password) {
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter("login_activity.txt", true));

            boolean login = DBUser.userLogin(userName, password);
            if (login) {

                String log = "Username: " + userName + "\t logged in @: " + LocalDateTime.now() + "\n";
                bw.write(log);
            } else {
                String log = "Username: " + userName + "\t failed loggin in @: " + LocalDateTime.now() + "\n";
                bw.write(log);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
