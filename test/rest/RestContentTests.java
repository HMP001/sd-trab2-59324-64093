package rest;

import client.ContentClient;
import client.UsersClient;
import jakarta.ws.rs.core.UriBuilder;
import server.rest.RestContentsServer;
import server.rest.RestImagesServer;
import server.rest.UsersRestServer;
import service.ContentTests;

public class RestContentTests extends ContentTests {

    private static final long TEST_PERIOD = 10; //ms

    public RestContentTests() {
        UsersRestServer.launchServer(6000, TEST_PERIOD);
        RestImagesServer.launchServer(6001, TEST_PERIOD);
        RestContentsServer.launchServer(6002, TEST_PERIOD);
        this.users = UsersClient.getInstance();
        this.content = ContentClient.getInstance();
    }

    @Override
    public String getUriPrefix(String id) {
        var prefix = "http://address:port/rest/content";
        return UriBuilder.fromUri(prefix).path(id).build().toASCIIString();
    }
}
