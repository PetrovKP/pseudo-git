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
    public boolean createFile(String login, String title, List<String> text) {
        return !(title.equals("FIRST FILE"));
    }

    @Override
    public Integer getCommitsCount(String login, int idFile) {
        return null;
    }

    @Override
    public String getTitle(String login, int idFile) {
        if (idFile == 1) {
            return "Title1";
        }
        if (idFile == 2) {
            return "Title2";
        }
        return null;
    }

    @Override
    public String getFileStatus(String login, int idFile) {
        if (idFile == 1) {
            return "locked";
        }
        if (idFile == 2) {
            return "unlocked";
        }
        return null;
    }

    @Override
    public boolean addUserToFile(String login, String newUserLogin, int idFile) {
        return false;
    }

    @Override
    public boolean changeFileStatus(String login, int idFile) {
        return !(idFile == 1);
    }

    @Override
    public List<Integer> getAllFilesId(String login) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        return (login.equals("Oleg")) ? list : null;
    }

    @Override
    public List<String> getActualText(String login, int idFile) {
        List<String> list = new ArrayList<>();
        list.add("Text");
        list.add("Here");
        return (idFile == 1) ? list : null;
    }

    @Override
    public Commit getCommitByDate(int idFile, String date) {
        List<String> list1 = new ArrayList<>();
        list1.add("Text");
        list1.add("Here");
        List<String> list2 = new ArrayList<>();
        list2.add("Text1");
        list2.add("Here1");
        return new Commit(list1, list2);
    }

    @Override
    public List<Integer> getAllCommitsId(String login, int idFile) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        return (idFile == 3) ? list : null;
    }

    @Override
    public List<String> getAllUsersByFile(String login, int idFile) {
        return null;
    }

    @Override
    public boolean addCommit(int idUser, int idFile, List<String> text) {
        return (idFile == 1);
    }

    @Override
    public String getCommitDateById(String login, int idCommit) {
        return null;
    }

    @Override
    public Commit getCommitById(String login, int idFile, int idCommit) {
        return null;
    }

    @Override
    public boolean revertFileToCommit(String login, int idFile, int idCommit) {
        return false;
    }
}
