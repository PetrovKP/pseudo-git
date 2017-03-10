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
        return true;
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
    public boolean createFile(int idUser, String title, List<String> text) {
        return !(title.equals("FIRST FILE"));
    }

    @Override
    public Integer getCommitsCount(int idUser, int idFile) {
        return null;
    }

    @Override
    public String getTitle(int idUser, int idFile) {
        if (idFile == 1) {
            return "Title1";
        }
        if (idFile == 2) {
            return "Title2";
        }
        return null;
    }

    @Override
    public boolean addUserToFile(int idUser, int newIdUser, int idFile) {
        return false;
    }

    @Override
    public boolean deleteUserToFile(int idUser, int newIdUser, int idFile) {
        return false;
    }

    @Override
    public List<Integer> getAllFilesId(int idUser) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        return (idUser > 0) ? list : null;
    }

    @Override
    public List<String> getActualText(int idUser, int idFile) {
        List<String> list = new ArrayList<>();
        list.add("Text");
        list.add("Here");
        return (idFile == 1) ? list : null;
    }

//    @Override
//    public Commit getCommitByDate(int idFile, String date) {
//        List<String> list1 = new ArrayList<>();
//        list1.add("Text");
//        list1.add("Here");
//        List<String> list2 = new ArrayList<>();
//        list2.add("Text1");
//        list2.add("Here1");
//        return new Commit(list1, list2);
//    }

    @Override
    public List<Integer> getAllCommitsId(int idUser, int idFile) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        return (idFile == 3) ? list : null;
    }

    @Override
    public boolean isAccessUserToFile(int idUser, int idFile) {
        return false;
    }

    @Override
    public List<String> getAllUsersByFile(int idUser, int idFile) {
        return null;
    }

    @Override
    public boolean addCommit(int idUser, int idFile, List<String> text) {
        return (idFile == 1);
    }

    @Override
    public boolean deleteCommit(int idUser, int idFile, int idCommit) {
        return false;
    }

    @Override
    public String getCommitDateById(int idUser, int idFile, int idCommit) {
        return null;
    }

    public String getCommitDateById(int idUser, int idCommit) {
        return null;
    }

    @Override
    public Commit getCommitById(int idUser, int idFile, int idCommit) {
        return null;
    }

    @Override
    public boolean revertFileToCommit(int idUser, int idFile, int idCommit) {
        return false;
    }
}
