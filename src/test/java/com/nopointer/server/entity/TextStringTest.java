package com.nopointer.server.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class TextStringTest {
    @Test
    public void canCreateTextString() throws Exception {
        TextString ts;

        ts = new TextString();
        assertNotNull(ts);

        ts = new TextString(1, "This is status", "This is string");
        assertNotNull(ts);
    }

    @Test
    public void getStatusAndString() throws Exception {
        TextString ts = new TextString(1, "This is status", "This is string");

        int number = ts.getNumber();
        String status = ts.getStatus();
        String string = ts.getString();

        assertEquals(number, 1);
        assertEquals(status, "This is status");
        assertEquals(string, "This is string");
    }

    @Test
    public void canCompareEqualTextStrings() throws Exception {
        TextString ts1 = new TextString(1, "Status", "Text");
        TextString ts2 = new TextString(1, "Status", "Text");

        assertTrue(ts1.equals(ts2));
        assertTrue(ts2.equals(ts1));
        assertTrue(ts1.equals(ts1));
    }

    @Test
    public void canCompareNotEqualTextStrings() throws Exception {
        TextString ts1 = new TextString(1, "Status1", "Text1");
        TextString ts2 = new TextString(2, "Status2", "Text2");

        assertFalse(ts1.equals(ts2));
        assertFalse(ts2.equals(ts1));
        assertFalse(ts2.equals(null));
        assertFalse(ts2.equals(new Integer(3)));

    }

    @Test
    public void canGetHashCode() throws Exception {
        TextString ts1 = new TextString(1, "This is status", "This is string");

        TextString ts2 = new TextString(1, "This is status", "This is string");

        assertEquals(ts1.hashCode(), ts2.hashCode());
    }
}