package com.nopointer.server.protocol;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nopointer.server.config.TestModule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProtocolTest {
    Injector injector;
    Protocol protocol;

    @Before
    public void setUp(){
        injector = Guice.createInjector(new TestModule());
        protocol = injector.getInstance(Protocol.class);

    }

    @Test
    public void handleRequest() throws Exception {
        Request request = new Request("RETURN ME!", new String("qwe"));
        Responce responce = protocol.handleRequest(request);
        assertEquals(responce.getData(), request.getData());
    }

}