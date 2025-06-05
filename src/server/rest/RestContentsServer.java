package server.rest;

import client.ContentClient;
import client.ImageClient;
import client.UsersClient;
import impl.JavaContent;
import impl.JavaImgur;
import network.ServiceAnnouncer;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

public class RestContentsServer {

    private static final Logger log = Logger.getLogger(RestContentsServer.class.getName());

    static {
        System.setProperty("java.net.preferIPv4Stack", "true");
        System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s%n");
    }

    public static final int PORT = 8080;

    public static void main(String[] args) {
    	JavaContent content = new JavaContent();
    	content.setUsers(UsersClient.getInstance());
        content.setImages(ImageClient.getInstance());
        content.setNullThread();
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
            announceService(period, serverURI);
            RestServerUtils.launchResource(serverURI, ContentResource.class);
            log.info(String.format("%s Server ready @ %s\n",  ContentClient.SERVICE, serverURI));
        } catch( Exception e) {
            log.severe(e.getMessage());
        }
    }

    private static void announceService(Optional<Long> period, String serverURI) throws IOException {
        if (period.isPresent())
            new ServiceAnnouncer(ContentClient.SERVICE, serverURI, period.get());
        else
            new ServiceAnnouncer(ContentClient.SERVICE, serverURI);
    }


}
