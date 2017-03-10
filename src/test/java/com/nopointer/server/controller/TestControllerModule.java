package com.nopointer.server.controller;

import com.google.inject.AbstractModule;

public class TestControllerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Controller.class).to(ControllerImpl.class);
    }
}
