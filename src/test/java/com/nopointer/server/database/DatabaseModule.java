package com.nopointer.server.database;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class DatabaseModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Database.class)
                .annotatedWith(Names.named("STUB"))
                .to(DatabaseStub.class)
                .asEagerSingleton();

        bind(DatabaseAPI.class)
                .annotatedWith(Names.named("STUB"))
                .to(DatabaseAPIStub.class);

    }
}
