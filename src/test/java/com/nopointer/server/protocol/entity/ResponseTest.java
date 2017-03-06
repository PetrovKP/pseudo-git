package com.nopointer.server.protocol.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ResponseTest {
    private Response response;

    @Before
    public void setUp() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("string1");
        list.add("string2");

        response = new Response(100, list, "Kirill", 123);
    }

    @Test
    public void getCode() throws Exception {
        assertEquals(response.getCode(), 100);
    }

    @Test
    public void getData() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("string1");
        list.add("string2");

        assertEquals(response.getData().get(0), list);
        assertEquals(response.getData().get(1), "Kirill");
        assertEquals(response.getData().get(2), 123);
    }

    @Test
    public void defaultConstructor() {
        response = new Response();
        assertEquals(response.getCode(), -1);
        assertEquals(response.getData(), null);
    }

}