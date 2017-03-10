package com.nopointer.server.controller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nopointer.server.config.TestModule;
import com.nopointer.server.entity.Commit;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ControllerTest {
    private Controller controller;

    @Before
    public void setUp() throws Exception {
        Injector injector = Guice.createInjector(new TestModule());
        controller = injector.getInstance(Controller.class);
    }

    @Test
    public void registerUser() throws Exception {
        assertEquals(controller.registerUser("Oleg", "pass"), 200);
        assertEquals(controller.registerUser("Kirill", "pass"), 100);
    }

    @Test
    public void deleteUser() throws Exception {
        controller.deleteUser("Oleg");
        assertEquals("a", "a");
    }

    @Test
    public void login() throws Exception {
        assertEquals(controller.login("oleg", "ovcharuk"), 100);
        assertEquals(controller.login("oleg2", "ovcharuk"), 200);
    }

    @Test
    public void createFile() throws Exception {
        assertEquals(controller.createFile("New file"), 100);
        assertEquals(controller.createFile("FIRST FILE"), 200);
    }

    @Test
    public void getTitle() throws Exception {
        assertEquals(controller.getTitle(1), "Title1");
        // TODO : change
        assertEquals(controller.getTitle(2), "Title2");
        assertEquals(controller.getTitle(3), null);
    }

    @Test
    public void changeFileStatus() throws Exception {
        assertEquals(controller.changeFileStatus(2), 100);
        assertEquals(controller.changeFileStatus(1), 200);
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

        assertEquals(controller.getActualText(2), null);
        assertEquals(controller.getActualText(1), list);
    }

    @Test
    public void getCommitByDate() throws Exception {
        List<String> list1 = new ArrayList<>();
        list1.add("Text");
        list1.add("Here");
        List<String> list2 = new ArrayList<>();
        list2.add("Text1");
        list2.add("Here1");
        Commit commit1 = new Commit(list1, list2);
        Commit commit2 = new Commit(list2, list1);
        assertTrue(controller.getCommitByDate(1, "NOW").equals(commit1));
        assertFalse(controller.getCommitByDate(1, "NOW").equals(commit2));
    }

    @Test
    public void getAllCommitsId() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        assertEquals(controller.getAllCommitsId(3), list);
        assertEquals(controller.getAllCommitsId(1), null);
    }

    @Test
    public void addCommit() throws Exception {
        assertEquals(controller.addCommit(1, 1, null), 100);
        assertEquals(controller.addCommit(2, 1, null), 200);
    }
}