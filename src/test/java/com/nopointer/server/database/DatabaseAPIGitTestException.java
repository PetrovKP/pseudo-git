package com.nopointer.server.database;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;
import org.junit.*;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class DatabaseAPIGitTestException {
    private static Database database;
    @BeforeClass
    public static void setUp() throws Exception {
        Injector injector = Guice.createInjector(new TestDatabaseModule());
        database = injector.getInstance(Database.class);
        database.connect();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        database.disconnect();
    }

    @Ignore
    @Test(expected = SQLException.class)
    public void registerUser() throws SQLException {
        database.getAPI().registerUser("23233232322323", "4344RFWEF34FE34");

    }

    @Ignore
    @Test(expected = SQLException.class)
    public void deleteUser() throws SQLException {
        database.getAPI().deleteUser("3232323");
    }

    @Ignore
    @Test(expected = SQLException.class)
    public void login() throws SQLException {
        database.getAPI().login("1,./+-=!@&^&*@#2323!#", "123");
    }

    @Test
    public void getIdUser() throws Exception {
        database.getAPI().getIdUser("ewf4r3t4t34223r21");
    }

    @Test(expected = SQLException.class)
    public void createFile() throws Exception {
        database.getAPI().createFile(23,"123457890123", null);
    }

    @Ignore
    @Test(expected = SQLException.class)
    public void deleteToFile() throws Exception {
        database.getAPI().deleteToFile(23,43);
    }

    @Ignore
    @Test(expected = SQLException.class)
    public void getCommitsCount() throws Exception {
        database.getAPI().getCommitsCount(1,23);
    }

    @Ignore
    @Test(expected = SQLException.class)
    public void getTitle() throws Exception {
        database.getAPI().getTitle(2323,343434);

    }

    @Ignore
    @Test(expected = SQLException.class)
    public void getAllFilesId() throws Exception {
        database.getAPI().getAllFilesId(232332332);

    }

    @Ignore
    @Test(expected = SQLException.class)
    public void getActualText() throws Exception {
        database.getAPI().getActualText(2332,3443);

    }

    @Ignore
    @Test(expected = SQLException.class)
    public void getAllCommitsId() throws Exception {
        database.getAPI().getAllCommitsId(2232324,433322);

    }

    @Ignore
    @Test(expected = SQLException.class)
    public void isAccessUserToFile() throws Exception {
        database.getAPI().isAccessUserToFile(999999999,99999999);

    }

    @Ignore
    @Test(expected = SQLException.class)
    public void getAllUsersByFile() throws Exception {
        database.getAPI().getAllUsersByFile(242424321,2);

    }

    @Test(expected = SQLException.class)
    public void addUserToFile() throws Exception {
        database.getAPI().addUserToFile(2,43,2);

    }

    @Ignore
    @Test(expected = SQLException.class)
    public void deleteUserToFile() throws Exception {
        database.getAPI().deleteUserToFile(2,434,2);
    }

    @Ignore
    @Test(expected = SQLException.class)
    public void addCommit() throws Exception {
        database.getAPI().addCommit(1, 3, null);
    }

    @Ignore
    @Test(expected = SQLException.class)
    public void deleteCommit() throws Exception {
        database.getAPI().deleteCommit(2,2, 545);

    }

    @Ignore
    @Test(expected = SQLException.class)
    public void getCommitDateById() throws Exception {
        database.getAPI().getCommitDateById(2,2,34);
    }

    @Ignore
    @Test(expected = SQLException.class)
    public void getCommitById() throws Exception {
        database.getAPI().getCommitById(2,2,34);

    }

    @Ignore
    @Test(expected = SQLException.class)
    public void revertFileToCommit() throws Exception {
        database.getAPI().revertFileToCommit(2,2,345);

    }

}