package com.nopointer.server.database;

import com.google.inject.AbstractModule;

public class TestDatabaseStubModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Database.class)
                .to(DatabaseStub.class)
                .asEagerSingleton();

        bind(DatabaseAPI.class)
                .to(DatabaseAPIStub.class);

    }
}
