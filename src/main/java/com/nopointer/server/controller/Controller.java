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
    int createFile(String title);

    int createFile(int idUser, String title, List<String> text);

    String getTitle(int idFile);

    String getTitle(int idUser, int idFile);

    int changeFileStatus(int idFile);

    int changeFileStatus(String login, int idFile);

    // Access
    List<Integer> getAllFilesId(int idUser);

    // Commit
    List<String> getActualText(int idFile);

    List<String> getActualText(int idUser, int idFile);

    Commit getCommitByDate(int idFile, String date);

    List<Integer> getAllCommitsId(int idFile);

    List<Integer> getAllCommitsId(int idUser, int idFile);

    int addCommit(int idFile, int idUser, List<String> text);
}
