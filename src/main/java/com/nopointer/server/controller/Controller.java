package com.nopointer.server.controller;

import com.nopointer.server.protocol.entity.Code;

public interface Controller {
    // TODO: add all functions
    Code registerUser(String login, String password);
}
