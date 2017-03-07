package com.nopointer.server.protocol;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nopointer.server.config.TestModule;
import com.nopointer.server.protocol.entity.Request;
import com.nopointer.server.protocol.entity.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class OKProtocolTest
{
    private Injector injector;
    private Protocol protocol;

    @Before
    public void setUp(){
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
    public void successRegister() throws Exception{
        List<String> auth = new ArrayList<>();
        auth.add("Kirill");
        auth.add("Petrov");

        Request request = new Request("registerUser", auth);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 100);
    }

    @Test
    public void unsuccessRegister() throws Exception{
        List<String> auth = new ArrayList<>();
        auth.add("Oleg");
        auth.add("Ovcharuk");

        Request request = new Request("registerUser", auth);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 200);
    }

    @Test
    public void successDeleteUser() throws Exception{
        List<String> auth = new ArrayList<>();
        auth.add("Oleg");
        auth.add("Ovcharuk");

        Request request = new Request("deleteUser", auth);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 100);
    }

    @Test
    public void unsuccessDeleteUser() throws Exception{
        List<String> auth = new ArrayList<>();
        auth.add("Kirill");
        auth.add("Petrov");

        Request request = new Request("deleteUser", auth);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 200);
    }

    @Test
    public void successCreateFile() throws Exception{
        List<String> data = new ArrayList<>();
        data.add("New file");

        Request request = new Request("createFile", data);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 100);
    }

    @Test
    public void unsuccessCreateFile() throws Exception{
        List<String> data = new ArrayList<>();
        data.add("FIRST FILE");

        Request request = new Request("createFile", data);
        Response response = protocol.handleRequest(request);

        assertEquals(response.getCode(), 200);
    }

    @Test
    public void getTitle() throws Exception{
        int idFile = 1;

        Request request = new Request("getTitle", idFile);
        Response response = protocol.handleRequest(request);

        String title = (String)response.getData().get(0);

        assertEquals(title, "Title1");
    }

    @Test
    public void getFileStatus() throws Exception {
        int idFile = 1;

        Request request = new Request("getFileStatus", idFile);
        Response response = protocol.handleRequest(request);

        String status = (String)response.getData().get(0);

        assertEquals(status, "locked");
    }


}