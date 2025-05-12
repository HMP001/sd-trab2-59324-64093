package client.rest;

import api.java.Image;
import api.java.Result;
import api.rest.RestImage;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;

import java.net.URI;
import java.util.logging.Logger;

import static api.java.Result.ErrorCode.BAD_REQUEST;
import static api.java.Result.error;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM;

import static client.rest.RestClientUtils.deleteNoContentRequest;

public class RestImageClient implements Image {

    private static final Logger log = Logger.getLogger(RestImageClient.class.getName());

    private final WebTarget baseTarget;

    public RestImageClient(URI serverUri) {
        Client client = RestClientUtils.computeClient();
        this.baseTarget = client.target(serverUri).path(RestImage.PATH);
    }

    @Override
    public Result<String> createImage(String uid, byte[] content, String pwd) {
        log.info("Creating image with uid: " + uid);
        if (uid == null)
            return error(BAD_REQUEST);
        return RestClientUtils.runRepeatableRequest(() -> {
            var target = baseTarget.path(uid).queryParam(RestImage.PASSWORD, pwd);
            var invocation = target.request().accept(APPLICATION_JSON);
            var body = Entity.entity(content, APPLICATION_OCTET_STREAM);
            try (var response = invocation.post(body)) {
                return RestClientUtils.processResponseWithBody(String.class, response);
            }
        });
    }

    @Override
    public Result<byte[]> getImage(String uid, String iid) {
        log.info("Retrieving image with uid: " + iid);
        if (uid == null || iid == null)
            return error(BAD_REQUEST);
        return RestClientUtils.runRepeatableRequest(() -> {
            var target = baseTarget.path(uid).path(iid);
            var invocation = target.request().accept(APPLICATION_OCTET_STREAM);
            try (var response = invocation.get()) {
                return RestClientUtils.processResponseWithBody(byte[].class, response);
            }
        });
    }

    @Override
    public Result<Void> deleteImage(String uid, String iid, String pwd) {
        log.info("Deleting image with uid: " + uid);
        if (uid == null || iid == null)
            return error(BAD_REQUEST);
        var target = baseTarget.path(uid).path(iid).queryParam(RestImage.PASSWORD, pwd);
        return deleteNoContentRequest(target);
    }

    @Override
    public Result<Void> deleteImageUponUserOrPostRemoval(String uid, String iid) {
        var target = baseTarget.path(RestImage.DELETE).path(uid).path(iid);
        return deleteNoContentRequest(target);
    }

    @Override
    public Result<Void> teardown() {
        var target = baseTarget.path(RestImage.DELETEALL);
        return deleteNoContentRequest(target);
    }

}
