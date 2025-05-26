package server.grpc;

import client.ContentClient;
import network.ServiceAnnouncer;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

public class GrpcContentServer {

    private static final Logger log = Logger.getLogger(GrpcContentServer.class.getName());

    public static final int PORT = 9000;

    public static void main(String[] args) {
        launchServer(PORT);
    }

    public static void launchServer(int port) {
        launchServer(port, Optional.empty());
    }

    public static void launchServer(int port, long period) {
        launchServer(port, Optional.of(period));
    }

    public static void launchServer(int port, Optional<Long> period) {
        try {
            var serverURI = GrpcServerUtils.computeServerUri(port);
            announceService(serverURI, period);
            var stub = new GrpcContentStub(serverURI);
            var context = GrpcServerUtils.addSslContext();
            log.info(String.format("Users gRPC Server ready @ %s\n", serverURI));
            GrpcServerUtils.launchServer(context, port, stub);
        } catch (Exception e) {
            log.severe("Unable to launch gRPC server at port %d".formatted(port));
            throw new RuntimeException(e);
        }
    }

    private static void announceService(String serverURI, Optional<Long> period) throws IOException {
        if (period.isPresent())
            new ServiceAnnouncer(ContentClient.SERVICE, serverURI, period.get());
        else
            new ServiceAnnouncer(ContentClient.SERVICE, serverURI);
    }
}
