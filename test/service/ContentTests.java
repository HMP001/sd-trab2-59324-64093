package service;

import api.Post;
import api.User;
import api.java.Content;
import api.java.Users;
import impl.Hibernate;
import network.DiscoveryListener;
import org.junit.After;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static api.java.Result.ErrorCode.*;
import static api.rest.RestContent.MOST_REPLIES;
import static api.rest.RestContent.MOST_UP_VOTES;
import static org.junit.Assert.*;

public abstract class ContentTests {

    protected Users users;

    protected Content content;

    @After
    public void teardown() {
        Hibernate.teardown();
        DiscoveryListener.teardown();
    }

    @Test
    public void shouldRejectCreateInvalidUid() {
        var u = createValidUser();
        String pwd = u.getPassword();
        var p = new Post(null, "media", null);
        var res = content.createPost(p, pwd);
        assertEquals(BAD_REQUEST, res.error());
    }

    @Test
    public void shouldRejectCreateInvalidPwd() {
        var u = createValidUser();
        var p = new Post(u.getUserId(), "media", null);
        var res = content.createPost(p, null);
        assertEquals(FORBIDDEN, res.error());
    }

    @Test
    public void shouldRejectCreateUserNotExist() {
        var p = new Post("uid", "media", null);
        var res = content.createPost(p, "pwd");
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldRejectCreateIncorrectPwd() {
        var u = createValidUser();
        var p = new Post(u.getUserId(), "media", null);
        var res = content.createPost(p, u.getPassword() + "incorrect");
        assertEquals(FORBIDDEN, res.error());
    }

    @Test
    public void shouldAcceptCreateNullParent() {
        var u = createValidUser();
        createValidPost(u, null);
    }

    @Test
    public void shouldRejectCreateInvalidParent() {
        var u = createValidUser();
        var media = "media" + new Random().nextInt();
        var p = new Post(u.getUserId(), media, "parent");
        var res = content.createPost(p, u.getPassword());
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldAcceptCreateValidParent() {
        var u = createValidUser();
        String parentId = null;
        for (int i = 0; i < 10; i++)
            parentId = createValidPost(u, parentId).getPostId();
    }

    @Test
    public void shouldRejectGetPostInvalidInput() {
        var res = content.getPost(null);
        assertEquals(BAD_REQUEST, res.error());
    }

    @Test
    public void shouldRejectGetPostNotExist() {
        var res = content.getPost("invalid");
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldAcceptGetPost() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var res = content.getPost(p.getPostId());
        assertTrue(res.isOK());
    }

    @Test
    public void shouldRejectGetPostAnswersInvalidInput() {
        var res = content.getPostAnswers(null, 0);
        assertEquals(BAD_REQUEST, res.error());
    }

    @Test
    public void shouldRejectGetPostAnswersPostNotExist() {
        var res = content.getPostAnswers("invalid", 0);
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldGetPostAnswers() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var numAnswers = 10;
        for (int i = 0; i < numAnswers; i++)
            createValidPost(u, p.getPostId());
        var answers = content.getPostAnswers(p.getPostId(), 0).value();
        assertEquals(numAnswers, answers.size());
    }

    @Test
    public void shouldGetEmptyPostAnswers() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var answers = content.getPostAnswers(p.getPostId(), 0).value();
        assertTrue(answers.isEmpty());
    }

    @Test
    public void shouldBlockGetPostAnswers() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var maxTimeout = 1000L;
        AtomicBoolean waited = new AtomicBoolean(false);
        new Thread(() -> {
            try {
                Thread.sleep(maxTimeout / 2);
            } catch (InterruptedException ignored) {}
            waited.set(true);
        }).start();
        var answers = content.getPostAnswers(p.getPostId(), maxTimeout).value();
        assertTrue(waited.get());
        assertTrue(answers.isEmpty());
    }

