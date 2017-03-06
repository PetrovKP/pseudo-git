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
        if (login.equals("Oleg")) return false;
        return true;
    }

    @Override
    public void deleteUser(String login) {

    }

    @Override
    public boolean login(String login, String password) {
        return (login.equals("oleg") && password.equals("ovcharuk"));
    }

    @Override
    public boolean createFile(String title) {
        return !(title.equals("FIRST FILE"));
    }

    @Override
    public String getTitle(int idFile) {
        if (idFile == 1) {
            return "Title1";
        }
        if (idFile == 2) {
            return "Title2";
        }
        return null;
    }

    @Override
    public String getFileStatus(int idFile) {
        if (idFile == 1) {
            return "locked";
        }
        if (idFile == 2) {
            return "unlocked";
        }
        return null;
    }

    @Override
    public boolean changeFileStatus(int idFile) {
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
    public List<String> getActualText(int idFile) {
        List<String> list = new ArrayList<>();
        list.add("Text");
        list.add("Here");
        return (idFile == 5) ? list : null;
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
    public List<Integer> getAllCommitsId(int idFile) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        return !(idFile == 3) ? list : null;
    }

    @Override
    public boolean addCommit(int idFile, String login, List<String> text) {
        return false;
    }
}
