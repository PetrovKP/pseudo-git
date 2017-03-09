package com.nopointer.server.database;

import com.nopointer.server.entity.Commit;

import java.sql.Connection;
import java.util.List;

public interface DatabaseAPI {
    void setConnection(Connection connection);

    // Users
    boolean registerUser(String login, String password);
    boolean login(String login, String password);

    @Deprecated
    boolean deleteUser(String login);

    // Files
    boolean createFile(String login, String title, List<String> text); // Я на уровне клиента запрещу создать пустой по содержанию файл
    Integer getCommitsCount(String login, int idFile);
    String getTitle(String login, int idFile);
    List<Integer> getAllFilesId(String login);
    List<String> getActualText(String login, int idFile);
    List<Integer> getAllCommitsId(String login, int idFile);

    //Access
    List<String> getAllUsersByFile(String login, int idFile);
    String getFileStatus(String login, int idFile);
    boolean addUserToFile(String login, String newUserLogin, int idFile);
    boolean changeFileStatus(String login, int idFile);

    // Commits
    String getCommitDateById(String login, int idCommit);
    Commit getCommitById(String login, int idFile, int idCommit);
    boolean addCommit (String login, int idFile, List<String> text);
    boolean revertFileToCommit (String login, int idFile, int idCommit);

}
