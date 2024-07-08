package com.uroria.core.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

public final class VelocityPlugin {
    private final Logger logger;
    private final ProxyServer server;

    @Inject
    public VelocityPlugin(Logger logger, ProxyServer server) {
        this.logger = logger;
        this.server = server;
    }
}
