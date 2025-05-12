package grpc;

import client.ContentClient;
import client.UsersClient;
import jakarta.ws.rs.core.UriBuilder;
import server.grpc.GrpcContentServer;
import server.grpc.GrpcImageServer;
import server.grpc.GrpcUsersServer;
import service.ContentTests;

public class GrpcContentTests extends ContentTests {

    private static final long TEST_PERIOD = 10; //ms

    private static boolean hasStarted = false;

    public GrpcContentTests() throws InterruptedException {
        if (!hasStarted) {
            new Thread(() -> GrpcUsersServer.launchServer(6000, TEST_PERIOD)).start();
            new Thread(() -> GrpcImageServer.launchServer(6001, TEST_PERIOD)).start();
            new Thread(() -> GrpcContentServer.launchServer(6002, TEST_PERIOD)).start();
            hasStarted = true;
            Thread.sleep(2000);
        }
        this.users = UsersClient.getInstance();
        this.content = ContentClient.getInstance();
    }

    @Override
    public String getUriPrefix(String id) {
        var prefix = "grpc://address:port/grpc/content";
        return UriBuilder.fromUri(prefix).path(id).build().toASCIIString();
    }
}
