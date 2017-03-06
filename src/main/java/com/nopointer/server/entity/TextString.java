package com.nopointer.server.entity;

import java.io.Serializable;
import java.util.Objects;

public class TextString implements Serializable{
    private String status;
    private String string;

    public TextString()
    {
        status = "DEFAULT";
        string = "DEFAULT";
    }

    public TextString(String status, String string)
    {
        this.status = status;
        this.string = string;
    }

    public String getStatus()
    {
        return status;
    }

    public String getString()
    {
        return string;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        TextString that = (TextString)o;
        return Objects.equals(status, that.status) &&
                Objects.equals(string, that.string);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(status, string);
    }
}
