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

        /* Не совсем корректный, но очень информативный пример. Тут мы подменяем Protocol
        * на "заглушку" OKProtocolStub, в которой втупую задано какое-то поведение.
        * Пример этот не совсем корректный потому что "заглушки" тестировать глупо, но
        * вот если бы нам пришлось тестировать класс, зависящий напрямую от Protocol -
        * тут бы нам это и пригодилось. Надеюсь, ты все понял.
        * TODO: удалить после прочтения
        */

        assertEquals(responce.getData(), request.getData());
    }

}