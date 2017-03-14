package com.nopointer.server.database;

import com.nopointer.server.entity.Commit;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DatabaseAPI {
    void setConnection(Connection connection);

    // Users
    boolean registerUser(String login, String password) throws SQLException;
    int login(String login, String password) throws SQLException;
    Integer getIdUser(String login) throws SQLException;

    @Deprecated
    boolean deleteUser(String login) throws SQLException;

    // Files
    int createFile(int idUser, String title, List<String> text) throws SQLException;
    @Deprecated
    boolean deleteToFile(int idUser, int idFile) throws SQLException;

    Integer getCommitsCount(int idUser, int idFile) throws SQLException;
    String getTitle(int idUser, int idFile) throws SQLException;
    List<Integer> getAllFilesId(int idUser) throws SQLException;
    List<String> getActualText(int idUser, int idFile) throws SQLException;
    List<Integer> getAllCommitsId(int idUser, int idFile) throws SQLException;

    //Access
    boolean isAccessUserToFile(int idUser, int idFile) throws SQLException;
    List<String> getAllUsersByFile(int idUser, int idFile) throws SQLException;
    boolean addUserToFile(int idUser, int newIdUser, int idFile) throws SQLException;
    @Deprecated
    boolean deleteUserToFile(int idUser, int newIdUser, int idFile) throws SQLException;

    // Commits
    boolean addCommit(int idUser, int idFile, List<String> text) throws SQLException;
    @Deprecated
    boolean deleteCommit(int idUser, int idFile, int idCommit) throws SQLException;
    String getCommitDateById(int idUser, int idFile, int idCommit) throws SQLException;
    Commit getCommitById(int idUser, int idFile, int idCommit) throws SQLException;
    boolean revertFileToCommit(int idUser, int idFile, int idCommit) throws SQLException;
}