    @Test
    public void shouldInterruptTimeoutPostAnswers() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var maxTimeout = 2000L;
        AtomicBoolean waited = new AtomicBoolean(false);
        new Thread(() -> {
            try {
                Thread.sleep(maxTimeout / 2);
            } catch (InterruptedException ignored) {}
            createValidPost(u, p.getPostId());
            try {
                Thread.sleep((maxTimeout / 2) - (maxTimeout / 10));
            } catch (InterruptedException ignored) {}
            waited.set(true);
        }).start();
        var answers = content.getPostAnswers(p.getPostId(), maxTimeout).value();
        assertFalse(waited.get());
        assertFalse(answers.isEmpty());
    }

    @Test
    public void shouldRejectUpdatePostInvalidPid() {
        var res = content.updatePost(null, "pwd", new Post());
        assertEquals(BAD_REQUEST, res.error());
    }

    @Test
    public void shouldRejectUpdatePostInvalidPwd() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var res = content.updatePost(p.getPostId(), null, new Post());
        assertEquals(FORBIDDEN, res.error());
    }

    @Test
    public void shouldRejectUpdatePostIncorrectPwd() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var res = content.updatePost(p.getPostId(), u.getPassword() + "incorrect", new Post());
        assertEquals(FORBIDDEN, res.error());
    }

    @Test
    public void shouldRejectUpdatePostNotExist() {
        var u = createValidUser();
        var res = content.updatePost("invalid", u.getPassword(), new Post());
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldUpdatePost() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var cpy = new Post(p.getPostId(), p.getAuthorId(), p.getCreationTimestamp(), p.getContent(), p.getMediaUrl(), p.getParentUrl(), p.getUpVote(), p.getDownVote());
        var updatedFields = new Post();
        updatedFields.setContent("new content");
        var updatedPost = content.updatePost(p.getPostId(), u.getPassword(), updatedFields).value();
        assertEquals(cpy.getPostId(), updatedPost.getPostId());
        assertEquals(cpy.getAuthorId(), updatedPost.getAuthorId());
        assertEquals(cpy.getMediaUrl(), updatedPost.getMediaUrl());
        assertEquals(cpy.getParentUrl(), updatedPost.getParentUrl());
        assertEquals(cpy.getUpVote(), updatedPost.getUpVote());
        assertEquals(cpy.getDownVote(), updatedPost.getDownVote());
        assertEquals(updatedFields.getContent(), updatedPost.getContent());
    }

    @Test
    public void shouldRejectUpdateAfterUpvote() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        content.upVotePost(p.getPostId(), u.getUserId(), u.getPassword());
        var updatedFields = new Post();
        updatedFields.setContent("new content");
        var res = content.updatePost(p.getPostId(), u.getPassword(), updatedFields);
        assertEquals(BAD_REQUEST, res.error());
    }

    @Test
    public void shouldRejectUpdateAfterDownvote() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        content.downVotePost(p.getPostId(), u.getUserId(), u.getPassword());
        var updatedFields = new Post();
        updatedFields.setContent("new content");
        var res = content.updatePost(p.getPostId(), u.getPassword(), updatedFields);
        assertEquals(BAD_REQUEST, res.error());
    }

    @Test
    public void shouldRejectUpdateOfParentPost() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        createValidPost(u, p.getPostId());
        var updatedFields = new Post();
        updatedFields.setContent("new content");
        var res = content.updatePost(p.getPostId(), u.getPassword(), updatedFields);
        assertEquals(BAD_REQUEST, res.error());
    }

    @Test
    public void shouldRejectDeletePostInvalidPid() {
        var res = content.deletePost(null, "pwd");
        assertEquals(BAD_REQUEST, res.error());
    }

    @Test
    public void shouldRejectDeletePostInvalidPwd() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var res = content.deletePost(p.getPostId(), null);
        assertEquals(FORBIDDEN, res.error());
    }

    @Test
    public void shouldRejectDeletePostIncorrectPwd() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var res = content.deletePost(p.getPostId(), u.getPassword() + "incorrect");
        assertEquals(FORBIDDEN, res.error());
    }

    @Test
    public void shouldRejectDeletePostNotExist() {
        var u = createValidUser();
        var res = content.deletePost("invalid", u.getPassword());
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldDeletePost() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var res = content.deletePost(p.getPostId(), u.getPassword());
        assertTrue(res.isOK());
    }

    @Test
    public void shouldDeletePostDirectChildren() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var child1 = createValidPost(u, p.getPostId());
        var child2 = createValidPost(u, p.getPostId());
        content.deletePost(p.getPostId(), u.getPassword());
        var res1 = content.getPost(child1.getPostId());
        assertEquals(NOT_FOUND, res1.error());
        var res2 = content.getPost(child2.getPostId());
        assertEquals(NOT_FOUND, res2.error());
    }

    @Test
    public void shouldDeletePostIndirectChildren() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var direct = createValidPost(u, p.getPostId());
        var descendant1 = createValidPost(u, direct.getPostId());
        var descendant2 = createValidPost(u, direct.getPostId());
        content.deletePost(p.getPostId(), u.getPassword());
        var resDirect = content.getPost(direct.getPostId());
        assertEquals(NOT_FOUND, resDirect.error());
        var res1 = content.getPost(descendant1.getPostId());
        assertEquals(NOT_FOUND, res1.error());
        var res2 = content.getPost(descendant2.getPostId());
        assertEquals(NOT_FOUND, res2.error());
    }

    @Test
    public void shouldRejectUpVotePostInvalidPid() {
        var u = createValidUser();
        shouldRejectUpVotePostInvalidInput(null, u.getUserId(), u.getPassword());
    }

    @Test
    public void shouldRejectUpVotePostInvalidUid() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        shouldRejectUpVotePostInvalidInput(p.getPostId(), null, u.getPassword());
    }

    @Test
    public void shouldRejectUpVotePostInvalidPwd() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var res = content.upVotePost(p.getPostId(), u.getUserId(), null);
        assertEquals(FORBIDDEN, res.error());
    }

    private void shouldRejectUpVotePostInvalidInput(String pid, String uid, String pwd) {
        var res = content.upVotePost(pid, uid, pwd);
        assertEquals(BAD_REQUEST, res.error());
    }

    @Test
    public void shouldRejectUpVotePostNotExist() {
        var u = createValidUser();
        var res = content.upVotePost("invalid", u.getUserId(), u.getPassword());
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldRejectUpVotePostUserNotExist() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var res = content.upVotePost(p.getPostId(), "invalid", u.getPassword());
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldRejectUpVoteIncorrectPwd() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var res = content.upVotePost(p.getPostId(), u.getUserId(), u.getPassword() + "incorrect");
        assertEquals(FORBIDDEN, res.error());
    }

    @Test
    public void shouldUpVotePost() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        content.upVotePost(p.getPostId(), u.getUserId(), u.getPassword());
        var retrievedPost = content.getPost(p.getPostId()).value();
        assertEquals(1, retrievedPost.getUpVote());
    }

    @Test
    public void shouldRejectUpVotePostVoteAlreadyCastUpvote() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        content.upVotePost(p.getPostId(), u.getUserId(), u.getPassword());
        var res = content.upVotePost(p.getPostId(), u.getUserId(), u.getPassword());
        assertEquals(CONFLICT, res.error());
    }

    @Test
    public void shouldRejectUpVotePostVoteAlreadyCastDownvote() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        content.downVotePost(p.getPostId(), u.getUserId(), u.getPassword());
        var res = content.upVotePost(p.getPostId(), u.getUserId(), u.getPassword());
        assertEquals(CONFLICT, res.error());
    }

    @Test
    public void shouldRejectRemoveUpVotePostInvalidPid() {
        var u = createValidUser();
        shouldRejectRemoveUpVotePostInvalidInput(null, u.getUserId(), u.getPassword());
    }

    @Test
    public void shouldRejectRemoveUpVotePostInvalidUid() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        shouldRejectRemoveUpVotePostInvalidInput(p.getPostId(), null, u.getPassword());
    }

    @Test
    public void shouldRejectRemoveUpVotePostInvalidPwd() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var res = content.removeUpVotePost(p.getPostId(), u.getUserId(), null);
        assertEquals(FORBIDDEN, res.error());
    }

    private void shouldRejectRemoveUpVotePostInvalidInput(String pid, String uid, String pwd) {
        var res = content.removeUpVotePost(pid, uid, pwd);
        assertEquals(BAD_REQUEST, res.error());
    }

    @Test
    public void shouldRejectRemoveUpVotePostNotExist() {
        var u = createValidUser();
        var res = content.removeUpVotePost("invalid", u.getUserId(), u.getPassword());
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldRejectRemoveUpVoteUserNotExist() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var res = content.removeUpVotePost(p.getPostId(), "invalid", u.getPassword());
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldRejectRemoveUpVoteIncorrectPwd() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var res = content.removeUpVotePost(p.getPostId(), u.getUserId(), u.getPassword() + "incorrect");
        assertEquals(FORBIDDEN, res.error());
    }

    @Test
    public void shouldRejectRemoveUpVotePostVoteNotCast() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var res = content.removeUpVotePost(p.getPostId(), u.getUserId(), u.getPassword());
        assertEquals(CONFLICT, res.error());
    }

    @Test
    public void shouldRemoveUpVotePost() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        content.upVotePost(p.getPostId(), u.getUserId(), u.getPassword());
        content.removeUpVotePost(p.getPostId(), u.getUserId(), u.getPassword());
        var retrievedPost = content.getPost(p.getPostId()).value();
        assertEquals(0, retrievedPost.getUpVote());
    }

    @Test
    public void shouldRejectDownVotePostInvalidPid() {
        var u = createValidUser();
        shouldRejectDownVotePostInvalidInput(null, u.getUserId(), u.getPassword());
    }

    @Test
    public void shouldRejectDownVotePostInvalidUid() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        shouldRejectDownVotePostInvalidInput(p.getPostId(), null, u.getPassword());
    }

    @Test
    public void shouldRejectDownVotePostInvalidPwd() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var res = content.downVotePost(p.getPostId(), u.getUserId(), null);
        assertEquals(FORBIDDEN, res.error());
    }

    private void shouldRejectDownVotePostInvalidInput(String pid, String uid, String pwd) {
        var res = content.downVotePost(pid, uid, pwd);
        assertEquals(BAD_REQUEST, res.error());
    }

    @Test
    public void shouldRejectDownVotePostNotExist() {
        var u = createValidUser();
        var res = content.downVotePost("invalid", u.getUserId(), u.getPassword());
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldRejectDownVotePostUserNotExist() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var res = content.downVotePost(p.getPostId(), "invalid", u.getPassword());
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldRejectDownVoteIncorrectPwd() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var res = content.downVotePost(p.getPostId(), u.getUserId(), u.getPassword() + "incorrect");
        assertEquals(FORBIDDEN, res.error());
    }

    @Test
    public void shouldDownVotePost() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        content.downVotePost(p.getPostId(), u.getUserId(), u.getPassword());
        var retrievedPost = content.getPost(p.getPostId()).value();
        assertEquals(1, retrievedPost.getDownVote());
    }

    @Test
    public void shouldRejectDownVotePostVoteAlreadyCastUpvote() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        content.upVotePost(p.getPostId(), u.getUserId(), u.getPassword());
        var res = content.downVotePost(p.getPostId(), u.getUserId(), u.getPassword());
        assertEquals(CONFLICT, res.error());
    }

    @Test
    public void shouldRejectDownVotePostVoteAlreadyCastDownvote() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        content.downVotePost(p.getPostId(), u.getUserId(), u.getPassword());
        var res = content.downVotePost(p.getPostId(), u.getUserId(), u.getPassword());
        assertEquals(CONFLICT, res.error());
    }

    @Test
    public void shouldRejectRemoveDownVotePostInvalidPid() {
        var u = createValidUser();
        shouldRejectRemoveDownVotePostInvalidInput(null, u.getUserId(), u.getPassword());
    }

    @Test
    public void shouldRejectRemoveDownVotePostInvalidUid() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        shouldRejectRemoveDownVotePostInvalidInput(p.getPostId(), null, u.getPassword());
    }

    @Test
    public void shouldRejectRemoveDownVotePostInvalidPwd() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var res = content.removeDownVotePost(p.getPostId(), u.getUserId(), null);
        assertEquals(FORBIDDEN, res.error());
    }

    private void shouldRejectRemoveDownVotePostInvalidInput(String pid, String uid, String pwd) {
        var res = content.removeDownVotePost(pid, uid, pwd);
        assertEquals(BAD_REQUEST, res.error());
    }

    @Test
    public void shouldRejectRemoveDownVotePostNotExist() {
        var u = createValidUser();
        var res = content.removeDownVotePost("invalid", u.getUserId(), u.getPassword());
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldRejectRemoveDownVoteUserNotExist() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var res = content.removeDownVotePost(p.getPostId(), "invalid", u.getPassword());
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldRejectRemoveDownVoteIncorrectPwd() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var res = content.removeDownVotePost(p.getPostId(), u.getUserId(), u.getPassword() + "incorrect");
        assertEquals(FORBIDDEN, res.error());
    }

    @Test
    public void shouldRejectRemoveDownVotePostVoteNotCast() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var res = content.removeDownVotePost(p.getPostId(), u.getUserId(), u.getPassword());
        assertEquals(CONFLICT, res.error());
    }

    @Test
    public void shouldRemoveDownVotePost() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        content.downVotePost(p.getPostId(), u.getUserId(), u.getPassword());
        content.removeDownVotePost(p.getPostId(), u.getUserId(), u.getPassword());
        var retrievedPost = content.getPost(p.getPostId()).value();
        assertEquals(0, retrievedPost.getDownVote());
    }

    @Test
    public void shouldRejectGetUpVotesInvalidPid() {
        var res = content.getupVotes(null);
        assertEquals(BAD_REQUEST, res.error());
    }

    @Test
    public void shouldRejectGetUpVotesPostNotExist() {
        var res = content.getupVotes("invalid");
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldGetUpVotesZero() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        int upVotes = content.getupVotes(p.getPostId()).value();
        assertEquals(0, upVotes);
    }

    @Test
    public void shouldGetUpVotesMany() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var nVoters = 10;
        for (int i = 0; i < nVoters; i++) {
            var voter = createValidUser();
            var res = content.upVotePost(p.getPostId(), voter.getUserId(), voter.getPassword());
            assertTrue(res.isOK());
        }
        int upVotes = content.getupVotes(p.getPostId()).value();
        assertEquals(nVoters, upVotes);
    }

    @Test
    public void shouldRejectGetDownVotesInvalidPid() {
        var res = content.getDownVotes(null);
        assertEquals(BAD_REQUEST, res.error());
    }

    @Test
    public void shouldRejectGetDownVotesPostNotExist() {
        var res = content.getDownVotes("invalid");
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldGetDownVotesZero() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        int downVotes = content.getDownVotes(p.getPostId()).value();
        assertEquals(0, downVotes);
    }

    @Test
    public void shouldGetDownVotesMany() {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var nVoters = 10;
        for (int i = 0; i < nVoters; i++) {
            var voter = createValidUser();
            var res = content.downVotePost(p.getPostId(), voter.getUserId(), voter.getPassword());
            assertTrue(res.isOK());
        }
        int downVotes = content.getDownVotes(p.getPostId()).value();
        assertEquals(nVoters, downVotes);
    }

    @Test
    public void shouldRejectGetPostsInvalidSortOption() {
        var res = content.getPosts(0, "invalid");
        assertEquals(BAD_REQUEST, res.error());
    }

    @Test
    public void shouldGetPostsSingleDefaultOrder() {
        shouldGetPostsSingle(null);
    }

    @Test
    public void shouldGetPostsSingleMostUpVotes() {
        shouldGetPostsSingle(MOST_UP_VOTES);
    }

    @Test
    public void shouldGetPostsSingleMostReplies() {
        shouldGetPostsSingle(MOST_REPLIES);
    }

    private void shouldGetPostsSingle(String sortOrder) {
        var u = createValidUser();
        var p = createValidPost(u, null);
        var posts = content.getPosts(0, sortOrder).value();
        assertEquals(1, posts.size());
        assertEquals(p.getPostId(), posts.get(0));
    }

    @Test
    public void shouldGetPostsMultipleDefaultOrder() {
        shouldGetPostsMultiple(null);
    }

    @Test
    public void shouldGetPostsMultipleMostUpVotes() {
        shouldGetPostsMultiple(MOST_UP_VOTES);
    }

    @Test
    public void shouldGetPostsMultipleMostReplies() {
        shouldGetPostsMultiple(MOST_REPLIES);
    }

    private void shouldGetPostsMultiple(String sortOrder) {
        var u = createValidUser();
        var numPosts = 10;
        for (int i = 0; i < numPosts; i++)
            createValidPost(u, null);
        var posts = content.getPosts(0, sortOrder).value();
        assertEquals(numPosts, posts.size());
    }

    @Test
    public void shouldGetPostsExcludingOlderDefaultOrder() {
        shouldGetPostsExcludingOlder(null);
    }

    @Test
    public void shouldGetPostsExcludingOlderMostUpVotes() {
        shouldGetPostsExcludingOlder(MOST_UP_VOTES);
    }

    @Test
    public void shouldGetPostsExcludingOlderMostReplies() {
        shouldGetPostsExcludingOlder(MOST_REPLIES);
    }

    private void shouldGetPostsExcludingOlder(String sortOrder) {
        var u = createValidUser();
        var numPosts = 10;
        for (int i = 0; i < numPosts; i++)
            createValidPost(u, null);
        sleepALittle();
        var timestamp = System.currentTimeMillis();
        sleepALittle();
        for (int i = 0; i < numPosts; i++)
            createValidPost(u, null);
        var posts = content.getPosts(timestamp, sortOrder).value();
        assertEquals(numPosts, posts.size());
    }

    @Test
    public void shouldGetPostsOrderedByTimestamp() {
        var u = createValidUser();
        var numPosts = 5;
        List<Post> posts = new ArrayList<>(numPosts);
        for (int i = 0; i < numPosts; i++) {
            posts.add(createValidPost(u, null));
            sleepALittle();
        }
        var retrievedPids = content.getPosts(0, null).value();
        for (int i = 0; i < numPosts; i++) {
            var p = content.getPost(retrievedPids.get(i)).value();
            assertEquals(posts.get(i).getPostId(), p.getPostId());
        }
    }

    private void sleepALittle() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            fail();
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldGetPostsOrderedByReplies() {
        var u = createValidUser();
        var numPosts = 10;
        var topPids = new ArrayList<String>(numPosts);
        for (int i = 0; i < numPosts; i++) {
            var p = createValidPost(u, null);
            topPids.add(p.getPostId());
        }
        var numReplies = 100;
        for (int i = 0; i < numReplies; i++) {
            var parent = topPids.get(new Random().nextInt(numPosts));
            createValidPost(u, parent);
        }
        createValidPost(u, null); //<- Post with no replies
        var retrievedPids = content.getPosts(0, MOST_REPLIES).value();
        content.getPost(retrievedPids.get(2));
        var offsetPosts = retrievedPids.stream().skip(1).toList();
        for (int i = 0; i < offsetPosts.size(); i++)
            assertTrue(countReplies(offsetPosts.get(i)) <= countReplies(retrievedPids.get(i)));
    }

    private int countReplies(String pid) {
        return content.getPostAnswers(pid, 0).value().size();
    }

    @Test
    public void shouldGetPostsOrderedByNumUpvotes() {
        var u = createValidUser();
        var numPosts = 10;
        var postIds = new ArrayList<String>(numPosts);
        for (int i = 0; i < numPosts; i++)
            postIds.add(createValidPost(u, null).getPostId());
        var totalUpvotes = 100;
        var rand = new Random();
        for (int i = 0; i < totalUpvotes; i++) {
            var voter = createValidUser();
            var pid = postIds.get(rand.nextInt(postIds.size()));
            var res = content.upVotePost(pid, voter.getUserId(), voter.getPassword());
            assertTrue(res.isOK());
        }
        for (int i = 0; i < totalUpvotes * 2; i++) {
            var voter = createValidUser();
            var pid = postIds.get(rand.nextInt(postIds.size()));
            var res = content.downVotePost(pid, voter.getUserId(), voter.getPassword());
            assertTrue(res.isOK());
        }
        createValidPost(u, null); // <- Post with no upvotes
        var retrievedPids = content.getPosts(0, MOST_UP_VOTES).value();
        assertEquals(numPosts + 1, retrievedPids.size());
        var retrievedPosts = getPostsFromIds(retrievedPids);
        var offsetPosts = retrievedPosts.stream().skip(1).toList();
        for (int i = 0; i < offsetPosts.size(); i++)
            assertTrue(offsetPosts.get(i).getUpVote() <= retrievedPosts.get(i).getUpVote());
    }

    private List<Post> getPostsFromIds(Collection<String> pids) {
        return pids.stream().map(pid -> content.getPost(pid).value()).toList();
    }
    
    private User createValidUser() {
        var uid = UUID.randomUUID().toString();
        var u = new User(uid, "name", "email", "password");
        try {
            users.createUser(u);
        } catch (Exception e) {
            fail();
            throw new RuntimeException(e);
        }
        return u;
    }

    private Post createValidPost(User u, String parent) {
        parent = parent != null ? getUriPrefix(parent) : null;
        var media = "media" + new Random().nextInt();
        var p = new Post(u.getUserId(), media, parent);
        p.setPostId(content.createPost(p, u.getPassword()).value());
        return p;
    }

    public abstract String getUriPrefix(String id);

}
