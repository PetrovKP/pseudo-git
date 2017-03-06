package com.nopointer.server.protocol;

import com.google.inject.AbstractModule;

public class TestProtocolModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Protocol.class).to(OKProtocolStub.class);
    }
}
