package server.rest;

import api.Post;
import api.rest.RestContent;
import impl.JavaContent;
import client.ImageClient;
import client.UsersClient;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import network.DataModelAdaptor;

import java.util.List;

import static network.DataModelAdaptor.incorporateUrlToId;
import static server.rest.RestServerUtils.statusCodeToException;
import static server.rest.RestServerUtils.wrapResult;

public class ContentResource implements RestContent {

    @Context
    private UriInfo uri;

    private final JavaContent contents = new JavaContent();

    public ContentResource() {
        contents.setUsers(UsersClient.getInstance());
        contents.setImages(ImageClient.getInstance());
    }

    @Override
    public String createPost(Post post, String userPassword) {
        hideParentUrl(post);
        return wrapResult(contents.createPost(post, userPassword));
    }

    private static void hideParentUrl(Post post) {
        var parentUrl = post.getParentUrl();
        if (parentUrl != null)
            post.setParentUrl(DataModelAdaptor.extractIdFromUrl(parentUrl));
    }

    @Override
    public List<String> getPosts(long timestamp, String sortOrder) {
        return wrapResult(contents.getPosts(timestamp, sortOrder));
    }

    @Override
    public Post getPost(String postId) {
        var res = contents.getPost(postId);
        if (res.isOK()) {
            var post = res.value();
            incorporateParentUrl(post);
            return post;
        }
        throw statusCodeToException(res.error());
    }

    private void incorporateParentUrl(Post post) {
        var parentId = post.getParentUrl();
        if (parentId != null) {
            var parentUrl = incorporateUrlToId(uri.getBaseUri(), parentId);
            post.setParentUrl(parentUrl);
        }
    }

    @Override
    public List<String> getPostAnswers(String postId, long maxTimeout) {
        return wrapResult(contents.getPostAnswers(postId, maxTimeout));
    }

    @Override
    public Post updatePost(String postId, String userPassword, Post post) {
        return wrapResult(contents.updatePost(postId, userPassword, post));
    }

    @Override
    public void deletePost(String postId, String userPassword) {
        wrapResult(contents.deletePost(postId, userPassword));
    }

    @Override
    public void upVotePost(String postId, String userId, String userPassword) {
        wrapResult(contents.upVotePost(postId, userId, userPassword));
    }

    @Override
    public void removeUpVotePost(String postId, String userId, String userPassword) {
        wrapResult(contents.removeUpVotePost(postId, userId, userPassword));
    }

    @Override
    public void downVotePost(String postId, String userId, String userPassword) {
        wrapResult(contents.downVotePost(postId, userId, userPassword));
    }

    @Override
    public void removeDownVotePost(String postId, String userId, String userPassword) {
        wrapResult(contents.removeDownVotePost(postId, userId, userPassword));
    }

    @Override
    public Integer getupVotes(String postId) {
        return wrapResult(contents.getupVotes(postId));
    }

    @Override
    public Integer getDownVotes(String postId) {
        return wrapResult(contents.getDownVotes(postId));
    }

    @Override
    public void forgetUser(String uid) {
        wrapResult(contents.forgetUser(uid));
    }
    
    @Override
    public Boolean checkImage(String iid) {
        return wrapResult(contents.checkImage(iid));
    }
}
