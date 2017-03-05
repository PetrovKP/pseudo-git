package com.nopointer.server.protocol.entity;

import java.io.Serializable;

public class Code implements Serializable {
    private String code;

    public Code() {
        code = "DEFAULT";
    }

    public Code(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
