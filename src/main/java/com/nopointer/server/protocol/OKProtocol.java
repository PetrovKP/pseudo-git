package com.nopointer.server.protocol;

public class OKProtocol implements Protocol {
    @Override
    public Responce handleRequest(Request request) {
        Responce responce = null;
        switch (request.getType()){
            //TODO: add logic
        }
        return responce;
    }
}
