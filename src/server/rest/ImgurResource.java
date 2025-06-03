package server.rest;

import api.rest.RestImage;
import api.rest.RestImgur;
import client.UsersClient;
import impl.JavaImgur;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

import static server.rest.RestServerUtils.statusCodeToException;
import static server.rest.RestServerUtils.wrapResult;

public class ImgurResource implements RestImgur {

	@Context
    private UriInfo uri;
	
    private final JavaImgur imgur = new JavaImgur();

    public ImgurResource() {
        imgur.setUsers(UsersClient.getInstance());
    }

    @Override
    public String createImage(String userId, byte[] imageContents, String password) {
        var res = imgur.createImage(userId, imageContents, password);
        if (!res.isOK())
            throw statusCodeToException(res.error());
        var relativeUri = res.value();
        var baseUri = uri.getBaseUri();
        var uri = UriBuilder.fromUri(baseUri).path(RestImage.PATH).path(relativeUri).build();
        return uri.toASCIIString();
    }

    @Override
    public byte[] getImage(String userId, String imageId) {
        return wrapResult(imgur.getImage(userId, imageId));
    }

    @Override
    public void deleteImage(String userId, String imageId, String password) {
        wrapResult(imgur.deleteImage(userId, imageId, password));
    }

    @Override
    public void deleteImageUponUserOrPostRemoval(String uid, String iid) {
        wrapResult(imgur.deleteImageUponUserOrPostRemoval(uid, iid));
    }

    @Override
    public void teardown() {
        wrapResult(imgur.teardown());
    }
  
}
