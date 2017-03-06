package com.nopointer.server.entity;

import java.io.Serializable;

public class TextString implements Serializable {
    private int number;
    private String status;
    private String string;

    public TextString() {
        number = -1;
        status = "DEFAULT";
        string = "DEFAULT";
    }

    public TextString(int number, String status, String string) {
        this.status = status;
        this.number = number;
        this.string = string;
    }

    public int getNumber() {
        return number;
    }

    public String getStatus() {
        return status;
    }

    public String getString() {
        return string;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextString that = (TextString) o;

        if (number != that.number) return false;
        if (!status.equals(that.status)) return false;
        return string.equals(that.string);
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + status.hashCode();
        result = 31 * result + string.hashCode();
        return result;
    }
}
