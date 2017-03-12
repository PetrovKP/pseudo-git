package com.nopointer.server.protocol;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nopointer.server.config.TestModule;
import com.nopointer.server.entity.Commit;
import com.nopointer.server.protocol.entity.Request;
import com.nopointer.server.protocol.entity.Response;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class OKProtocolTest {
    private Injector injector;
    private Protocol protocol;

    @Before
    public void setUp() {
        injector = Guice.createInjector(new TestModule());
        protocol = injector.getInstance(Protocol.class);
    }

    @Test
    public void successLogin() throws Exception {
        Request request = new Request("login", "oleg", "ovcharuk");
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 100);
    }

    @Test
    public void unsuccessLogin() throws Exception {
        Request request = new Request("login", "kirill", "petrov");
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 200);
    }

    @Test
    public void successGetIdUser() throws Exception {
        Request request = new Request("getIdUser", "log2");
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 100);
    }

    @Test
    public void unsuccessGetIdUser() throws Exception {
        Request request = new Request("getIdUser", "lo4");
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 200);
    }

    @Test
    public void successRegister() throws Exception {
        Request request = new Request("registerUser", "Kirill", "Petrov");
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 100);
    }

    @Test
    public void unsuccessRegister() throws Exception {
        Request request = new Request("registerUser", "Oleg", "Ovcharuk");
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 200);
    }

    @Test
    public void successCreateFile() throws Exception {
        int idUser = 1;
        List<String> text = new ArrayList<>();
        text.add("Hello");
        text.add("World!");

        Request request = new Request("createFile", idUser, "title", text);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 100);
    }

    @Test
    public void unsuccessCreateFile() throws Exception {
        int idUser = 3;
        List<String> text = new ArrayList<>();
        text.add("Hello");
        text.add("World!");

        Request request = new Request("createFile", idUser, "title", text);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 200);
    }

    @Test
    public void successGetCommitsCount() throws Exception {
        int idUser = 1;
        int idFile = 1;

        Request request = new Request("getCommitsCount", idUser, idFile);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 100);

        Integer count = (Integer) response.getData().get(0);
        assertEquals(count, new Integer(4));
    }

    @Test
    public void unsuccessGetCommitsCount() throws Exception {
        int idUser = 2;
        int idFile = 3;

        Request request = new Request("getCommitsCount", idUser, idFile);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 200);
    }

    @Test
    public void successGetTitle() throws Exception {
        int idUser = 1;
        int idFile = 1;
        Request request = new Request("getTitle", idUser, idFile);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 100);

        String title = (String) response.getData().get(0);
        assertEquals(title, "Title1");
    }

    @Test
    public void unsuccessGetTitle() throws Exception {
        int idUser = 2;
        int idFile = 1;
        Request request = new Request("getTitle", idUser, idFile);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 200);

        String title = (String) response.getData().get(0);
        assertNull(title);
    }

    @Test
    public void successAddUserToFile() throws Exception {
        int idUser = 1;
        int newIdUser = 3;
        int idFile = 1;
        Request request = new Request("addUserToFile", idUser, newIdUser, idFile);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 100);
    }

    @Test
    public void unsuccessAddUserToFile() throws Exception {
        int idUser = 2;
        int newIdUser = 2;
        int idFile = 1;
        Request request = new Request("addUserToFile", idUser, newIdUser, idFile);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 200);
    }

    @Test
    public void successGetAllUsersByFile() throws Exception {
        int idUser = 1;
        int idFile = 1;
        Request request = new Request("getAllUsersByFile", idUser, idFile);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 100);

        List<String> text = (List<String>) response.getData().get(0);

        List<String> expected = new ArrayList<>();
        expected.add("Petrov");
        expected.add("Ovchraruk");
        assertEquals(expected, text);
    }

    @Test
    public void unsuccessGetAllUsersByFile() throws Exception {
        int idUser = 2;
        int idFile = 1;
        Request request = new Request("getAllUsersByFile", idUser, idFile);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 200);

        List<String> text = (List<String>) response.getData().get(0);
        assertNull(text);
    }

    @Test
    public void successGetAllFilesId() throws Exception {
        int idUser = 1;

        Request request = new Request("getAllFilesId", idUser);
        Response response = protocol.handleRequest(request);

        List<Integer> list = (List<Integer>) response.getData().get(0);

        assertEquals(response.getCode(), 100);

        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        assertEquals(expected, list);
    }

    @Test
    public void unsuccessGetAllFilesId() throws Exception {
        int idUser = 2;

        Request request = new Request("getAllFilesId", idUser);
        Response response = protocol.handleRequest(request);

        List<Integer> list = (List<Integer>) response.getData().get(0);

        assertEquals(response.getCode(), 200);

        assertNull(list);
    }

    @Test
    public void successGetActualText() throws Exception {
        int idUser = 2;
        int idFile = 1;

        Request request = new Request("getActualText", idUser, idFile);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 100);

        List<String> text = (List<String>) response.getData().get(0);

        List<String> expected = new ArrayList<>();
        expected.add("Text");
        expected.add("Here");
        assertEquals(expected, text);
    }

    @Test
    public void unsuccessGetActualText() throws Exception {
        int idUser = 2;
        int idFile = 2;

        Request request = new Request("getActualText", idUser, idFile);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 200);

        List<String> text = (List<String>) response.getData().get(0);
        assertNull(text);
    }

    @Test
    public void successAddCommit() throws Exception {
        int idUser = 1;
        int idFile = 1;
        List<String> text = new ArrayList<>();
        text.add("Hello");
        text.add("World!");

        Request request = new Request("addCommit", idUser, idFile, text);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 100);
    }

    @Test
    public void unsuccessAddCommit() throws Exception {
        int idUser = 2;
        int idFile = 3;
        List<String> text = new ArrayList<>();
        text.add("Hello");
        text.add("World!");

        Request request = new Request("addCommit", idUser, idFile, text);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 200);
    }

    @Test
    public void successGetAllCommitsId() throws Exception {
        int idUser = 1;
        int idFile = 3;

        Request request = new Request("getAllCommitsId", idUser, idFile);
        Response response = protocol.handleRequest(request);

        List<Integer> list = (List<Integer>) response.getData().get(0);

        assertEquals(response.getCode(), 100);

        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        assertEquals(expected, list);
    }

    @Test
    public void unsuccessGetAllCommitsId() throws Exception {
        int idUser = 2;
        int idFile = 3;

        Request request = new Request("getAllCommitsId", idUser, idFile);
        Response response = protocol.handleRequest(request);

        List<Integer> list = (List<Integer>) response.getData().get(0);

        assertEquals(response.getCode(), 200);

        assertNull(list);
    }

    @Test
    public void successGetCommitDateById() throws Exception {
        int idUser = 1;
        int idFile = 1;
        int idCommit = 1;
        Request request = new Request("getCommitDateById", idUser, idFile, idCommit);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 100);

        String data = (String) response.getData().get(0);
        assertEquals(data, "03.11.2017 - 22:12");
    }

    @Test
    public void unsuccessGetCommitDateById() throws Exception {
        int idUser = 1;
        int idFile = 2;
        int idCommit = 3;
        Request request = new Request("getCommitDateById", idUser, idFile, idCommit);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 200);

        String data = (String) response.getData().get(0);
        assertNull(data);
    }

    @Test
    public void successGetCommitById() throws Exception {
        int idUser = 1;
        int idFile = 1;
        int idCommit = 1;
        Request request = new Request("getCommitById", idUser, idFile, idCommit);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 100);

        Commit commit = (Commit) response.getData().get(0);
        List<String> oldText = new ArrayList<>();
        List<String> newText = new ArrayList<>();
        oldText.add("Java");
        oldText.add("Not");
        newText.add("Java");
        newText.add("Yes");
        Commit expected = new Commit(oldText, newText);
        assertTrue(expected.equals(expected));
    }

    @Test
    public void unsuccessGetCommitById() throws Exception {
        int idUser = 1;
        int idFile = 2;
        int idCommit = 3;
        Request request = new Request("getCommitById", idUser, idFile, idCommit);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 200);

        Commit commit = (Commit) response.getData().get(0);
        assertNull(commit);
    }

    @Test
    public void successRevertFileToCommit() throws Exception {
        int idUser = 1;
        int idFile = 1;
        int idCommit = 1;
        Request request = new Request("revertFileToCommit", idUser, idFile, idCommit);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 100);
    }

    @Test
    public void unsuccessRevertFileToCommit() throws Exception {
        int idUser = 1;
        int idFile = 2;
        int idCommit = 3;
        Request request = new Request("revertFileToCommit", idUser, idFile, idCommit);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 200);
    }

}