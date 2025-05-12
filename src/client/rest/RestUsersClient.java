package client.rest;

import api.User;
import api.java.Result;
import api.java.Users;
import api.rest.RestUsers;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static api.java.Result.ErrorCode.BAD_REQUEST;
import static api.java.Result.ErrorCode.FORBIDDEN;
import static api.java.Result.error;
import static client.rest.RestClientUtils.*;

public class RestUsersClient implements Users {

    private static final Logger log = Logger.getLogger(RestUsersClient.class.getName());

    private final WebTarget baseTarget;

    public RestUsersClient(URI serverUri) {
        Client client = RestClientUtils.computeClient();
        this.baseTarget = client.target(serverUri).path(RestUsers.PATH);
    }

    @Override
    public Result<String> createUser(User u) {
        log.info("sending create user request");
        return genericPostRequest(baseTarget, u, String.class);
    }

    @Override
    public Result<User> getUser(String uid, String pwd) {
        log.info("sending get user request");
        if (uid == null)
            return error(BAD_REQUEST);
        if (pwd == null)
            return error(FORBIDDEN);
        var target = baseTarget.path(uid).queryParam(RestUsers.PASSWORD, pwd);
        return genericGetRequest(target, User.class);
    }

    @Override
    public Result<User> updateUser(String uid, String pwd, User updatedFields) {
        log.info("sending update user request");
        if (uid == null)
            return error(BAD_REQUEST);
        if (pwd == null)
            return error(FORBIDDEN);
        var target = baseTarget.path(uid).queryParam(RestUsers.PASSWORD, pwd);
        return genericPutRequest(target, updatedFields, User.class);
    }

    @Override
    public Result<User> deleteUser(String uid, String pwd) {
        log.info("sending delete user request");
        if (uid == null)
            return error(BAD_REQUEST);
        if (pwd == null)
            return error(FORBIDDEN);
        var target = baseTarget.path(uid).queryParam(RestUsers.PASSWORD, pwd);
        return genericDeleteRequest(target, User.class);
    }

    @Override
    public Result<List<User>> searchUsers(String pattern) {
        log.info("sending search users request");
        var target = baseTarget.queryParam(RestUsers.QUERY, pattern);
        return Result.map(genericGetRequest(target, User[].class), arr -> Arrays.stream(arr).toList());
    }

}
