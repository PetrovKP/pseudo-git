package com.nopointer.server.protocol;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class ProtocolModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Protocol.class).to(OKProtocol.class);
        bind(ProtocolConnection.class).to(OKProtocolConnection.class);
    }
}
