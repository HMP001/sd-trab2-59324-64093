package server.rest;

import api.rest.RestImage;
import impl.JavaImage;
import client.ContentClient;
import client.UsersClient;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

import static server.rest.RestServerUtils.statusCodeToException;
import static server.rest.RestServerUtils.wrapResult;

public class ImageResource implements RestImage {

    @Context
    private UriInfo uri;

    private final JavaImage images = new JavaImage();

    public ImageResource() {
        images.setUsers(UsersClient.getInstance());
        images.setContent(ContentClient.getInstance());
    }

    @Override
    public String createImage(String userId, byte[] imageContents, String password) {
        var res = images.createImage(userId, imageContents, password);
        if (!res.isOK())
            throw statusCodeToException(res.error());
        var relativeUri = res.value();
        var baseUri = uri.getBaseUri();
        var uri = UriBuilder.fromUri(baseUri).path(RestImage.PATH).path(relativeUri).build();
        return uri.toASCIIString();
    }

    @Override
    public byte[] getImage(String userId, String imageId) {
        return wrapResult(images.getImage(userId, imageId));
    }

    @Override
    public void deleteImage(String userId, String imageId, String password) {
        wrapResult(images.deleteImage(userId, imageId, password));
    }

    @Override
    public void deleteImageUponUserOrPostRemoval(String uid, String iid) {
        wrapResult(images.deleteImageUponUserOrPostRemoval(uid, iid));
    }

    @Override
    public void teardown() {
        images.teardown();
    }
}
