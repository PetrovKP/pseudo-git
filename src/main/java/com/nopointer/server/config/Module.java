package com.nopointer.server.config;

import com.google.inject.AbstractModule;
import com.nopointer.server.protocol.ProtocolModule;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        install(new ProtocolModule());
    }
}
