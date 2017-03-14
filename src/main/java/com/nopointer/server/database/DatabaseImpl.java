package com.nopointer.server.database;

import com.google.inject.Inject;
import com.google.inject.name.Named;

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
    public DatabaseImpl(@Named("config.properties") String config,
                        DatabaseAPI databaseAPI) {
        this.databaseAPI = databaseAPI;
        properties = new Properties();
        connection = null;
//      Реализация считывания из фала
        try {
        InputStream input = new FileInputStream(config);

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
    public void connect() throws SQLException {
        if ( !isConnect() ) {
            try {
                String driver = properties.getProperty("db.driver");
                String url = properties.getProperty("db.host");
                String username = properties.getProperty("db.login");
                String password = properties.getProperty("db.password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
//                Не оч корректно!
                databaseAPI.setConnection(connection);
            }
            catch (SQLException | ClassNotFoundException e) {
                throw new SQLException(e.getMessage());
            }
        }
    }

    @Override
    public void disconnect() throws SQLException {
        if ( isConnect() ) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                throw new SQLException(e.getMessage());
            }
        }
    }

    @Override
    public DatabaseAPI getAPI() {
        return databaseAPI;
    }
}
