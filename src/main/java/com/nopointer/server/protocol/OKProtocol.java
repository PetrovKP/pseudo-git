package com.nopointer.server.protocol;

import com.google.inject.Inject;
import com.nopointer.server.controller.Controller;
import com.nopointer.server.protocol.entity.Code;
import com.nopointer.server.protocol.entity.Request;
import com.nopointer.server.protocol.entity.Response;

import java.util.List;

class OKProtocol implements Protocol {
    private Controller controller;

    @Inject
    public OKProtocol(Controller controller) {
        this.controller = controller;
    }

    @Override
    public Response handleRequest(Request request) {
        Response response = null;
        switch (request.getType()) {
            case "login":
                List<String> list = (List<String>) request.getData();
                Code code = controller.registerUser(list.get(0), list.get(1));
                response = new Response(code);
                break;
            // TODO: and so on.
        }
        return response;
    }
}
