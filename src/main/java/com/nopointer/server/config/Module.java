package com.nopointer.server.config;

import com.google.inject.AbstractModule;
import com.nopointer.server.connection.ConnectionModule;
import com.nopointer.server.controller.ControllerModule;
import com.nopointer.server.database.DatabaseModule;
import com.nopointer.server.protocol.ProtocolModule;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        // install all modules
        install(new DatabaseModule());
        install(new ControllerModule());
        install(new ProtocolModule());
        install(new ConnectionModule());
    }
}
