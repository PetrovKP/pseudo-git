package com.nopointer.server.config;

import com.google.inject.AbstractModule;

import com.nopointer.server.database.TestDatabaseModule;
import com.nopointer.server.controller.TestControllerModule;
import com.nopointer.server.database.TestDatabaseStubModule;
import com.nopointer.server.protocol.TestProtocolModule;

public class TestModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new TestProtocolModule());
        install(new TestDatabaseModule());
        install(new TestDatabaseStubModule());
        install(new TestControllerModule());
    }
}
