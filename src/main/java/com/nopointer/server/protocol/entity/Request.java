package com.nopointer.server.protocol.entity;

import java.io.Serializable;

public class Request implements Serializable {
    private String type;
    private Object data;

    public Request() {
        this.type = "default";
        this.data = null;
    }

    public Request(String type, Object data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public Object getData() {
        return data;
    }
}
