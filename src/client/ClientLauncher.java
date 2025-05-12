package client;

import network.DiscoveryListener;
import server.ServerUtils;

import java.net.URI;
import java.util.function.Function;
import java.util.logging.Logger;

public class ClientLauncher {

    private static final Logger log = Logger.getLogger(ClientLauncher.class.getName());

    private final DiscoveryListener listener = DiscoveryListener.getInstance();

    <T> T launch(String service, Function<URI, T> restLauncher, Function<URI, T> grpcLauncher) {
        var serverUri = listener.knownUrisOf(service, 1)[0];
        var uriComponents = serverUri.getPath().split("/");
        var commType = uriComponents[uriComponents.length - 1];
        if (commType.equals(ServerUtils.CommInterface.REST.getType())) {
            return restLauncher.apply(serverUri);
        } else if (commType.equals(ServerUtils.CommInterface.GRPC.getType())) {
            return grpcLauncher.apply(serverUri);
        } else {
            log.severe("Received unknown communication type %s".formatted(commType));
            throw new RuntimeException();
        }

    }

}
