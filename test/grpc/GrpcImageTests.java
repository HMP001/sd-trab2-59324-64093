package grpc;

import client.ImageClient;
import client.UsersClient;
import server.grpc.GrpcContentServer;
import server.grpc.GrpcImageServer;
import server.grpc.GrpcUsersServer;
import service.ImageTests;

public class GrpcImageTests extends ImageTests {

    private static final long TEST_PERIOD = 10; //ms

    private static boolean hasStarted = false;

    public GrpcImageTests() throws InterruptedException {
        if (!hasStarted) {
            new Thread(() -> GrpcUsersServer.launchServer(6000, TEST_PERIOD)).start();
            new Thread(() -> GrpcImageServer.launchServer(6001, TEST_PERIOD)).start();
            new Thread(() -> GrpcContentServer.launchServer(6002, TEST_PERIOD)).start();
            hasStarted = true;
            Thread.sleep(2000);
        }
        this.users = UsersClient.getInstance();
        this.images = ImageClient.getInstance();
    }

}
