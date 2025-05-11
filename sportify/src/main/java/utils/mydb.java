package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class mydb {
    private String url = "jdbc:mysql://localhost:3306/sportify";
    private String user = "root";
    private String password = "";
    private Connection conn;
private static mydb instance;

    public static mydb getInstance() {
        if (instance == null) {
            instance = new mydb();
        }
        return instance;
    }

    public Connection getConn() {
        return conn;
    }

    public mydb() {
        try {
            this.conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}