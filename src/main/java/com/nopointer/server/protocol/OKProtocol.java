package com.nopointer.server.protocol;

import com.google.inject.Inject;
import com.nopointer.server.controller.Controller;
import com.nopointer.server.entity.Commit;
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
        int code, idFile;
        List<String> auth;
        Response response = null;
        switch (request.getType()) {
            case "login":
                auth = (List<String>) request.getData().get(0);
                code = controller.login(auth.get(0), auth.get(1));
                response = new Response(code);
                break;

            case "registerUser":
                auth = (List<String>) request.getData().get(0);
                code = controller.registerUser(auth.get(0), auth.get(1));
                response = new Response(code);
                break;

            case "deleteUser":
                auth = (List<String>) request.getData().get(0);
                code = controller.deleteUser(auth.get(0));
                response = new Response(code);
                break;

            case "createFile":
                String title = ((List<String>) request.getData().get(0)).get(1);
                code = controller.createFile(title);
                response = new Response(code);
                break;

            case "getTitle":
                idFile = (int) request.getData().get(0);
                String titleFile = controller.getTitle(idFile);
                code = (titleFile != null) ? 100 : 200;
                response = new Response(code, titleFile);
                break;

            case "getFileStatus":
                idFile = (int) request.getData().get(0);
                String statusFile = controller.getFileStatus(idFile);
                code = (statusFile != null) ? 100 : 200;
                response = new Response(code, statusFile);
                break;

            case "changeFileStatus":
                idFile = (int) request.getData().get(0);
                code = controller.changeFileStatus(idFile);
                response = new Response(code);
                break;

            case "getAllFilesId":
                List<String> filesOwner = (List<String>) request.getData().get(0);
                List<Integer> filesIds = controller.getAllFilesId(filesOwner.get(0));
                code = (filesIds != null) ? 100 : 200;
                response = new Response(code, filesIds);
                break;

            case "getActualText":
                idFile = (int) request.getData().get(0);
                List<String> actualText = controller.getActualText(idFile);
                code = (actualText != null) ? 100 : 200;
                response = new Response(code, actualText);
                break;

            case "getCommitByDate":
                idFile = (int) request.getData().get(0);
                //String date = (String)request.getData().get(1);
                Commit commitByDate = controller.getCommitByDate(idFile, /*date*/null);
                response = new Response(100, commitByDate);
                break;

            case "getAllCommitsId":
                idFile = (int) request.getData().get(0);
                List<Integer> allCommitsId = controller.getAllCommitsId(idFile);
                response = new Response(100, allCommitsId);
                break;

            case "addCommit":
                idFile = (int) request.getData().get(0);
                auth = (List<String>) request.getData().get(1);
                List<String> textCommit = (List<String>) request.getData().get(2);
                code = controller.addCommit(idFile, auth.get(0), textCommit);
                response = new Response(code);
                break;
        }
        return response;
    }
}
