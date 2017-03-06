package com.nopointer.server.controller;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.nopointer.server.database.Database;
import com.nopointer.server.entity.Commit;

import java.util.List;

class ControllerImpl implements Controller {
    private Database database;

    @Inject
    public ControllerImpl(@Named("StubIfNeeded") Database database) {
        this.database = database;
        database.connect();
    }

    // TODO: add all functions, provided by DatabaseAPI

    @Override
    public int registerUser(String login, String password) {
        return database.getAPI().registerUser(login, password) ? 100 : 200;
    }

    @Override
    public int deleteUser(String login) {
        database.getAPI().deleteUser(login);
        return -1;
    }

    @Override
    public int login(String login, String password) {
        return database.getAPI().login(login, password) ? 100 : 200;
    }

    @Override
    public int createFile(String title) {
        return database.getAPI().createFile(title) ? 100 : 200;
    }

    @Override
    public String getTitle(int idFile) {
        return database.getAPI().getTitle(idFile);
    }

    @Override
    public String getFileStatus(int idFile) {
        return database.getAPI().getFileStatus(idFile);
    }

    @Override
    public int changeFileStatus(int idFile) {
        return database.getAPI().changeFileStatus(idFile) ? 100 : 200;
    }

    @Override
    public List<Integer> getAllFilesId(String login) {
        return database.getAPI().getAllFilesId(login);
    }

    @Override
    public List<String> getActualText(int idFile) {
        return database.getAPI().getActualText(idFile);
    }

    @Override
    public Commit getCommitByDate(int idFile, String date) {
        return database.getAPI().getCommitByDate(idFile, date);
    }

    @Override
    public List<Integer> getAllCommitsId(int idFile) {
        return database.getAPI().getAllCommitsId(idFile);
    }

    @Override
    public int addCommit(int idFile, String login, List<String> text) {
        return database.getAPI().addCommit(idFile, login, text) ? 100 : 200;
    }


}
