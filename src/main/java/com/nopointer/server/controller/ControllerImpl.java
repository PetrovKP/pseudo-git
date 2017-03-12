package com.nopointer.server.controller;

import com.google.inject.Inject;
import com.nopointer.server.database.Database;
import com.nopointer.server.entity.Commit;

import java.util.List;

class ControllerImpl implements Controller {
    private Database database;

    @Inject
    public ControllerImpl(Database database) {
        this.database = database;
        database.connect();
    }

    @Override
    public int registerUser(String login, String password) {
        return database.getAPI().registerUser(login, password);
    }

    @Override
    public int deleteUser(String login) {
        return database.getAPI().deleteUser(login) ? 100 : 200;
    }

    @Override
    public int login(String login, String password) {
        return database.getAPI().login(login, password) ? 100 : 200;
    }

    @Override
    public Integer getIdUser(String login) {
        return database.getAPI().getIdUser(login);
    }

    @Override
    public int createFile(int idUser, String title, List<String> text) {
        return database.getAPI().createFile(idUser, title, text);
    }

    @Override
    public int deleteToFile(int idUser, int idFile) {
        return database.getAPI().deleteToFile(idUser, idFile) ? 100 : 200;
    }

    @Override
    public Integer getCommitsCount(int idUser, int idFile) {
        return database.getAPI().getCommitsCount(idUser, idFile);
    }

    @Override
    public String getTitle(int idUser, int idFile) {
        return database.getAPI().getTitle(idUser, idFile);
    }

    @Override
    public List<Integer> getAllFilesId(int idUser) {
        return database.getAPI().getAllFilesId(idUser);
    }

    @Override
    public List<String> getActualText(int idUser, int idFile) {
        return database.getAPI().getActualText(idUser, idFile);
    }

    @Override
    public List<Integer> getAllCommitsId(int idUser, int idFile) {
        return database.getAPI().getAllCommitsId(idUser, idFile);
    }

    @Override
    public int isAccessUserToFile(int idUser, int idFile) {
        return database.getAPI().isAccessUserToFile(idUser, idFile) ? 100 : 200;
    }

    @Override
    public List<String> getAllUsersByFile(int idUser, int idFile) {
        return database.getAPI().getAllUsersByFile(idUser, idFile);
    }

    @Override
    public int addUserToFile(int idUser, int newIdUser, int idFile) {
        return database.getAPI().addUserToFile(idUser, newIdUser, idFile) ? 100 : 200;
    }

    @Override
    public int deleteUserToFile(int idUser, int newIdUser, int idFile) {
        return database.getAPI().deleteUserToFile(idUser, newIdUser, idFile) ? 100 : 200;
    }

    @Override
    public int addCommit(int idFile, int idUser, List<String> text) {
        return database.getAPI().addCommit(idUser, idFile, text) ? 100 : 200;
    }

    @Override
    public int deleteCommit(int idUser, int idFile, int idCommit) {
        return database.getAPI().deleteCommit(idUser, idFile, idCommit) ? 100 : 200;
    }

    @Override
    public String getCommitDateById(int idUser, int idFile, int idCommit) {
        return database.getAPI().getCommitDateById(idUser, idFile, idCommit);
    }

    @Override
    public Commit getCommitById(int idUser, int idFile, int idCommit) {
        return database.getAPI().getCommitById(idUser, idFile, idCommit);
    }

    @Override
    public int revertFileToCommit(int idUser, int idFile, int idCommit) {
        return database.getAPI().revertFileToCommit(idUser, idFile, idCommit) ? 100 : 200;
    }

}
