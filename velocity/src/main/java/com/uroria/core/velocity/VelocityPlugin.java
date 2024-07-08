package com.uroria.core.velocity;

import com.google.inject.Inject;
import com.uroria.core.UroriaCore;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

public final class VelocityPlugin {
    private final Logger logger;
    private final ProxyServer server;

    @Inject
    public VelocityPlugin(ProxyServer server) {
        this.logger = UroriaCore.getLogger();
        this.server = server;
        this.logger.info("Hey");
    }
}
