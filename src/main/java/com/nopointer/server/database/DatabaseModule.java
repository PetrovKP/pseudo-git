package com.nopointer.server.database;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class DatabaseModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Database.class).to(DatabaseImpl.class).asEagerSingleton();
        bind(DatabaseAPI.class).to(DatabaseAPIGit.class);
    }
}
