package server.rest;

import client.UsersClient;
import network.ServiceAnnouncer;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

public class UsersRestServer {

    private static final Logger log = Logger.getLogger(UsersRestServer.class.getName());

    static {
        System.setProperty("java.net.preferIPv4Stack", "true");
        System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s%n");
    }

    public static final int PORT = 8080;

    public static void main(String[] args) {
        launchServer(PORT);
    }

    public static void launchServer(int port) {
        launchServer(port, Optional.empty());
    }

    public static void launchServer(int port, long period) {
        launchServer(port, Optional.of(period));
    }

    private static void launchServer(int port, Optional<Long> period) {
        try {
            var serverURI = RestServerUtils.computeServerUri(port);
            announceService(serverURI, period);
            RestServerUtils.launchResource(serverURI, UsersResource.class);
            log.info(String.format("%s Server ready @ %s\n",  UsersClient.SERVICE, serverURI));
        } catch( Exception e) {
            log.severe(e.getMessage());
        }
    }

    private static void announceService(String serverURI, Optional<Long> period) throws IOException {
        if (period.isPresent())
            new ServiceAnnouncer(UsersClient.SERVICE, serverURI, period.get());
        else
            new ServiceAnnouncer(UsersClient.SERVICE, serverURI);
    }

}
