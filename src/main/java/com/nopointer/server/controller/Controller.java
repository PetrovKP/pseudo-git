package com.nopointer.server.controller;

import com.nopointer.server.entity.Commit;

import java.util.List;

public interface Controller {
    // TODO: add all functions
    int registerUser(String login, String password);

    int deleteUser(String login);

    int login(String login, String password);

    int getIdUser(String login);

    // Texts

    int createFile(int idUser, String title, List<String> text);

    int deleteToFile(int idUser, int idFile);

    Integer getCommitsCount(int idUser, int idFile);

    List<Integer> getAllFilesId(int idUser);

    String getTitle(int idUser, int idFile);

    List<String> getActualText(int idUser, int idFile);

    List<Integer> getAllCommitsId(int idUser, int idFile);

    // Access

    int isAccessUserToFile(int idUser, int idFile);

    List<String> getAllUsersByFile(int idUser, int idFile);

    int addUserToFile(int idUser, int newIdUser, int idFile);

    int deleteUserToFile(int idUser, int newIdUser, int idFile);

    // Commit

    int addCommit(int idUser, int idFile, List<String> text);

    int deleteCommit(int idUser, int idFile, int idCommit);

    String getCommitDateById(int idUser, int idFile, int idCommit);

    Commit getCommitById(int idUser, int idFile, int idCommit);

    int revertFileToCommit(int idUser, int idFile, int idCommit);

}
