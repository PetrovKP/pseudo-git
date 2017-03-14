package com.nopointer.server.database;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class TestDatabaseError extends AbstractModule{
    @Override
    protected void configure() {
        bind(Database.class)
                .to(DatabaseImpl.class)
                .asEagerSingleton();

        bind(DatabaseAPI.class).to(DatabaseAPIGit.class);

        bind(String.class)
                .annotatedWith(Names.named("config.properties"))
                .toInstance("src/main/resources/error.properties");

    }
}
