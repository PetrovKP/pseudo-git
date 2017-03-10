package com.nopointer.server.protocol.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RequestTest {
    private Request request;

    @Before
    public void setUp() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("string1");
        list.add("string2");

        request = new Request("TestRequest", list, "VGV", 111);
    }

    @Test
    public void getType() throws Exception {
        assertEquals(request.getType(), "TestRequest");
    }

    @Test
    public void getData() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("string1");
        list.add("string2");

        assertEquals(request.getData().get(0), list);
        assertEquals(request.getData().get(1), "VGV");
        assertEquals(request.getData().get(2), 111);
    }

    @Test
    public void defaultConstructor() {
        request = new Request();
        assertEquals(request.getType(), "default");
        assertEquals(request.getData(), null);
    }

}