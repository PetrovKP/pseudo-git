package com.nopointer.server.database;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class DatabaseModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Database.class).to(DatabaseImpl.class);

        bind(String.class)
                .annotatedWith(Names.named("config.properties"))
                .toInstance("src/main/resources/config.properties");

        bind(DatabaseAPI.class).to(DatabaseAPIGit.class);
    }
}
