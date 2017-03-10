package com.nopointer.server.controller;

import com.nopointer.server.entity.Commit;

import java.util.List;

public interface Controller {
    // TODO: add all functions
    int registerUser(String login, String password);

    int deleteUser(String login);

    int login(String login, String password);

    // Texts
    int createFile(String title);

    int createFile(String login, String title, List<String> text);

    String getTitle(int idFile);

    String getTitle(String login, int idFile);

    String getFileStatus(int idFile);

    String getFileStatus(String login, int idFile);

    int changeFileStatus(int idFile);

    int changeFileStatus(String login, int idFile);

    // Access
    List<Integer> getAllFilesId(String login);

    // Commit
    List<String> getActualText(int idFile);

    List<String> getActualText(String login, int idFile);

    Commit getCommitByDate(int idFile, String date);

    List<Integer> getAllCommitsId(int idFile);

    List<Integer> getAllCommitsId(String login, int idFile);

    int addCommit(int idFile, int idUser, List<String> text);
}
