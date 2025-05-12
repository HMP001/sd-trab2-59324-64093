package rest;

import client.ContentClient;
import client.ImageClient;
import client.UsersClient;
import server.rest.RestContentsServer;
import server.rest.RestImagesServer;
import server.rest.UsersRestServer;
import service.IntegrationTests;

public class RestIntegrationTests extends IntegrationTests {

    private static final long TEST_PERIOD = 10; //ms

    public RestIntegrationTests() {
        UsersRestServer.launchServer(6000, TEST_PERIOD);
        RestImagesServer.launchServer(6001, TEST_PERIOD);
        RestContentsServer.launchServer(6002, TEST_PERIOD);
        this.users = UsersClient.getInstance();
        this.contents = ContentClient.getInstance();
        this.images = ImageClient.getInstance();
    }

}
