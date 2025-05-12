package client.rest;

import api.Post;
import api.java.Content;
import api.java.Result;
import api.rest.RestContent;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

import static api.java.Result.ErrorCode.*;
import static api.java.Result.*;
import static client.rest.RestClientUtils.*;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class RestContentClient implements Content {

    private static final Logger log = Logger.getLogger(RestContentClient.class.getName());

    private final WebTarget baseTarget;

    public RestContentClient(URI serverUri) {
        Client client = RestClientUtils.computeClient();
        this.baseTarget = client.target(serverUri).path(RestContent.PATH);
    }

    @Override
    public Result<String> createPost(Post p, String pwd) {
        log.fine("Creating post %s with pwd %s".formatted(p.toString(), pwd));
        var target = baseTarget.queryParam(RestContent.PASSWORD, pwd);
        return genericPostRequest(target, p, String.class);
    }

    @Override
    public Result<List<String>> getPosts(long timestamp, String sortOrder) {
        log.fine("Getting posts with sort order " + sortOrder);
        var target = baseTarget.queryParam(RestContent.TIMESTAMP, timestamp).queryParam(RestContent.SORTBY, sortOrder);
        Result<String[]> res = genericGetRequest(target, String[].class);
        return Result.map(res, arr -> Arrays.stream(arr).toList());
    }

    @Override
    public Result<Post> getPost(String pid) {
        log.fine("Getting post " + pid);
        if (pid == null)
            return error(BAD_REQUEST);
        var target = baseTarget.path(pid);
        return genericGetRequest(target, Post.class);
    }

    @Override
    public Result<List<String>> getPostAnswers(String pid, long maxTimeout) {
        log.fine("Getting post answers for post " + pid);
        if (pid == null)
            return error(BAD_REQUEST);
        var target = baseTarget.path(pid).path(RestContent.REPLIES);
        Result<String[]> res = genericGetRequest(target, String[].class);
        return Result.map(res, arr -> Arrays.stream(arr).toList());
    }

    @Override
    public Result<Post> updatePost(String pid, String pwd, Post updatedFields) {
        log.fine("Updating post %s with pwd %s and fields %s".formatted(pid, pwd, updatedFields.toString()));
        if (pid == null)
            return error(BAD_REQUEST);
        var target = baseTarget.path(pid).queryParam(RestContent.PASSWORD, pwd);
        return genericPutRequest(target, updatedFields, Post.class);
    }

    @Override
    public Result<Void> deletePost(String pid, String pwd) {
        log.fine("Deleting post %s with pwd %s".formatted(pid, pwd));
        if (pid == null)
            return error(BAD_REQUEST);
        var target = baseTarget.path(pid).queryParam(RestContent.PASSWORD, pwd);
        return deleteNoContentRequest(target);
    }

    @Override
    public Result<Void> upVotePost(String pid, String uid, String pwd) {
        log.fine("Upvoting post %s of user %s with pwd %s".formatted(pid, uid, pwd));
        if (pid == null || uid == null)
            return error(BAD_REQUEST);
        var target = baseTarget.path(pid).path(RestContent.UPVOTE).path(uid).queryParam(RestContent.PASSWORD, pwd);
        return addVoteRequest(target);
    }

    @Override
    public Result<Void> downVotePost(String pid, String uid, String pwd) {
        log.fine("Downvoting post %s of user %s with pwd %s".formatted(pid, uid, pwd));
        if (pid == null || uid == null)
            return error(BAD_REQUEST);
        var target = baseTarget.path(pid).path(RestContent.DOWNVOTE).path(uid).queryParam(RestContent.PASSWORD, pwd);
        return addVoteRequest(target);
    }

    private static Result<Void> addVoteRequest(WebTarget target) {
        return RestClientUtils.runRepeatableRequest(() -> {
            var invocation = target.request();
            try (var response = invocation.post(null)) {
                int status = response.getStatus();
                if (status != Response.Status.NO_CONTENT.getStatusCode())
                    return error(getErrorCodeFrom(status));
                return Result.ok();
            }
        });
    }

    @Override
    public Result<Void> removeUpVotePost(String pid, String uid, String pwd) {
        log.fine("Removing upvote from post %s of user %s with pwd %s".formatted(pid, uid, pwd));
        if (pid == null || uid == null)
            return error(BAD_REQUEST);
        var target = baseTarget.path(pid).path(RestContent.UPVOTE).path(uid).queryParam(RestContent.PASSWORD, pwd);
        return deleteNoContentRequest(target);
    }

    @Override
    public Result<Void> removeDownVotePost(String pid, String uid, String pwd) {
        log.fine("Removing downvote from post %s of user %s with pwd %s".formatted(pid, uid, pwd));
        if (pid == null || uid == null)
            return error(BAD_REQUEST);
        var target = baseTarget.path(pid).path(RestContent.DOWNVOTE).path(uid).queryParam(RestContent.PASSWORD, pwd);
        return deleteNoContentRequest(target);
    }

    @Override
    public Result<Integer> getupVotes(String pid) {
        log.fine("Getting upvotes for post " + pid);
        if (pid == null)
            return error(BAD_REQUEST);
        var target = baseTarget.path(pid).path(RestContent.UPVOTE);
        return genericGetRequest(target, Integer.class);
    }

    @Override
    public Result<Integer> getDownVotes(String pid) {
        log.fine("Getting downvotes for post " + pid);
        if (pid == null)
            return error(BAD_REQUEST);
        var target = baseTarget.path(pid).path(RestContent.DOWNVOTE);
        return genericGetRequest(target, Integer.class);
    }

    @Override
    public Result<Void> forgetUser(String uid) {
        log.fine("Forgetting user " + uid);
        var target = baseTarget.path(RestContent.FORGET).path(uid);
        return deleteNoContentRequest(target);
    }

}
