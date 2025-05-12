package service;

import api.Post;
import api.User;
import api.java.Content;
import api.java.Image;
import api.java.Users;
import network.DiscoveryListener;
import org.junit.After;
import org.junit.Test;
import impl.Hibernate;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static api.java.Result.ErrorCode.NOT_FOUND;
import static org.junit.Assert.*;


public abstract class IntegrationTests {

    protected Users users;

    protected Content contents;

    protected Image images;

    @After
    public void teardown() {
        Hibernate.teardown();
        images.teardown();
        DiscoveryListener.teardown();
    }

    @Test
    public void shouldDeleteAvatarWhenUserDeleted() {
        var u = createValidUser();
        var iid = images.createImage(u.getUserId(), new byte[]{1,2,3}, u.getPassword()).value();
        users.updateUser(u.getUserId(), u.getPassword(), new User(null, null, null, null, iid));
        users.deleteUser(u.getUserId(), u.getPassword());
        var res = images.getImage(u.getUserId(), iid);
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldDeletePostAuthorWhenUserDeleted() {
        var u = createValidUser();
        var numPosts = 10;
        for (int i = 0; i < numPosts; i++)
            createValidPost(u, null);
        users.deleteUser(u.getUserId(), u.getPassword());
        var pids = contents.getPosts(0, null).value();
        var posts = getPostsFromIds(pids);
        for (Post p : posts)
            assertNull(p.getAuthorId());
    }

    @Test
    public void shouldDeleteImageOfRemovedPost() {
        var u = createValidUser();
        var post = new Post(u.getUserId(), "content");
        post.setPostId(contents.createPost(post, u.getPassword()).value());
        var iid = images.createImage(u.getUserId(), new byte[]{1,2,3}, u.getPassword()).value();
        contents.updatePost(post.getPostId(), u.getPassword(), new Post(null, null, null, iid));
        contents.deletePost(post.getPostId(), u.getPassword());
        var res = images.getImage(u.getUserId(), iid);
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldDeleteUpvotesOfRemovedUser() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        contents.upVotePost(p.getPostId(), u.getUserId(), u.getPassword());
        int upvotesStart = contents.getupVotes(p.getPostId()).value();
        assertEquals(1, upvotesStart);
        users.deleteUser(u.getUserId(), u.getPassword());
        int upvotesEnd = contents.getupVotes(p.getPostId()).value();
        assertEquals(0, upvotesEnd);
    }

    @Test
    public void shouldDeleteDownvotesOfRemovedUser() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        contents.downVotePost(p.getPostId(), u.getUserId(), u.getPassword());
        int downvotesStart = contents.getDownVotes(p.getPostId()).value();
        assertEquals(1, downvotesStart);
        users.deleteUser(u.getUserId(), u.getPassword());
        int downvotesEnd = contents.getDownVotes(p.getPostId()).value();
        assertEquals(0, downvotesEnd);
    }

    private User createValidUser() {
        var uid = UUID.randomUUID().toString();
        var u = new User(uid, "name", "email", "password");
        var res = users.createUser(u);
        assertTrue(res.isOK());
        return u;
    }

    private Post createValidPost(User u, String parent) {
        var media = "media" + new Random().nextInt();
        var p = new Post(u.getUserId(), media, parent);
        var pid = contents.createPost(p, u.getPassword()).value();
        p.setPostId(pid);
        return p;
    }

    private List<Post> getPostsFromIds(Collection<String> pids) {
        return pids.stream().map(pid -> contents.getPost(pid).value()).toList();
    }
}
