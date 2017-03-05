package com.nopointer.server.database;

import com.nopointer.server.entity.Commit;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabaseAPIGit implements DatabaseAPI {
    private Connection connection;

    DatabaseAPIGit(Connection connection_) {
        connection = connection_;
    }

    @Override
    public void registerUser(String login, String password) {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("SELECT * FROM `users`");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean login(String login, String password) {
        return false;
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
