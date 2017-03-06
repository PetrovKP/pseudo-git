package com.nopointer.server.config;

import com.google.inject.AbstractModule;
import com.nopointer.server.controller.ControllerModule;
import com.nopointer.server.database.DatabaseModule;
import com.nopointer.server.protocol.TestProtocolModule;

public class TestModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new TestProtocolModule());
        install(new DatabaseModule());
        install(new ControllerModule());
    }
}
