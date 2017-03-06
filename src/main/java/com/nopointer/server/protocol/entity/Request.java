package com.nopointer.server.protocol.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Request implements Serializable {
    private String type;
    private List<Object> data;

    public Request() {
        this.type = "default";
        this.data = null;
    }

    public Request(String type, Object... data) {
        this.type = type;
        this.data = new ArrayList<>();
        for (Object object : data) {
            this.data.add(object);
        }
    }

    public String getType() {
        return type;
    }

    public List<Object> getData() {
        return data;
    }
}
