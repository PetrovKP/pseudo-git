package com.nopointer.server.database;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class TestDatabaseStubModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Database.class)
                .annotatedWith(Names.named("StubIfNeeded"))
                .to(DatabaseStub.class)
                .asEagerSingleton();

        bind(DatabaseAPI.class)
                .annotatedWith(Names.named("StubIfNeeded"))
                .to(DatabaseAPIStub.class);

    }
}
