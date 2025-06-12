package projects.dao;

import projects.exception.DbException;
import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection {
    //public static String ANSI_GREEN = "\u001B[32m";

    private static String url = "jdbc:mysql://localhost:3306/projects_schema";
    private static String host = "root";
    private static String password = "Germanman21!";
    private static String Schema = "projects";

    public static Connection getConnection() {


        try {
            Connection connection = DriverManager.getConnection(url, host, password);
            System.out.println("\nConnection at " + Schema + " Established.\n");
            return connection;

        } catch (Exception e) {
            System.err.println("Failed to Connect");
            throw new DbException(e.getMessage());
        }
    }
}