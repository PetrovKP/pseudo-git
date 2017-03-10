package com.nopointer.server.database;

import com.nopointer.server.entity.Commit;

import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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

    private int getActualId(int idFile) {
        int result = 0;
        String sql = "SELECT MAX(Commits.idLocalCommits) AS maxIndex " +
                "FROM Commits WHERE idFile = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idFile);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt("maxIndex");
            result = count;

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
        boolean result = false;
        if ( !isExistLogin(login) ) {
            String sql = "INSERT INTO Users (login, password) VALUES (?, ?)";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);

                preparedStatement.executeUpdate();

                preparedStatement.close();
                result = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
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
        return true;
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
    public List<String> getActualText(String login, int idFile) {
        List<String> result = new ArrayList<>();

        String sql = "SELECT text FROM Commits WHERE idLocalCommits = ? AND idFile = ?";
        try {
            int idActualCommit = getActualId(idFile);

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idActualCommit);
            preparedStatement.setInt(2, idFile);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            // Преобразование blob в массив строк
            Blob blob = resultSet.getBlob("text");
            String data = new String(blob.getBytes(1, (int)blob.length()));
            result = Arrays.asList(data.split("\n"));
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Integer> getAllCommitsId(String login, int idFile) {
        return null;
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
    public boolean changeFileStatus(String login, int idFile) {
        return false;
    }

    @Override
    public boolean addCommit(int idUser, int idFile, List<String> text) {
        boolean result = false;
        String sql = "INSERT INTO Commits (idLocalCommits, idFile," +
                " idUser, text, data) VALUES (?, ?, ?, ?, ?)";
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();;
            DataOutputStream out = new DataOutputStream(baos);

            for (String element : text) {
                out.write(element.getBytes(Charset.forName("UTF-8")));
                out.write("\n".getBytes(Charset.forName("UTF-8")));
            }

            Blob blob=new SerialBlob(baos.toByteArray());

            java.util.Date date = Calendar.getInstance().getTime();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
            int localFileId = getActualId(idFile) + 1;

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, localFileId);
            preparedStatement.setInt(2, idFile);
            preparedStatement.setInt(3, idUser);
            preparedStatement.setBlob(4, blob);
            preparedStatement.setTimestamp(5, sqlDate);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            result = true;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getCommitDateById(String login, int idCommit) {

        return null;
    }

    @Override
    public Commit getCommitById(String login, int idFile, int idCommit) {
        return null;
    }

    @Override
    public Commit getCommitByDate(int idFile, String date) {
        return null;
    }

    @Override
    public boolean revertFileToCommit(String login, int idFile, int idCommit) {
        return false;
    }

}
