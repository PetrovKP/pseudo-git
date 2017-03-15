package com.nopointer.server.controller;

import com.google.inject.Inject;
import com.nopointer.server.database.Database;
import com.nopointer.server.entity.Commit;
import com.nopointer.server.entity.TextString;

import java.sql.SQLException;
import java.util.List;

class ControllerImpl implements Controller {
    private Database database;

    @Inject
    public ControllerImpl(Database database) throws SQLException{
        this.database = database;
        database.connect();
    }

    @Override
    public int registerUser(String login, String password) throws SQLException {
        return database.getAPI().registerUser(login, password) ? 100 : 200;
    }

    @Override
    public int deleteUser(String login) throws SQLException {
        return database.getAPI().deleteUser(login) ? 100 : 200;
    }

    @Override
    public int login(String login, String password) throws SQLException {
        return database.getAPI().login(login, password);
    }

    @Override
    public Integer getIdUser(String login) throws SQLException {
        return database.getAPI().getIdUser(login);
    }

    @Override
    public int createFile(int idUser, String title, List<String> text) throws SQLException {
        return database.getAPI().createFile(idUser, title, text);
    }

    @Override
    public int deleteToFile(int idUser, int idFile) throws SQLException {
        return database.getAPI().deleteToFile(idUser, idFile) ? 100 : 200;
    }

    @Override
    public Integer getCommitsCount(int idUser, int idFile) throws SQLException {
        return database.getAPI().getCommitsCount(idUser, idFile);
    }

    @Override
    public String getTitle(int idUser, int idFile) throws SQLException {
        return database.getAPI().getTitle(idUser, idFile);
    }

    @Override
    public List<Integer> getAllFilesId(int idUser) throws SQLException {
        return database.getAPI().getAllFilesId(idUser);
    }

    @Override
    public List<String> getActualText(int idUser, int idFile) throws SQLException {
        return database.getAPI().getActualText(idUser, idFile);
    }

    @Override
    public List<Integer> getAllCommitsId(int idUser, int idFile) throws SQLException {
        return database.getAPI().getAllCommitsId(idUser, idFile);
    }

    @Override
    public int isAccessUserToFile(int idUser, int idFile) throws SQLException {
        return database.getAPI().isAccessUserToFile(idUser, idFile) ? 100 : 200;
    }

    @Override
    public List<String> getAllUsersByFile(int idUser, int idFile) throws SQLException {
        return database.getAPI().getAllUsersByFile(idUser, idFile);
    }

    @Override
    public int addUserToFile(int idUser, String newUser, int idFile) throws SQLException {
        int newIdUser = database.getAPI().getIdUser(newUser);
        if ( newIdUser == 0 )
            return 200;
        return database.getAPI().addUserToFile(idUser, newIdUser, idFile) ? 100 : 200;
    }

    @Override
    public int deleteUserToFile(int idUser, int newIdUser, int idFile) throws SQLException {
        return database.getAPI().deleteUserToFile(idUser, newIdUser, idFile) ? 100 : 200;
    }

    @Override
    public int addCommit(int idUser, int idFile, List<String> text) throws SQLException {
        return database.getAPI().addCommit(idUser, idFile, text) ? 100 : 200;
    }

    @Override
    public int deleteCommit(int idUser, int idFile, int idCommit) throws SQLException {
        return database.getAPI().deleteCommit(idUser, idFile, idCommit) ? 100 : 200;
    }

    @Override
    public String getCommitDateById(int idUser, int idFile, int idCommit) throws SQLException {
        return database.getAPI().getCommitDateById(idUser, idFile, idCommit);
    }

    @Override
    public List<TextString> getCommitById(int idUser, int idFile, int idCommit) throws SQLException {
        Commit commit = database.getAPI().getCommitById(idUser, idFile, idCommit);
        if (commit == null)
            return null;
        return commit.getTextStrings();
    }

    @Override
    public int revertFileToCommit(int idUser, int idFile, int idCommit) throws SQLException {
        return database.getAPI().revertFileToCommit(idUser, idFile, idCommit) ? 100 : 200;
    }

}
