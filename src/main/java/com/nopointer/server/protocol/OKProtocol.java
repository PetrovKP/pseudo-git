package com.nopointer.server.protocol;

import com.google.inject.Inject;
import com.nopointer.server.controller.Controller;
import com.nopointer.server.protocol.entity.Request;
import com.nopointer.server.protocol.entity.Response;

import java.util.List;

class OKProtocol implements Protocol {
    private Controller controller;

    @Inject
    public OKProtocol(Controller controller) {
        this.controller = controller;
    }

    // Тут никаких изменений быть уже не должно, вся ответственность будет лежать
    // на поведении контроллера и бд
    @Override
    public Response handleRequest(Request request) {
        Response response = null;
        switch (request.getType()) {
            case "login":
                List<String> auth = (List<String>) request.getData().get(0);
                int code = controller.login(auth.get(0), auth.get(1));
                response = new Response(code, null);
                break;
            case "registerUser":
                // TODO: realisation
                break;
            case "deleteUser":
                // TODO: realisation
                break;
            case "createFile":
                // TODO: realisation
                break;
            case "getTitle":
                // TODO: realisation
                break;
            case "getFileStatus":
                // TODO: realisation
                break;
            case "changeFileStatus":
                // TODO: realisation
                break;
            case "getAllFilesId":
                // TODO: realisation
                break;
            case "getActualText":
                // TODO: realisation
                break;
            case "getCommitByDate":
                // TODO: realisation
                break;
            case "getAllCommitsId":
                // TODO: realisation
                break;
            case "addCommit":
                // TODO: realisation
                break;
        }
        return response;
    }
}
