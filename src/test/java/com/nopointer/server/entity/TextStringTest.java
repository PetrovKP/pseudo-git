package com.nopointer.server.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class TextStringTest
{
    @Test
    public void getStatusAndString() throws Exception
    {
        TextString ts = new TextString("This is status", "This is string");

        String status = ts.getStatus();
        String string = ts.getString();

        assertEquals(status, "This is status");
        assertEquals(string, "This is string");
    }

    @Test
    public void canCompareEqualTextStrings() throws Exception
    {
        TextString ts1 = new TextString("Status", "Text");
        TextString ts2 = new TextString("Status", "Text");

        assertTrue(ts1.equals(ts2));
        assertTrue(ts2.equals(ts1));
    }

    @Test
    public void canCompareNotEqualTextStrings() throws Exception{
        TextString ts1 = new TextString("Status1", "Text1");
        TextString ts2 = new TextString("Status2", "Text2");

        assertFalse(ts1.equals(ts2));
        assertFalse(ts2.equals(ts1));
    }
}