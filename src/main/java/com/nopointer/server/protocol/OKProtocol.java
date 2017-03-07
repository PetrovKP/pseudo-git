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
        int code;
        Response response = null;
        switch (request.getType()) {
            case "login":
                List<String> auth = (List<String>) request.getData().get(0);
                code = controller.login(auth.get(0), auth.get(1));
                response = new Response(code);
                break;
            case "registerUser":
                List<String> newUser = (List<String>)request.getData().get(0);
                code = controller.registerUser(newUser.get(0), newUser.get(1));
                response = new Response(code);
                break;
            case "deleteUser":
                List<String> deleteUser = (List<String>)request.getData().get(0);
                code = controller.deleteUser(deleteUser.get(0));
                response = new Response(code);
                break;
            case "createFile":
                String title = (String)request.getData().get(1);
                code = controller.createFile(title);
                response = new Response(code);
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
