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
    public void getIdUser() throws Exception {
        int idUser;

        idUser = database.getAPI().getIdUser("petrov");
        assertEquals(1, idUser);

        idUser = database.getAPI().getIdUser("petrov5");
        assertEquals(0, idUser);
    }

    @Test
    public void createFile() throws Exception {
        boolean isAdd, isDelete;
        List<String> text = new ArrayList<>();
        Collections.addAll(text, "Petrov", "love", "is", "Java!");

        isAdd = database.getAPI().createFile(1,"title_petrov", text);

        assertTrue(isAdd);
//
//        isDelete = database.getAPI().deleteCommit(1,)
    }

    @Test
    public void getTitle() throws Exception {
        String result, expected;

        result = database.getAPI().getTitle(1, 1);

        expected = "test1";
        assertEquals(result, expected);

        result = database.getAPI().getTitle(1, 100);

        assertNull(result);
    }

    @Test
    public void getAllFilesId() throws Exception {
        List<Integer> result, expected;

        result = database.getAPI().getAllFilesId(1);

        expected = new ArrayList<Integer>();
        Collections.addAll(expected, 1, 2, 3);
        assertEquals(expected, result);
    }

    @Test
    public void addUserToFile() throws Exception {
        boolean isAdd, isDelete;

        isAdd = database.getAPI().addUserToFile(1, 2, 1);
        assertTrue(isAdd);

        isDelete = database.getAPI().deleteUserToFile(1,2,1);
        assertTrue(isDelete);

        isAdd = database.getAPI().addUserToFile(4, 2, 1);
        assertFalse(isAdd);
    }

    @Test
    public void getAllUsersByFile() throws Exception {
        List<String> expected = new ArrayList<>();
        List<String> result;

        result = database.getAPI().getAllUsersByFile(1,1);

        Collections.addAll(expected, "petrov", "ola");
        assertEquals(expected, result);

        result = database.getAPI().getAllUsersByFile(2,3);

        expected = new ArrayList<>();
        assertEquals(expected, result);
    }


    @Test
    public void getActualText() throws Exception {
        List<String> expected = new ArrayList<>();

        List<String> result = database.getAPI().getActualText(1, 1);

        Collections.addAll(expected, "Hello123", "Qwerty123", "123Lol!");
        assertEquals(expected, result);
    }

    @Test
    public void getCommitByDate() throws Exception {
        assertTrue(false);
    }

    @Test
    public void getAllCommitsId() throws Exception {
        List<Integer> result, expected;

        result = database.getAPI().getAllCommitsId(1,1);

        expected = new ArrayList<Integer>();
        Collections.addAll(expected, 1, 2, 3);
        assertEquals(expected, result);
    }

    @Test
    public void addCommit() throws Exception {
        List<String> text = new ArrayList<>();
        Collections.addAll(text, "Hello123", "Qwerty123", "123Lol!");

        database.getAPI().addCommit(1, 1, text);
        List<String> result = database.getAPI().getActualText(1, 1);
        assertEquals(text, result);
    }

    @Test
    public void getCommitsCount() throws Exception {
        Integer result, expected;

        result = database.getAPI().getCommitsCount(1, 1);

        expected = 5;
//        assertEquals(expected, result);

        result = database.getAPI().getCommitsCount(2, 2);

        expected = 0;
        assertEquals(expected, result);
    }

    @Test
    public void isAccessUserToFile() throws Exception {
        boolean isAccess;

        isAccess = database.getAPI().isAccessUserToFile(1, 1);
        assertTrue(isAccess);

        isAccess = database.getAPI().isAccessUserToFile(100, 1);
        assertFalse(isAccess);

        isAccess = database.getAPI().isAccessUserToFile(1, 100);
        assertFalse(isAccess);
    }
}
