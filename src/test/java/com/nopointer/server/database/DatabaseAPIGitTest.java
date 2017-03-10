package com.nopointer.server.database;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nopointer.server.config.Module;
import com.nopointer.server.config.TestModule;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class DatabaseAPIGitTest {
    private Database database;

    @Before
    public void setUp() throws Exception {
        Injector injector = Guice.createInjector(new TestModule());
        database = injector.getInstance(Database.class);

        database.connect();
    }

    @Test
    public void registerUser() throws Exception {
        boolean isEnter;
        database.getAPI().registerUser("ADD", "123");
        isEnter = database.getAPI().login("ADD", "123");
        assertTrue(isEnter);

        database.getAPI().registerUser("ADD", "145");
        isEnter = database.getAPI().login("ADD", "123");
        assertTrue(isEnter);

        database.getAPI().deleteUser("ADD");
    }

    @Test
    public void login() throws Exception {
        boolean isEnter;
        isEnter = database.getAPI().login("petrov", "qwerty123");
        assertTrue(isEnter);

        isEnter = database.getAPI().login("ovcharuk", "12332");
        assertFalse(isEnter);
    }

    @Test
    public void createFile() throws Exception {

    }

    @Test
    public void getTitle() throws Exception {
        String result, expected;

        result = database.getAPI().getTitle("petrov", 1);

        expected = "test1";
        assertEquals(result, expected);

        result = database.getAPI().getTitle("petrov", 100);

        assertNull(result);
    }

    @Test
    public void getAllFilesId() throws Exception {
        List<Integer> result, expected;

        result = database.getAPI().getAllFilesId("e");

        expected = new ArrayList<Integer>();
        Collections.addAll(expected, 1, 2, 3);
        assertEquals(result, expected);
    }

    @Test
    public void getFileStatus() throws Exception {

    }

    @Test
    public void changeFileStatus() throws Exception {

    }

    @Test
    public void getActualText() throws Exception {

    }

    @Test
    public void getCommitByDate() throws Exception {

    }

    @Test
    public void getAllCommitsId() throws Exception {

    }

    @Test
    public void addCommit() throws Exception {

    }
}
