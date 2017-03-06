package com.nopointer.server.controller;

import com.google.inject.Inject;
import com.nopointer.server.database.Database;
import com.nopointer.server.protocol.entity.Code;

class ControllerImpl implements Controller {
    private Database database;

    @Inject
    public ControllerImpl(Database database) {
        this.database = database;
        database.connect();
    }

    // TODO: add all functions, provided by DatabaseAPI
    @Override
    public Code registerUser(String login, String password) {
        database.getAPI().registerUser(login, password);
        return null;
    }
}
