package com.nopointer.server.database;

import com.nopointer.server.entity.Commit;

import java.sql.Connection;
import java.util.List;

public interface DatabaseAPI {
    void setConnection(Connection connection);
    // Users
    void registerUser(String login, String password);

    boolean login(String login, String password);

    // Texts
    void createFile(String title, String text);
    String getTitle(int idFile);
    List<Integer> getAllFilesId();
    String getFileStatus();
    void changeFileStatus();

    // Commit
    String getActualText(int idFile);
    Commit getCommitByDate(int idFile, String date);
    List<Integer> getAllCommitsId(int idFile);
    void addCommit(int idFile, String login, String text);

}
