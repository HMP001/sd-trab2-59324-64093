package server.rest;

import client.ImageClient;
import network.ServiceAnnouncer;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

public class RestImagesServer {

    private static final Logger log = Logger.getLogger(RestImagesServer.class.getName());

    static {
        System.setProperty("java.net.preferIPv4Stack", "true");
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
            RestServerUtils.launchResource(serverURI, ImageResource.class);
            log.info(String.format("%s Server ready @ %s\n",  ImageClient.SERVICE, serverURI));
        } catch( Exception e) {
            log.severe(e.getMessage());
        }
    }

    private static void announceService(String serverURI, Optional<Long> period) throws IOException {
        if (period.isPresent())
            new ServiceAnnouncer(ImageClient.SERVICE, serverURI, period.get());
        else
            new ServiceAnnouncer(ImageClient.SERVICE, serverURI);
    }
}
