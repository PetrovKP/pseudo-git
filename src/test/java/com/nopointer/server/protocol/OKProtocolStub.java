package com.nopointer.server.protocol;

class OKProtocolStub implements Protocol {
    @Override
    public Responce handleRequest(Request request) {
        Responce responce = null;
        if (request.getType().equals("RETURN ME!")){
            responce = new Responce(request.getData());
        }
        return responce;
    }
}
