package com.nopointer.server.protocol;

import java.io.Serializable;

public class Responce implements Serializable {
    private Object data;

    public Responce(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
