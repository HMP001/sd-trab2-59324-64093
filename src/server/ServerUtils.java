package server;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerUtils {

    private static final String SERVER_URI_FMT = "%s://%s:%s/%s";

    public enum CommInterface {
        REST("rest"),
        GRPC("grpc");

        private final String type;

        CommInterface(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public static String computeServerUri(String protocol, int port, CommInterface comm) throws UnknownHostException {
        String hostname = InetAddress.getLocalHost().getHostName();
        return SERVER_URI_FMT.formatted(protocol, hostname, port, comm.getType());
    }

}
