package com.nopointer.server.controller;

import com.nopointer.server.entity.TextString;

import java.sql.SQLException;
import java.util.List;

public interface Controller {
    // TODO: add all functions
    int registerUser(String login, String password) throws SQLException;

    int deleteUser(String login) throws SQLException;

    int login(String login, String password) throws SQLException;

    Integer getIdUser(String login) throws SQLException;

    // Texts

    int createFile(int idUser, String title, List<String> text) throws SQLException;

    int deleteToFile(int idUser, int idFile) throws SQLException;

    Integer getCommitsCount(int idUser, int idFile) throws SQLException;

    List<Integer> getAllFilesId(int idUser) throws SQLException;

    String getTitle(int idUser, int idFile) throws SQLException;

    List<String> getActualText(int idUser, int idFile) throws SQLException;

    List<Integer> getAllCommitsId(int idUser, int idFile) throws SQLException;

    // Access

    int isAccessUserToFile(int idUser, int idFile) throws SQLException;

    List<String> getAllUsersByFile(int idUser, int idFile) throws SQLException;

    int addUserToFile(int idUser, String newUser, int idFile) throws SQLException;

    int deleteUserToFile(int idUser, String newUser, int idFile) throws SQLException;

    // Commit

    int addCommit(int idUser, int idFile, List<String> text) throws SQLException;

    int deleteCommit(int idUser, int idFile, int idCommit) throws SQLException;

    String getCommitDateById(int idUser, int idFile, int idCommit) throws SQLException;

    List<TextString> getCommitById(int idUser, int idFile, int idCommit) throws SQLException;

    int revertFileToCommit(int idUser, int idFile, int idCommit) throws SQLException;

}
