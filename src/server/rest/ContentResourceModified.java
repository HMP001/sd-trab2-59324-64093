package server.rest;

import api.Post;
import api.rest.ModifiedRestContent;
import impl.JavaContent;
import client.ImageClient;
import client.UsersClient;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import network.DataModelAdaptor;
import jakarta.ws.rs.core.Response;

import static network.DataModelAdaptor.incorporateUrlToId;
import static server.rest.RestServerUtils.wrapResponse;

public class ContentResourceModified implements ModifiedRestContent {

    @Context
    private UriInfo uri;

    private final JavaContent contents = new JavaContent();

    public ContentResourceModified() {
        contents.setUsers(UsersClient.getInstance());
        contents.setImages(ImageClient.getInstance());
    }

    @Override
    public Response createPost(Long Version, Post post, String userPassword) {
        hideParentUrl(post);
        return wrapResponse(contents.createPost(post, userPassword));
    }

    private static void hideParentUrl(Post post) {
        var parentUrl = post.getParentUrl();
        if (parentUrl != null)
            post.setParentUrl(DataModelAdaptor.extractIdFromUrl(parentUrl));
    }

    @Override
    public Response getPosts(Long Version, long timestamp, String sortOrder) {
        return wrapResponse(contents.getPosts(timestamp, sortOrder));
    }

    @Override
    public Response getPost(Long version, String postId) {
        var res = contents.getPost(postId);
        if (res.isOK()) {
            var post = res.value();
            incorporateParentUrl(post);   
        }
        return wrapResponse(res);
    }

    private void incorporateParentUrl(Post post) {
        var parentId = post.getParentUrl();
        if (parentId != null) {
            var parentUrl = incorporateUrlToId(uri.getBaseUri(), parentId);
            post.setParentUrl(parentUrl);
        }
    }

    @Override
    public Response getPostAnswers(Long Version, String postId, long maxTimeout) {
        return wrapResponse(contents.getPostAnswers(postId, maxTimeout));
    }

    @Override
    public Response updatePost(Long Version, String postId, String userPassword, Post post) {
        return wrapResponse(contents.updatePost(postId, userPassword, post));
    }

    @Override
    public Response deletePost(Long version, String postId, String userPassword) {
        return wrapResponse(contents.deletePost(postId, userPassword));
    }

    @Override
    public Response upVotePost(Long version, String postId, String userId, String userPassword) {
        return wrapResponse(contents.upVotePost(postId, userId, userPassword));
    }

    @Override
    public Response removeUpVotePost(Long version, String postId, String userId, String userPassword) {
        return wrapResponse(contents.removeUpVotePost(postId, userId, userPassword));
    }

    @Override
    public Response downVotePost(Long version, String postId, String userId, String userPassword) {
       return wrapResponse(contents.downVotePost(postId, userId, userPassword));
    }

    @Override
    public Response removeDownVotePost(Long version, String postId, String userId, String userPassword) {
       return wrapResponse(contents.removeDownVotePost(postId, userId, userPassword));
    }

    @Override
    public Response getupVotes(Long version, String postId) {
        return wrapResponse(contents.getupVotes(postId));
    }

    @Override
    public Response getDownVotes(Long version, String postId) {
        return wrapResponse(contents.getDownVotes(postId));
    }

    @Override
    public Response forgetUser(Long version, String uid) {
        return wrapResponse(contents.forgetUser(uid));
    }
}
