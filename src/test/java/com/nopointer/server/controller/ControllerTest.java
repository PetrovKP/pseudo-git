package com.nopointer.server.controller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nopointer.server.config.TestModule;
import com.nopointer.server.entity.Commit;
import com.nopointer.server.entity.TextString;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ControllerTest {
    private Controller controller;

    @Before
    public void setUp() throws Exception {
        Injector injector = Guice.createInjector(new TestModule());
        controller = injector.getInstance(Controller.class);
    }

    @Test
    public void registerUser() throws Exception {
        assertEquals(controller.registerUser("oleg", "pass"), 100);
        assertEquals(controller.registerUser("Kirill", "pass"), 200);
    }

    @Test
    public void deleteUser() throws Exception {
        assertEquals(controller.deleteUser("Oleg"), 100);
        assertEquals(controller.deleteUser("Kirill"), 200);
    }

    @Test
    public void login() throws Exception {
        assertEquals(controller.login("Oleg", "123"), 2);
        assertEquals(controller.login("oleg2", "ovcharuk"), 0);
    }

    @Test
    public void getIdUser() throws Exception {
        assertEquals(controller.getIdUser("log1"), new Integer(1));
        assertEquals(controller.getIdUser("log2"), new Integer(2));
        assertEquals(controller.getIdUser("log4"), new Integer(0));
    }

    @Test
    public void createFile() throws Exception {
        List<String> list = new ArrayList<>();
        assertEquals(controller.createFile(1,"NewFile", list), 1);
        assertEquals(controller.createFile(3,"NewFile", list), 0);
    }

    @Test
    public void deleteToFile() throws Exception {
        assertEquals(controller.deleteToFile(1,2), 100);
        assertEquals(controller.deleteToFile(3,2), 200);
    }

    @Test
    public void getTitle() throws Exception {
        assertEquals(controller.getTitle(1,1), "Title1");
        assertEquals(controller.getTitle(1, 2), "Title2");
        assertEquals(controller.getTitle(2, 1), null);
        assertEquals(controller.getTitle(3, 100), null);
    }

    @Test
    public void getAllFilesId() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        assertEquals(controller.getAllFilesId(1), list);
        assertEquals(controller.getAllFilesId(2), null);
    }

    @Test
    public void getActualText() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("Text");
        list.add("Here");

        assertEquals(controller.getActualText(1,2), null);
        assertEquals(controller.getActualText(1,1), list);
    }

    @Test
    public void getAllCommitsId() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        assertEquals(controller.getAllCommitsId(1,3), list);
        assertEquals(controller.getAllCommitsId(1,1), null);
    }

    @Test
    public void addCommit() throws Exception {
        assertEquals(controller.addCommit(1, 1, null), 100);
        assertEquals(controller.addCommit(2, 1, null), 200);
    }

    @Test
    public void deleteCommit() throws Exception {
        assertEquals(controller.deleteCommit(1, 2, 1), 100);
        assertEquals(controller.deleteCommit(1, 3, 3), 200);
    }

    @Test
    public void getCommitsCount() throws Exception {
        assertEquals(controller.getCommitsCount(1, 1), new Integer(4));
        assertEquals(controller.getCommitsCount(3, 1), new Integer(0));
    }

    @Test
    public void isAccessUserToFile() throws Exception {
        assertEquals(controller.isAccessUserToFile(2,1), 100);
        assertEquals(controller.isAccessUserToFile(1,3), 200);
    }

    @Test
    public void getAllUsersByFile() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("Petrov");
        list.add("Ovchraruk");

        assertEquals(controller.getAllUsersByFile(1,1), list);
        assertEquals(controller.getAllUsersByFile(2,3), null);
    }

    @Test
    public void addUserToFile() throws Exception {
        assertEquals(controller.addUserToFile(1, "log3", 1), 100);
        assertEquals(controller.addUserToFile(1, "log1", 1), 200);
        assertEquals(controller.addUserToFile(2, "log2", 1), 200);
    }

    @Test
    public void deleteUserToFile() throws Exception {
        assertEquals(controller.deleteUserToFile(1, 2, 2), 100);
        assertEquals(controller.deleteUserToFile(1, 3,4), 200);
    }

    @Test
    public void getCommitDateById() throws Exception {
        assertEquals(controller.getCommitDateById(1,1, 1), "03.11.2017 - 22:12");
        assertEquals(controller.getCommitDateById(1, 1, 2), "04.11.2017 - 12:12");
        assertEquals(controller.getCommitDateById(2, 1, 4), null);
    }

    @Test
    public void getCommitById() throws Exception {
        List<String> oldText = new ArrayList<>();
        List<String> newText = new ArrayList<>();
        oldText.add("Java");
        oldText.add("Not");
        newText.add("Java");
        newText.add("Yes");
        Commit commit = new Commit(oldText, newText);
        List<TextString> expected = commit.getTextStrings();

        assertEquals(expected, controller.getCommitById(1,1,1));
        assertEquals(controller.getCommitById(2,3,5),null);
    }


    @Test
    public void revertFileToCommit() throws Exception {
        assertEquals(controller.revertFileToCommit(1, 1, 1), 100);
        assertEquals(controller.revertFileToCommit(1, 3, 3), 200);
    }
}
