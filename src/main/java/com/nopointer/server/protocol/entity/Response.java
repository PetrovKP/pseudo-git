package com.nopointer.server.protocol.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Response implements Serializable {
    private int code;
    private List<Object> data;

    public Response() {
        this.code = -1;
        this.data = null;
    }

    public Response(int code, Object... data) {
        this.code = code;
        this.data = new ArrayList<>();
        for (Object object : data) {
            this.data.add(object);
        }
    }

    public int getCode() {
        return code;
    }

    public List<Object> getData() {
        return data;
    }
}
