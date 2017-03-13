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

    @Override
    public Response handleRequest(Request request) {
        int code, idFile, idUser, idCommit;
        String login, password;
        List<String> text;
        Response response = null;
        switch (request.getType()) {
            case "registerUser":
                login = (String) request.getData().get(0);
                password = (String) request.getData().get(1);
                idUser = controller.registerUser(login, password);
                response = (idUser != 0) ? new Response(100, idUser) :
                        new Response(200, null);
                break;

            case "login":
                login = (String) request.getData().get(0);
                password = (String) request.getData().get(1);
                code = controller.login(login, password);
                response = new Response(code);
                break;

            case "getIdUser":
                login = (String) request.getData().get(0);
                idUser = controller.getIdUser(login);
                response = (idUser != 0) ? new Response(100, idUser) :
                        new Response(200, null);
                break;

            case "createFile":
                idUser = (int) request.getData().get(0);
                String title = (String) request.getData().get(1);
                text = (List<String>) request.getData().get(2);
                idFile = controller.createFile(idUser, title, text);
                response = (idFile != 0) ? new Response(100, idFile) :
                        new Response(200, null);
                break;

            case "getTitle":
                idUser = (int) request.getData().get(0);
                idFile = (int) request.getData().get(1);
                String fileTitle = controller.getTitle(idUser, idFile);
                code = (fileTitle != null) ? 100 : 200;
                response = new Response(code, fileTitle);
                break;

            case "getCommitsCount":
                idUser = (int) request.getData().get(0);
                idFile = (int) request.getData().get(1);
                int count = controller.getCommitsCount(idUser, idFile);
                response = (count != 0) ? new Response(100, count) :
                        new Response(200, null);
                break;

            case "getAllFilesId":
                idUser = (int) request.getData().get(0);
                List<Integer> filesIds = controller.getAllFilesId(idUser);
                code = (filesIds != null) ? 100 : 200;
                response = new Response(code, filesIds);
                break;

            case "getActualText":
                idUser = (int) request.getData().get(0);
                idFile = (int) request.getData().get(1);
                List<String> actualText = controller.getActualText(idUser, idFile);
                code = (actualText != null) ? 100 : 200;
                response = new Response(code, actualText);
                break;

            case "getAllCommitsId":
                idUser = (int) request.getData().get(0);
                idFile = (int) request.getData().get(1);
                List<Integer> allCommitsId = controller.getAllCommitsId(idUser, idFile);
                code = (allCommitsId != null) ? 100 : 200;
                response = new Response(code, allCommitsId);
                break;

            case "getAllUsersByFile":
                idUser = (int) request.getData().get(0);
                idFile = (int) request.getData().get(1);
                List<String> allUsersByFile = controller.getAllUsersByFile(idUser, idFile);
                code = (allUsersByFile != null) ? 100 : 200;
                response = new Response(code, allUsersByFile);
                break;

            case "addUserToFile":
                idUser = (int) request.getData().get(0);
                int newIdUser = (int) request.getData().get(1);
                idFile = (int) request.getData().get(2);
                code = controller.addUserToFile(idUser, newIdUser, idFile);
                response = new Response(code);
                break;

            case "addCommit":
                idUser = (int) request.getData().get(0);
                idFile = (int) request.getData().get(1);
                text = (List<String>) request.getData().get(2);
                code = controller.addCommit(idUser, idFile, text);
                response = new Response(code);
                break;

            case "getCommitDateById":
                idUser = (int) request.getData().get(0);
                idFile = (int) request.getData().get(1);
                idCommit = (int) request.getData().get(2);
                String date = controller.getCommitDateById(idUser, idFile, idCommit);
                code = (date != null) ? 100 : 200;
                response = new Response(code, date);
                break;

            case "getCommitById":
                idUser = (int) request.getData().get(0);
                idFile = (int) request.getData().get(1);
                idCommit = (int) request.getData().get(2);
                Commit commit = controller.getCommitById(idUser, idFile, idCommit);
                code = (commit != null) ? 100 : 200;
                response = new Response(code, commit);
                break;

            case "revertFileToCommit":
                idUser = (int) request.getData().get(0);
                idFile = (int) request.getData().get(1);
                idCommit = (int) request.getData().get(2);
                code = controller.revertFileToCommit(idUser, idFile, idCommit);
                response = new Response(code);
                break;
        }
        return response;
    }
}
