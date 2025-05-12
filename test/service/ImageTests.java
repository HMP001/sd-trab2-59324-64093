package service;

import api.User;
import api.java.Image;
import api.java.Users;
import impl.Hibernate;
import network.DiscoveryListener;
import org.junit.After;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

import static api.java.Result.ErrorCode.*;
import static org.junit.Assert.*;

public abstract class ImageTests {

    protected Image images;

    protected Users users;

    @After
    public void teardown() {
        Hibernate.teardown();
        images.teardown();
        DiscoveryListener.teardown();
    }

    @Test
    public void shouldRejectCreationInvalidUid() {
        shouldRejectCreationInvalidInput(null, randomByteString(), "pwd");
    }

    @Test
    public void shouldRejectCreationNullContent() {
        shouldRejectCreationInvalidInput("uid", null, "pwd");
    }

    @Test
    public void shouldRejectCreationEmptyContent() {
        shouldRejectCreationInvalidInput("uid", new byte[0], "pwd");
    }

    @Test
    public void shouldRejectCreationInvalidPwd() {
        var res = images.createImage("uid", randomByteString(), null);
        assertEquals(FORBIDDEN, res.error());
    }

    private void shouldRejectCreationInvalidInput(String uid, byte[] content, String pwd) {
        var res = images.createImage(uid, content, pwd);
        assertEquals(BAD_REQUEST, res.error());
    }

    @Test
    public void shouldRejectCreationUserNotExist() {
        var res = images.createImage("uid", randomByteString(), "pwd");
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldRejectCreationIncorrectPwd() {
        var u = createValidUser();
        var res = images.createImage(u.getUserId(), randomByteString(), u.getPassword() + "incorrect");
        assertEquals(FORBIDDEN, res.error());
    }

    @Test
    public void shouldAcceptCreation() {
        var u = createValidUser();
        var content = randomByteString();
        createValidImg(u.getUserId(), content, u.getPassword());
    }

    @Test
    public void shouldRejectGetInvalidUid() {
        var res = images.getImage(null, null);
        assertEquals(BAD_REQUEST, res.error());
    }

    @Test
    public void shouldRejectGetImgNotExists() {
        var u = createValidUser();
        var res = images.getImage(u.getUserId(), "iid");
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldGetImage() {
        var u = createValidUser();
        var content = randomByteString();
        var uri = createValidImg(u.getUserId(), content, u.getPassword());
        var splitUri = uri.split("/");
        var uid = splitUri[splitUri.length - 2];
        var iid = splitUri[splitUri.length - 1];
        var received = images.getImage(uid, iid).value();
        assertArrayEquals(content, received);
    }

    @Test
    public void shouldRejectDeleteInvalidUid() {
        shouldRejectDeleteInvalidInput(null, "iid", "pwd");
    }

    @Test
    public void shouldRejectDeleteInvalidIid() {
        shouldRejectDeleteInvalidInput("uid", null, "pwd");
    }

    @Test
    public void shouldRejectDeleteInvalidPwd() {
        var res = images.deleteImage("uid", "iid", null);
        assertEquals(FORBIDDEN, res.error());
    }

    private void shouldRejectDeleteInvalidInput(String uid, String iid, String pwd) {
        var res = images.deleteImage(uid, iid, pwd);
        assertEquals(BAD_REQUEST, res.error());
    }

    @Test
    public void shouldRejectDeleteUserNotExists() {
        var res = images.deleteImage("uid", "iid", "pwd");
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldRejectDeleteImgNotExists() {
        var u = createValidUser();
        var res = images.deleteImage(u.getUserId(), "iid", u.getPassword());
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldRejectDeleteIncorrectPwd() {
        var u = createValidUser();
        var uri = createValidImg(u.getUserId(), randomByteString(), u.getPassword());
        var splitUri = uri.split("/");
        var uid = splitUri[splitUri.length - 2];
        var iid = splitUri[splitUri.length - 1];
        var res = images.deleteImage(uid, iid, u.getPassword() + "incorrect");
        assertEquals(FORBIDDEN, res.error());
    }

    @Test
    public void shouldAcceptDelete() {
        var u = createValidUser();
        var uri = createValidImg(u.getUserId(), randomByteString(), u.getPassword());
        var splitUri = uri.split("/");
        var iid = splitUri[splitUri.length - 1];
        images.deleteImage(u.getUserId(), iid, u.getPassword());
        var res = images.getImage(u.getUserId(), iid);
        assertEquals(NOT_FOUND, res.error());
    }

    protected String createValidImg(String uid, byte[] content, String pwd) {
        return images.createImage(uid, content, pwd).value();
    }

    protected User createValidUser() {
        var uid = UUID.randomUUID().toString();
        var u = new User(uid, "name", "email", "password");
        var res = users.createUser(u);
        assertTrue(res.isOK());
        return u;
    }

    protected byte[] randomByteString() {
        var len = 10;
        var out = new byte[len];
        var rnd = new Random();
        rnd.nextBytes(out);
        return out;
    }

}
