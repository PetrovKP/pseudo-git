package com.nopointer.server.protocol.entity;

import java.io.Serializable;

public class Response implements Serializable {
    private int code;
    private Object data;

    public Response() {
        this.code = -1;
        this.data = null;
    }

    public Response(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }
}
