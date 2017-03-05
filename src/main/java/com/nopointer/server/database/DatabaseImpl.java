package com.nopointer.server.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseImpl implements Database {
    private static DatabaseImpl dbIsntance;
    private static Connection connection;
    private static Properties properties;

//    Будет в файле!
    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://94.142.139.84/PseudoGit";
    private static final String USERNAME = "petrov";
    private static final String PASSWORD = "petrov2016";

    public static DatabaseImpl getInstance(){
        if(dbIsntance==null) {
            dbIsntance= new DatabaseImpl();
        }
        return dbIsntance;
    }

    private DatabaseImpl() {
        properties = new Properties();
//      Реализация считывания из фала (ПОЗЖЕ)
        /*
        try {
        InputStream input = new FileInputStream("config.properties");

        // Загрузка конфигурационного файла
         properties.load(input);

        System.out.println(properties.getProperty("db.host"));
        System.out.println(properties.getProperty("db.login"));
        System.out.println(properties.getProperty("db.password"));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        */
        properties.setProperty("db.driver", DATABASE_DRIVER);
        properties.setProperty("db.host", DATABASE_URL);
        properties.setProperty("db.login", USERNAME);
        properties.setProperty("db.password", PASSWORD);
    }

    public void connect() {
        if (connection == null) {
            try {
                String driver = properties.getProperty("db.driver");
                String url = properties.getProperty("db.host");
                String username = properties.getProperty("db.login");
                String password = properties.getProperty("db.password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
            }
            catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isConnect() {
        return connection != null;
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public DatabaseAPI getAPI() {
        return null;
    }
}
