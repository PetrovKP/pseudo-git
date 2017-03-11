package com.nopointer.server.database;

import com.nopointer.server.entity.Commit;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAPIStub implements DatabaseAPI {
    @Override
    public void setConnection(Connection connection) {
    }

    @Override
    public boolean registerUser(String login, String password) {
        return !login.equals("Oleg");
    }

    @Override
    public boolean deleteUser(String login) {
        return login.equals("Oleg");
    }

    @Override
    public boolean login(String login, String password) {
        return (login.equals("oleg") && password.equals("ovcharuk"));
    }

    @Override
    public int getIdUser(String login) {
        return 0;
    }

    @Override
    public int createFile(int idUser, String title, List<String> text) {
        return 1;
    }

    @Override
    public boolean deleteToFile(int idUser, int idFile) {
        return idUser == 1 && idFile == 2;
    }

    @Override
    public Integer getCommitsCount(int idUser, int idFile) {
        return null;
    }

    @Override
    public String getTitle(int idUser, int idFile) {
        if (idFile == 1 && idUser == 1) {
            return "Title1";
        }
        if (idFile == 2 && idUser == 1) {
            return "Title2";
        }
        return null;
    }

    @Override
    public boolean addUserToFile(int idUser, int newIdUser, int idFile) {
        return idUser != newIdUser && idUser == 1 && newIdUser != 2;
    }

    @Override
    public boolean deleteUserToFile(int idUser, int newIdUser, int idFile) {
        return idUser != newIdUser && idUser == 1;
    }

    @Override
    public List<Integer> getAllFilesId(int idUser) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        return (idUser == 1) ? list : null;
    }

    @Override
    public List<String> getActualText(int idUser, int idFile) {
        List<String> list = new ArrayList<>();
        list.add("Text");
        list.add("Here");
        return (idFile == 1) ? list : null;
    }

    @Override
    public List<Integer> getAllCommitsId(int idUser, int idFile) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        return (idFile == 3 && idUser == 1) ? list : null;
    }

    @Override
    public boolean isAccessUserToFile(int idUser, int idFile) {
        return idUser == 2 && idFile == 1;
    }

    @Override
    public List<String> getAllUsersByFile(int idUser, int idFile) {
        List<String> list = new ArrayList<>();
        list.add("Petrov");
        list.add("Ovchraruk");
        return (idFile == 1 && idUser == 1) ? list : null;
    }

    @Override
    public boolean addCommit(int idUser, int idFile, List<String> text) {
        return (idFile == 1);
    }

    @Override
    public boolean deleteCommit(int idUser, int idFile, int idCommit) {
        return idUser == 1 && idFile == 2 && idCommit == 1;
    }

    @Override
    public String getCommitDateById(int idUser, int idFile, int idCommit) {
        if (idFile == 1 && idUser == 1 && idCommit == 1) {
            return "03.11.2017 - 22:12";
        }
        if (idFile == 1 && idUser == 1 && idCommit == 2) {
            return "04.11.2017 - 12:12";
        }
        return null;
    }

    @Override
    public Commit getCommitById(int idUser, int idFile, int idCommit) {
//        if (idFile == 1 && idUser == 1 && idCommit == 1)
//            return new Commit();
//        else
//            return null;
        return null;
    }

    @Override
    public boolean revertFileToCommit(int idUser, int idFile, int idCommit) {
        return idUser == 1 && idFile == 1 && idCommit == 1;
    }
}
