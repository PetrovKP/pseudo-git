package com.nopointer.server.database;

import com.google.inject.Inject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseImpl implements Database {
    private Properties properties;
    private Connection connection;
    private DatabaseAPI databaseAPI;

    @Inject
    public DatabaseImpl() {

        properties = new Properties();
        connection = null;
//      Реализация считывания из фала
        try {
        InputStream input = new FileInputStream("src/main/resources/config.properties");

//       Загрузка конфигурационного файла
         properties.load(input);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean isConnect() {
        return connection != null;
    }

    @Override
    public Connection connect() {
        if ( !isConnect() ) {
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
        return connection;
    }

    @Override
    public void disconnect() {
        if ( isConnect() ) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public DatabaseAPI getAPI() {
        databaseAPI = new DatabaseAPIGit(connection);

        return databaseAPI;
    }
}
