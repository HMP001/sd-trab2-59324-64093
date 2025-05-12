package grpc;

import client.UsersClient;
import server.grpc.GrpcContentServer;
import server.grpc.GrpcImageServer;
import server.grpc.GrpcUsersServer;
import service.UsersTests;

public class GrpcUsersTests extends UsersTests {

    private static final long TEST_PERIOD = 10; //ms

    private static boolean hasStarted = false;

    public GrpcUsersTests() throws InterruptedException {
        if (!hasStarted) {
            new Thread(() -> GrpcUsersServer.launchServer(6000, TEST_PERIOD)).start();
            new Thread(() -> GrpcImageServer.launchServer(6001, TEST_PERIOD)).start();
            new Thread(() -> GrpcContentServer.launchServer(6002, TEST_PERIOD)).start();
            hasStarted = true;
            Thread.sleep(2000); // Don't know why we have to wait so long for initialization, but less than this and the tests fail
        }
        this.users = UsersClient.getInstance();
    }

}
