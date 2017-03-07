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
        List<String> auth = new ArrayList<>();
        auth.add("oleg");
        auth.add("ovcharuk");

        Request request = new Request("login", auth);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 100);
    }

    @Test
    public void unsuccessLogin() throws Exception {
        List<String> auth = new ArrayList<>();
        auth.add("kirill");
        auth.add("petrov");

        Request request = new Request("login", auth);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 200);
    }

    @Test
    public void successRegister() throws Exception {
        List<String> auth = new ArrayList<>();
        auth.add("Kirill");
        auth.add("Petrov");

        Request request = new Request("registerUser", auth);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 100);
    }

    @Test
    public void unsuccessRegister() throws Exception {
        List<String> auth = new ArrayList<>();
        auth.add("Oleg");
        auth.add("Ovcharuk");

        Request request = new Request("registerUser", auth);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 200);
    }

    @Ignore
    @Test
    public void successDeleteUser() throws Exception {
        List<String> auth = new ArrayList<>();
        auth.add("Oleg");
        auth.add("Ovcharuk");

        Request request = new Request("deleteUser", auth);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 100);
    }

    @Ignore
    @Test
    public void unsuccessDeleteUser() throws Exception {
        List<String> auth = new ArrayList<>();
        auth.add("Kirill");
        auth.add("Petrov");

        Request request = new Request("deleteUser", auth);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 200);
    }

    @Test
    public void successCreateFile() throws Exception {
        List<String> data = new ArrayList<>();
        data.add("login");
        data.add("New file");

        Request request = new Request("createFile", data);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 100);
    }

    @Test
    public void unsuccessCreateFile() throws Exception {
        List<String> data = new ArrayList<>();
        data.add("login");
        data.add("FIRST FILE");

        Request request = new Request("createFile", data);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 200);
    }

    @Test
    public void getTitle() throws Exception {
        int idFile = 1;

        Request request = new Request("getTitle", idFile);
        Response response = protocol.handleRequest(request);

        String title = (String) response.getData().get(0);

        assertEquals(title, "Title1");
    }

    @Test
    public void getFileStatus() throws Exception {
        int idFile = 1;

        Request request = new Request("getFileStatus", idFile);
        Response response = protocol.handleRequest(request);

        String status = (String) response.getData().get(0);

        assertEquals(status, "locked");
    }

    @Test
    public void successChangeFileStatus() throws Exception {
        int idFile = 2;

        Request request = new Request("changeFileStatus", idFile);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 100);
    }

    @Test
    public void unsuccessChangeFileStatus() throws Exception {
        int idFile = 1;

        Request request = new Request("changeFileStatus", idFile);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 200);
    }

    @Test
    public void getAllFilesId() throws Exception {
        List<String> auth = new ArrayList<>();
        auth.add("Oleg");

        Request request = new Request("getAllFilesId", auth);
        Response response = protocol.handleRequest(request);

        List<Integer> list = (List<Integer>) response.getData().get(0);

        assertEquals((int) list.get(0), 1);
        assertEquals((int) list.get(1), 2);
    }

    @Test
    public void getActualText() throws Exception {
        int idFile = 1;

        Request request = new Request("getActualText", idFile);
        Response response = protocol.handleRequest(request);

        List<String> text = (List<String>) response.getData().get(0);

        assertEquals(text.get(0), "Text");
        assertEquals(text.get(1), "Here");
    }

    @Test
    public void getCommitByDate() throws Exception {
        int idFile = 1;

        List<String> list1 = new ArrayList<>();
        list1.add("Text");
        list1.add("Here");
        List<String> list2 = new ArrayList<>();
        list2.add("Text1");
        list2.add("Here1");
        Commit commit1 = new Commit(list1, list2);

        Request request = new Request("getCommitByDate", idFile);
        Response response = protocol.handleRequest(request);

        Commit commit2 = (Commit) response.getData().get(0);

        assertTrue(commit1.equals(commit2));
    }

    @Test
    public void getAllCommitsId() throws Exception {
        int idFile = 3;

        Request request = new Request("getAllCommitsId", idFile);
        Response response = protocol.handleRequest(request);

        List<Integer> list = (List<Integer>) response.getData().get(0);

        assertEquals((int) list.get(0), 1);
        assertEquals((int) list.get(1), 2);
    }

    @Test
    public void successAddCommit() throws Exception {
        int idFile = 1;
        List<String> auth = new ArrayList<>();
        auth.add("Oleg");
        auth.add("Ovcharuk");

        Request request = new Request("addCommit", idFile, auth, null);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 100);
    }

    @Test
    public void unsuccessAddCommit() throws Exception {
        int idFile = 2;
        List<String> auth = new ArrayList<>();
        auth.add("Oleg");
        auth.add("Ovcharuk");

        Request request = new Request("addCommit", idFile, auth, null);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 200);
    }

}