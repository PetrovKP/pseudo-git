package com.nopointer.server.database;

import com.google.inject.AbstractModule;

public class DatabaseModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DatabaseAPI.class);//.to(*твоя реализация*.class);
        bind(Database.class);//.to(*твоя реализация*.class);
        //где нужно - добавить .in(Singleton.class);
    }
}
