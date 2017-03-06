package com.nopointer.server.database;

import com.nopointer.server.entity.Commit;

import java.sql.Connection;
import java.util.List;

public interface DatabaseAPI {
    void setConnection(Connection connection);
    // Users
    boolean registerUser(String login, String password);
    void deleteUser(String login);
    boolean login(String login, String password);

    // Texts
    boolean createFile(String title);
    String getTitle(int idFile);
    String getFileStatus(int idFile);
    boolean changeFileStatus(int idFile);

    // Access
    List<Integer> getAllFilesId(String login);

    // Commit
    List<String> getActualText(int idFile);
    Commit getCommitByDate(int idFile, String date);
    List<Integer> getAllCommitsId(int idFile);
    boolean addCommit(int idFile, String login, List<String> text);

}
