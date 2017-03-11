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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class DatabaseAPIGit implements DatabaseAPI {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private static int id = 4;

    public DatabaseAPIGit() {
        connection = null;
        preparedStatement = null;
    }

    private boolean isExistLogin(String login) {
        boolean result = false;
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

    private boolean addUserToFileWithoutChecking(int idUser, int idFile) {
        boolean result = false;
        //  Если нет в таблице пользователя и id файла
        if (!isAccessUserToFile(idUser, idFile)) {
            String sql = "INSERT INTO Access (idUser, idFile) VALUES (?, ?)";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, idUser);
                preparedStatement.setInt(2, idFile);

                preparedStatement.executeUpdate();
                preparedStatement.close();
                result = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        boolean result = false;
        if ( isExistLogin(login) ) {
            String sql = "DELETE FROM Users WHERE login = ?";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, login);

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
    public boolean login(String login, String password) {
        boolean result = false;
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
    public int getIdUser(String login) {
        int result = 0;
        String sql = "SELECT idUsers FROM Users WHERE login = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                result = resultSet.getInt("idUsers");
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int createFile(int idUser, String title, List<String> text) {
        int result = 0;
        String sql = "INSERT INTO Files (title) VALUES (?)";
        try {
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, title);

            preparedStatement.executeUpdate();
            // Вернуть автосгенерируемый id файла
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int idFile = resultSet.getInt(1);

            // Сразу выполняем коммит (самый первый для этого файла)
            addCommit(idUser, idFile, text);
            // И добавляем права доступа для этого пользователя
            addUserToFileWithoutChecking(idUser, idFile);
            preparedStatement.close();
            result = idFile;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteToFile(int idUser, int idFile) {
        boolean result = false;
        if (isAccessUserToFile(idUser, idFile)) {
            String sql = "DELETE FROM Files WHERE idFiles = ?";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, idFile);

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
    public Integer getCommitsCount(int idUser, int idFile) {
        Integer result = 0;
        if (isAccessUserToFile(idUser, idFile)) {
            String sql = "SELECT COUNT(*) AS rowcount FROM Commits WHERE idFile = ?";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, idFile);

                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                result = resultSet.getInt("rowcount");

                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public String getTitle(int idUser, int idFile) {
        String result = null;
        if (isAccessUserToFile(idUser, idFile)) {
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
        }
        return result;
    }

    @Override
    public List<Integer> getAllFilesId(int idUser) {
        List<Integer> result = new ArrayList<>();
        String sql = "SELECT idFile FROM Access WHERE idUser = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUser);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                result.add(resultSet.getInt("idFile"));
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public List<String> getActualText(int idUser, int idFile) {
        List<String> result = new ArrayList<>();

        if (isAccessUserToFile(idUser, idFile)) {
            String sql = "SELECT text FROM Commits WHERE idLocalCommits = ? AND idFile = ?";
            try {
                int idActualCommit = getActualId(idFile);

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, idActualCommit);
                preparedStatement.setInt(2, idFile);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    // Преобразование blob в массив строк
                    Blob blob = resultSet.getBlob("text");
                    String data = new String(blob.getBytes(1, (int) blob.length()));
                    result = Arrays.asList(data.split("\n"));
                }
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public List<Integer> getAllCommitsId(int idUser, int idFile) {
        List<Integer> result = new ArrayList<>();
        if (isAccessUserToFile(idUser, idFile)) {
            String sql = "SELECT idLocalCommits FROM Commits WHERE idFile = ?";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, idFile);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next())
                    result.add(resultSet.getInt("idLocalCommits"));
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public boolean isAccessUserToFile(int idUser, int idFile) {
        boolean result = false;
        String sql = "SELECT * FROM Access WHERE idUser = ? AND idFile = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idFile);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<String> getAllUsersByFile(int idUser, int idFile) {
        List<String> result = new ArrayList<>();
        if (isAccessUserToFile(idUser, idFile)) {
            String sql = "SELECT Users.login FROM Users, Access" +
                    " WHERE Access.idFile = ? AND Users.idUsers = Access.idUser";
            try {

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, idFile);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    result.add(resultSet.getString("login"));
                }
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public boolean addUserToFile(int idUser, int newIdUser, int idFile) {
        boolean result = false;
        // Проверка наличия доступа к данному файлу
        if (isAccessUserToFile(idUser, idFile) ) {
            result = addUserToFileWithoutChecking(newIdUser, idFile);
        }
        return result;
    }

    @Override
    public boolean deleteUserToFile(int idUser, int newIdUser, int idFile) {
        boolean result = false;
        if (isAccessUserToFile(idUser, idFile) && isAccessUserToFile(newIdUser, idFile)) {
            String sql = "DELETE FROM Access WHERE idUser = ? AND idFile = ?";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, newIdUser);
                preparedStatement.setInt(2, idFile);

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
    public boolean deleteCommit(int idUser, int idFile, int idCommit) {
        boolean result = false;
        if (isAccessUserToFile(idUser, idFile)) {
            String sql = "DELETE FROM Commits " +
                    "WHERE idUser = ? AND idFile = ? AND idLocalCommits = ?";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, idUser);
                preparedStatement.setInt(2, idFile);
                preparedStatement.setInt(3, idCommit);

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
    public String getCommitDateById(int idUser, int idFile, int idCommit) {
        String result = "";
        String sql = "SELECT data FROM Commits WHERE idFile = ? AND idLocalCommits = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idFile);
            preparedStatement.setInt(2, idCommit);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                // Конвертация sql даты в строку по выбранному шаблону
                Timestamp timestamp = resultSet.getTimestamp("data");
                if (timestamp != null) {
                    java.sql.Date date = new java.sql.Date(timestamp.getTime());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY - HH:mm");
                    result = dateFormat.format(date);
                }
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Commit getCommitById(int idUser, int idFile, int idCommit) {
        Commit result = new Commit();
        if (isAccessUserToFile(idUser, idFile)) {
            String sql = "SELECT text FROM Commits " +
                    "WHERE idUser = ? AND idFile = ? AND " +
                    "(idLocalCommits = ? OR idLocalCommits = ?)" +
                    " ORDER BY idLocalCommits DESC";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, idUser);
                preparedStatement.setInt(2, idFile);
                preparedStatement.setInt(3, idCommit);
                preparedStatement.setInt(4, idCommit - 1);

                ResultSet resultSet = preparedStatement.executeQuery();

                List<String> oldT = new ArrayList<>();
                List<String> newT = new ArrayList<>();

                if (resultSet.next()) {
                    // Преобразование blob в массив строк
                    Blob blob = resultSet.getBlob("text");
                    String data = new String(blob.getBytes(1, (int) blob.length()));
                    newT = Arrays.asList(data.split("\n"));
                }
                if (resultSet.next()) {
                    // Преобразование blob в массив строк
                    Blob blob = resultSet.getBlob("text");
                    String data = new String(blob.getBytes(1, (int) blob.length()));
                    oldT = Arrays.asList(data.split("\n"));
                }
                result = new Commit(oldT, newT);
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
//    TODO:
//    Пользователь может удалять коммиты других пользователй?
    public boolean revertFileToCommit(int idUser, int idFile, int idCommit) {
        boolean result = false;
        if (isAccessUserToFile(idUser, idFile)) {
            String sql = "DELETE FROM Commits " +
                    "WHERE idUser = ? AND idFile = ? AND idLocalCommits > ?";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, idUser);
                preparedStatement.setInt(2, idFile);
                preparedStatement.setInt(3, idCommit);

                int count = preparedStatement.executeUpdate();
                preparedStatement.close();

                result = count > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
