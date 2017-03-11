package com.nopointer.server.database;

import com.nopointer.server.entity.Commit;

import java.sql.Connection;
import java.util.List;

public interface DatabaseAPI {
    void setConnection(Connection connection);

    // Users
    boolean registerUser(String login, String password);
    boolean login(String login, String password);
    int getIdUser(String login);

    @Deprecated
    boolean deleteUser(String login);

    // Files
    int createFile(int idUser, String title, List<String> text);
    @Deprecated
    boolean deleteToFile(int idUser, int idFile);

    Integer getCommitsCount(int idUser, int idFile);
    String getTitle(int idUser, int idFile);
    List<Integer> getAllFilesId(int idUser);
    List<String> getActualText(int idUser, int idFile);
    List<Integer> getAllCommitsId(int idUser, int idFile);

    //Access
    boolean isAccessUserToFile(int idUser, int idFile);
    List<String> getAllUsersByFile(int idUser, int idFile);
    boolean addUserToFile(int idUser, int newIdUser, int idFile);
    @Deprecated
    boolean deleteUserToFile(int idUser, int newIdUser, int idFile);

    // Commits
    boolean addCommit(int idUser, int idFile, List<String> text);
    @Deprecated
    boolean deleteCommit(int idUser, int idFile, int idCommit);
    String getCommitDateById(int idUser, int idFile, int idCommit);
    Commit getCommitById(int idUser, int idFile, int idCommit);
    boolean revertFileToCommit(int idUser, int idFile, int idCommit);
}
