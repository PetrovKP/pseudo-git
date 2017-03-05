package com.nopointer.server.connection;

import com.google.inject.AbstractModule;

public class ConnectionModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(ClientConnection.class).to(ClientConnectionImpl.class);
    }
}
