package com.nopointer.server.database;

import com.nopointer.server.entity.Commit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAPIGit implements DatabaseAPI {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public DatabaseAPIGit() {
        connection = null;
        preparedStatement = null;
    }

    private boolean isExistLogin(String login) {
        boolean result = true;
        String sql = "SELECT COUNT(*) AS rowcount FROM Users WHERE login = ?";
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

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean registerUser(String login, String password) {
        if ( !isExistLogin(login) ) {
            String sql = "INSERT INTO Users (login, password) VALUES (?, ?)";
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
        return true;
    }

    @Override
    public boolean deleteUser(String login) {
        if ( isExistLogin(login) ) {
            String sql = "DELETE FROM Users WHERE login =?";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, login);

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
        String sql = "SELECT COUNT(*) AS rowcount FROM Users WHERE login = ? AND password = ?";
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
    public boolean createFile(String login, String title, List<String> text) {
        String sql = "INSERT INTO Files (title) VALUES (?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
//                preparedStatement.setString(1, login);
//                preparedStatement.setString(2, password);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Integer getCommitsCount(String login, int idFile) {
        return null;
    }

    @Override
    public String getTitle(String login, int idFile) {
        String result = null;
        String sql = "SELECT title FROM Files WHERE idFiles = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idFile);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                result = resultSet.getString("title");

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Integer> getAllFilesId(String login) {
        List<Integer> result = new ArrayList<>();
        String sql = "SELECT idFiles FROM Files";
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                result.add(resultSet.getInt("idFiles"));
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<String> getAllUsersByFile(String login, int idFile) {
        return null;
    }

    @Override
    public String getFileStatus(String login, int idFile) {

        String result = null;
        String sql = "SELECT status FROM Files WHERE idFiles = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, idFile);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                result = resultSet.getString("title");

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addUserToFile(String login, String newUserLogin, int idFile) {
        return false;
    }

    @Override
    public String getCommitDateById(String login, int idCommit) {
        return null;
    }

    @Override
    public Commit getCommitById(String login, int idFile, int idCommit) {
        return null;
    }

}
