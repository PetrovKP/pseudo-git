package com.nopointer.server.protocol;

import com.nopointer.server.protocol.entity.Request;
import com.nopointer.server.protocol.entity.Response;

class OKProtocolStub implements Protocol {
    @Override
    public Response handleRequest(Request request) {
        Response response = null;
        if (request.getType().equals("RETURN ME!")){
            response = new Response(100, request.getData());
        }
        return response;
    }
}
