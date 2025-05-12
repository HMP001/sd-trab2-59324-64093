package rest;

import client.UsersClient;
import server.rest.RestContentsServer;
import server.rest.RestImagesServer;
import server.rest.UsersRestServer;
import service.UsersTests;

public class RestUsersTests extends UsersTests {

    private static final long TEST_PERIOD = 10; //ms

    public RestUsersTests() {
        UsersRestServer.launchServer(6000, TEST_PERIOD);
        RestImagesServer.launchServer(6001, TEST_PERIOD);
        RestContentsServer.launchServer(6002, TEST_PERIOD);
        this.users = UsersClient.getInstance();
    }

}
