package com.nopointer.server.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class TextStringTest
{
    @Test
    public void getStatusAndString() throws Exception
    {
        TextString ts = new TextString(1, "This is status", "This is string");

        int number = ts.getNumber();
        String status = ts.getStatus();
        String string = ts.getString();

        assertEquals(number, 1);
        assertEquals(status, "This is status");
        assertEquals(string, "This is string");
    }

    @Test
    public void canCompareEqualTextStrings() throws Exception
    {
        TextString ts1 = new TextString(1, "Status", "Text");
        TextString ts2 = new TextString(1, "Status", "Text");

        assertTrue(ts1.equals(ts2));
        assertTrue(ts2.equals(ts1));
    }

    @Test
    public void canCompareNotEqualTextStrings() throws Exception{
        TextString ts1 = new TextString(1, "Status1", "Text1");
        TextString ts2 = new TextString(2, "Status2", "Text2");

        assertFalse(ts1.equals(ts2));
        assertFalse(ts2.equals(ts1));
    }
}