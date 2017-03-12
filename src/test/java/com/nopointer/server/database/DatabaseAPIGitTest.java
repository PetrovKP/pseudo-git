package com.nopointer.server.database;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nopointer.server.entity.Commit;
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
        Injector injector = Guice.createInjector(new TestDatabaseModule());
        database = injector.getInstance(Database.class);

        database.connect();
    }

    @Test
    public void registerUser() throws Exception {
        boolean isEnter, isDel;
        database.getAPI().registerUser("ADD", "123");
        isEnter = database.getAPI().login("ADD", "123");
        assertTrue(isEnter);

        database.getAPI().registerUser("ADD", "145");
        isEnter = database.getAPI().login("ADD", "123");
        assertTrue(isEnter);

        isDel = database.getAPI().deleteUser("ADD");
        assertTrue(isDel);

        isDel = database.getAPI().deleteUser("ADD");
        assertFalse(isDel);
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
        int idFile;
        boolean isDelete;
        List<String> text = new ArrayList<>();
        Collections.addAll(text, "Petrov", "love", "is", "Java!");

        idFile = database.getAPI().createFile(1,"title_petrov", text);
        assertTrue(idFile != 0);

        String title = database.getAPI().getTitle(1, idFile);
        assertEquals("title_petrov", title);

        isDelete = database.getAPI().deleteToFile(1, idFile);
        assertTrue(isDelete);

        isDelete = database.getAPI().deleteToFile(1, idFile);
        assertFalse(isDelete);
    }

    @Test
    public void getTitle() throws Exception {
        String result, expected;

        result = database.getAPI().getTitle(1, 2);

        expected = "test2";
        assertEquals(result, expected);

        result = database.getAPI().getTitle(1, 100);
        assertNull(result);
    }

    @Test
    public void getAllFilesId() throws Exception {
        List<Integer> result, expected;

        result = database.getAPI().getAllFilesId(1);

        expected = new ArrayList<Integer>();
        Collections.addAll(expected, 2, 3);
        assertEquals(expected, result);
    }

    @Test
    public void addUserToFile() throws Exception {
        boolean isAdd, isDelete;

        isAdd = database.getAPI().addUserToFile(3, 1, 1);
        assertTrue(isAdd);

        isDelete = database.getAPI().deleteUserToFile(3,1,1);
        assertTrue(isDelete);

        isDelete = database.getAPI().deleteUserToFile(3,1,1);
        assertFalse(isDelete);

        isAdd = database.getAPI().addUserToFile(1, 2, 2);
        assertFalse(isAdd);

        isAdd = database.getAPI().addUserToFile(4, 2, 1);
        assertFalse(isAdd);

    }

    @Test
    public void getAllUsersByFile() throws Exception {
        List<String> expected = new ArrayList<>();
        List<String> result;

        result = database.getAPI().getAllUsersByFile(1,2);

        Collections.addAll(expected, "petrov", "ovcharul");
        assertEquals(expected, result);

        result = database.getAPI().getAllUsersByFile(2,3);

        expected = new ArrayList<>();
        assertEquals(expected, result);
    }

    @Test
    public void getActualText() throws Exception {
        List<String> expected = new ArrayList<>();
        List<String> result;

        result = database.getAPI().getActualText(1, 2);

        Collections.addAll(expected, "JAVA", "MYSQL");
        assertEquals(expected, result);

        result = database.getAPI().getActualText(3, 2);
        assertNull(result);

    }

    @Test
    public void getCommitByDate() throws Exception {
        String result, expected;

        result = database.getAPI().getCommitDateById(3,1, 1);

        expected = "03.11.2017 - 22:12";
        assertEquals(expected, result);

        result = database.getAPI().getCommitDateById(1,3, 1);

        expected = "";
        assertEquals(expected, result);
    }


    @Test
    public void getAllCommitsId() throws Exception {
        List<Integer> result, expected;

        result = database.getAPI().getAllCommitsId(1,2);

        expected = new ArrayList<Integer>();
        Collections.addAll(expected, 1);
        assertEquals(expected, result);

        result = database.getAPI().getAllCommitsId(3,2);
        assertNull(result);
    }

    @Test
    public void addCommit() throws Exception {
        List<String> text = new ArrayList<>();
        boolean isAdd, isDel;
        Collections.addAll(text, "Hello123", "Qwerty123", "123Lol!");

        isAdd = database.getAPI().addCommit(1, 2, text);

        assertTrue(isAdd);

        List<String> result = database.getAPI().getActualText(1, 2);
        assertEquals(text, result);

        isDel = database.getAPI().deleteCommit(1, 2, 2);
        assertTrue(isDel);

        isDel = database.getAPI().deleteCommit(1, 1, 2);
        assertFalse(isDel);
    }

    @Test
    public void getCommitsCount() throws Exception {
        Integer result, expected;

        result = database.getAPI().getCommitsCount(1, 3);

        expected = 2;
        assertEquals(expected, result);

        result = database.getAPI().getCommitsCount(1, 1);

        expected = 0;
        assertEquals(expected, result);
    }

    @Test
    public void getCommitById() throws Exception {
        Commit expected, result;
        List<String> oldText = new ArrayList<>();
        List<String> newText = new ArrayList<>();

        result = database.getAPI().getCommitById(1, 3,2);

        Collections.addAll(oldText, "Hello", "World!");
        Collections.addAll(newText, "Bye", "World!");
        expected = new Commit(oldText, newText);
        assertTrue(expected.equals(result));

        result = database.getAPI().getCommitById(3, 2,2);
        assertNull(result);

        result = database.getAPI().getCommitById(1, 3,1 );

        oldText = new ArrayList<>();
        newText = new ArrayList<>();

        Collections.addAll(newText, "Hello", "World!");
        expected = new Commit(oldText, newText);
        assertTrue(expected.equals(result));
    }

    @Test
    public void revertFileToCommit() throws Exception {
        List<String> text = new ArrayList<>();
        boolean isAdd, isDel;
        text.add("TEST");

        isAdd = database.getAPI().addCommit(1, 3, text);

        // Успешное добавление
        assertTrue(isAdd);

        isAdd = database.getAPI().addCommit(1, 3, text);

        // Успешное добавление
        assertTrue(isAdd);

        isDel = database.getAPI().revertFileToCommit(1, 3, 2);

        // Успешное удаление коммитов
        assertTrue(isDel);

        List<String> result = database.getAPI().getActualText(1, 3);

        List<String> expected = new ArrayList<>();
        Collections.addAll(expected, "Bye", "World!");
        // Проверка актульного текста
        assertEquals(expected, result);

        isDel = database.getAPI().revertFileToCommit(1, 3, 2);

        // Нельзя удалить коммиты, если больше нет
        assertFalse(isDel);

        isDel = database.getAPI().revertFileToCommit(1, 1, 2);

        // Нельзя удалить, если нет прав
        assertFalse(isDel);

    }

    @Test
    public void isAccessUserToFile() throws Exception {
        boolean isAccess;

        isAccess = database.getAPI().isAccessUserToFile(1, 2);
        assertTrue(isAccess);

        isAccess = database.getAPI().isAccessUserToFile(100, 1);
        assertFalse(isAccess);

        isAccess = database.getAPI().isAccessUserToFile(1, 100);
        assertFalse(isAccess);
    }
}
