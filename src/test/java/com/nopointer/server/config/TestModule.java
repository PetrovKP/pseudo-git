package com.nopointer.server.config;

import com.google.inject.AbstractModule;
<<<<<<< HEAD
import com.nopointer.server.database.TestDatabaseModule;
=======
import com.nopointer.server.controller.TestControllerModule;
import com.nopointer.server.database.TestDatabaseStubModule;
>>>>>>> 8cb6c13e511166601225beace5f10e31e3632cf7
import com.nopointer.server.protocol.TestProtocolModule;

public class TestModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new TestProtocolModule());
<<<<<<< HEAD
        install(new TestDatabaseModule());
=======
        install(new TestDatabaseStubModule());
        install(new TestControllerModule());
>>>>>>> 8cb6c13e511166601225beace5f10e31e3632cf7
    }
}
