package com.clothify.pos.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection dbConnection;


    private final Connection connection;

    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/Clothify","root","Moratuwa@123");
    }

    public Connection getConnection() {
        return connection;
    }
    public static DBConnection getInstance() throws SQLException,ClassNotFoundException {
        if (dbConnection == null){
           return dbConnection = new DBConnection();
        }
        return dbConnection;
    }

}
