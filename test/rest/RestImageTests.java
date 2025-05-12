package rest;

import api.rest.RestImage;
import client.ImageClient;
import client.UsersClient;
import org.junit.Test;
import server.ServerUtils;
import server.rest.*;
import service.ImageTests;

import static org.junit.Assert.*;

public class RestImageTests extends ImageTests {

    private static final long TEST_PERIOD = 10; //ms

    public RestImageTests() {
        UsersRestServer.launchServer(6000, TEST_PERIOD);
        RestImagesServer.launchServer(6001, TEST_PERIOD);
        RestContentsServer.launchServer(6002, TEST_PERIOD);
        this.users = UsersClient.getInstance();
        this.images = ImageClient.getInstance();
    }

    @Test
    public void shouldContainFullPath() {
        var u = createValidUser();
        var content = randomByteString();
        var uri = createValidImg(u.getUserId(), content, u.getPassword());
        assertTrue(uri.contains(RestServerUtils.COMM_PROTOCOL));
        assertTrue(uri.contains(ServerUtils.CommInterface.REST.getType()));
        assertTrue(uri.contains(RestImage.PATH));
    }

}
