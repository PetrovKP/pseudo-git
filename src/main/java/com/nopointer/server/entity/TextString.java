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
}
