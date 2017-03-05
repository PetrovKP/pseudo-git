package com.nopointer.server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nopointer.server.config.Module;
import com.nopointer.server.protocol.Protocol;

public class Starter {
    // The 'main' class, where instance of server will be started
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new Module());
    }
}
