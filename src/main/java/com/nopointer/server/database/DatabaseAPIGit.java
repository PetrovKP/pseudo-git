package com.nopointer.server.database;

import com.nopointer.server.entity.Commit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DatabaseAPIGit implements DatabaseAPI {
    private Connection connection;
    private PreparedStatement preparedStatement;

    DatabaseAPIGit(Connection connection) {
        this.connection = connection;
        preparedStatement = null;
    }

    private boolean isExistLogin(String login) {
        boolean result = true;
        String sql = "SELECT COUNT(*) AS rowcount FROM users WHERE login = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt("rowcount");
            result = count > 0;

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void registerUser(String login, String password) {
        if ( !isExistLogin(login) ) {
            String sql = "INSERT INTO users (login, password) VALUES (?, ?)";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);

                preparedStatement.executeUpdate();

                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean login(String login, String password) {
        boolean result = true;
        String sql = "SELECT COUNT(*) AS rowcount FROM users WHERE login = ? AND password = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt("rowcount");
            result = count > 0;

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void createFile(String title, String text) {

    }

    @Override
    public String getTitle(int idFile) {
        return null;
    }

    @Override
    public List<Integer> getAllFilesId() {
        return null;
    }

    @Override
    public String getFileStatus() {
        return null;
    }

    @Override
    public void changeFileStatus() {

    }

    @Override
    public String getActualText(int idFile) {
        return null;
    }

    @Override
    public Commit getCommitByDate(int idFile, String date) {
        return null;
    }

    @Override
    public List<Integer> getAllCommitsId(int idFile) {
        return null;
    }

    @Override
    public void addCommit(int idFile, String login, String text) {

    }
}
