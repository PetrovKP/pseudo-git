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

    String getTitle(int idFile);

    String getFileStatus(int idFile);

    int changeFileStatus(int idFile);

    // Access
    List<Integer> getAllFilesId(String login);

    // Commit
    List<String> getActualText(int idFile);

    Commit getCommitByDate(int idFile, String date);

    List<Integer> getAllCommitsId(int idFile);

    int addCommit(int idFile, String login, List<String> text);
}
