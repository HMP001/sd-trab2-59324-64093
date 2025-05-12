package service;

import api.User;
import api.java.Users;
import impl.Hibernate;
import network.DiscoveryListener;
import org.junit.After;
import org.junit.Test;

import static api.java.Result.ErrorCode.*;
import static org.junit.Assert.*;

public abstract class UsersTests {

    protected Users users;

    @After
    public void teardown() {
        Hibernate.teardown();
        DiscoveryListener.teardown();
    }

    @Test
    public void shouldRejectCreationInvalidUid() {
        var invalidUid = new User(null, "", "", "");
        var res = users.createUser(invalidUid);
        assertEquals(BAD_REQUEST, res.error());
    }

    @Test
    public void shouldRejectCreationInvalidName() {
        var invalidName = new User("uid", null, "", "");
        var res = users.createUser(invalidName);
        assertEquals(BAD_REQUEST, res.error());
    }

    @Test
    public void shouldRejectCreationInvalidEmail() {
        var invalidEmail = new User("uid", "", null, "");
        var res = users.createUser(invalidEmail);
        assertEquals(BAD_REQUEST, res.error());
    }

    @Test
    public void shouldRejectCreationInvalidPassword() {
        var invalidPassword = new User("uid", "", "", null);
        var res = users.createUser(invalidPassword);
        assertEquals(BAD_REQUEST, res.error());
    }

    @Test
    public void shouldCreateValidUserAvatarNotNull() {
        shouldCreateValidUser("avatar");
    }

    @Test
    public void shouldCreateWhenAvatarIsNull() {
        shouldCreateValidUser(null);
    }

    private void shouldCreateValidUser(String avatarUrl) {
        var uid = "uid";
        var validUser = new User(uid, "name", "email", "pwd", avatarUrl);
        var received = users.createUser(validUser);
        assertEquals(uid, received.value());
    }

    @Test
    public void shouldRejectCreationExistingUser() {
        var existingUser = new User("uid", "pwd", "email", "name");
        users.createUser(existingUser);
        var res = users.createUser(existingUser);
        assertEquals(CONFLICT, res.error());
    }

    @Test
    public void shouldRejectGetUserInvalidUid() {
        createValidUser();
        var res = users.getUser(null, "pwd");
        assertEquals(BAD_REQUEST, res.error());
    }

    @Test
    public void shouldRejectGetUserInvalidPwd() {
        var u = createValidUser();
        var res = users.getUser(u.getUserId(), null);
        assertEquals(FORBIDDEN, res.error());
    }

    @Test
    public void shouldRejectGetUserNonExistentUser() {
        var res = users.getUser("uid", "pwd");
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldRejectGetUserIncorrectPwd() {
        var u = createValidUser();
        var res = users.getUser(u.getUserId(), "wrongPwd");
        assertEquals(FORBIDDEN, res.error());
    }

    @Test
    public void shouldAcceptGetUser() {
        var u = createValidUser();
        var received = users.getUser(u.getUserId(), u.getPassword());
        assertEquals(u, received.value());
    }

    @Test
    public void shouldRejectUpdateUserInvalidUid() {
        createValidUser();
        var res = users.updateUser(null, "pwd", new User("uid", "name", "email", "pwd"));
        assertEquals(BAD_REQUEST, res.error());
    }

    @Test
    public void shouldRejectUpdateUserInvalidPwd() {
        var u = createValidUser();
        var res = users.updateUser(u.getUserId(), null, new User("uid", "name", "email", "pwd"));
        assertEquals(FORBIDDEN, res.error());
    }

    @Test
    public void shouldRejectUpdateUserNonExistentUser() {
        var res = users.updateUser("uid", "pwd", new User("uid", "name", "email", "pwd"));
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldRejectUpdateUserIncorrectPwd() {
        var u = createValidUser();
        var res = users.updateUser(u.getUserId(), "wrongPwd", new User("uid", "name", "email", "pwd"));
        assertEquals(FORBIDDEN, res.error());
    }

    @Test
    public void shouldUpdateUser() {
        var u = createValidUser();
        var updated = new User(u.getUserId(), "newName", "newEmail", "newPwd", "newAvatar");
        var received = users.updateUser(u.getUserId(), u.getPassword(), updated);
        assertEquals(updated, received.value());
    }

    @Test
    public void shouldUpdateMaintainingNonUpdatedFields() {
        var u = createValidUser();
        var oldCpy = new User(u.getUserId(), u.getFullName(), u.getEmail(), u.getPassword(), u.getAvatarUrl());
        var updated = new User(null, "newName", null, null, null);
        var received = users.updateUser(u.getUserId(), u.getPassword(), updated).value();
        assertNotEquals(updated, received);
        assertNotEquals(oldCpy, received);
        assertEquals(oldCpy.getUserId(), received.getUserId());
        assertEquals(updated.getFullName(), received.getFullName());
        assertEquals(oldCpy.getEmail(), received.getEmail());
        assertEquals(oldCpy.getPassword(), received.getPassword());
        assertEquals(oldCpy.getAvatarUrl(), received.getAvatarUrl());
    }

    @Test
    public void shouldRejectDeleteUserInvalidUid() {
        createValidUser();
        var res = users.deleteUser(null, "pwd");
        assertEquals(BAD_REQUEST, res.error());
    }

    @Test
    public void shouldRejectDeleteUserInvalidPwd() {
        var u = createValidUser();
        var res = users.deleteUser(u.getUserId(), null);
        assertEquals(FORBIDDEN, res.error());
    }

    @Test
    public void shouldRejectDeleteUserNonExistentUser() {
        var res = users.deleteUser("uid", "pwd");
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldRejectDeleteUserIncorrectPwd() {
        var u = createValidUser();
        var res = users.deleteUser(u.getUserId(), "wrongPwd");
        assertEquals(FORBIDDEN, res.error());
    }

    @Test
    public void shouldDeleteUser() {
        var u = createValidUser();
        var received = users.deleteUser(u.getUserId(), u.getPassword());
        assertEquals(u, received.value());
        var res = users.getUser(u.getUserId(), u.getPassword());
        assertEquals(NOT_FOUND, res.error());
    }

    @Test
    public void shouldSearchAllUsersEmptyPattern() {
        var nUsers = 10;
        for (int i = 0; i < nUsers; i++) {
            createValidUser();
        }
        var received = users.searchUsers("").value();
        assertEquals(nUsers, received.size());
    }

    @Test
    public void shouldSearchAllUsersNullPattern() {
        var nUsers = 10;
        for (int i = 0; i < nUsers; i++) {
            createValidUser();
        }
        var received = users.searchUsers(null).value();
        assertEquals(nUsers, received.size());
    }

    @Test
    public void shouldSearchHidePassword() {
        var u = createValidUser();
        var received = users.searchUsers("").value();
        assertEquals(1, received.size());
        assertNotEquals(u, received.get(0));
        assertNull(received.get(0).getPassword());
    }

    @Test
    public void shouldSearchExactMatch() {
        var nUsers = 10;
        for (int i = 0; i < nUsers; i++) {
            createValidUser();
        }
        var pattern = "verySpecificNameLikeWhatAreTheOdds";
        var u = new User("uid", pattern, "email", "pwd");
        try {
            users.createUser(u);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            fail();
        }
        var received = users.searchUsers(pattern).value();
        assertEquals(1, received.size());
        assertEquals(u.getUserId(), received.get(0).getUserId());
    }

    @Test
    public void shouldSearchPartialMatch() {
        var nUsers = 10;
        for (int i = 0; i < nUsers; i++) {
            createValidUser();
        }
        var pattern = "verySpecificNameLikeWhatAreTheOdds";
        var u0 = new User("u0", "prefix" + pattern, "email", "pwd");
        var u1 = new User("u1", pattern + "suffix", "email", "pwd");
        try {
            users.createUser(u0);
            users.createUser(u1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            fail();
        }
        var received = users.searchUsers(pattern).value();
        assertEquals(2, received.size());
    }

    @Test
    public void shouldSearchCaseInsensitive() {
        var pattern = "verySpecificNameLikeWhatAreTheOdds";
        var u0 = new User("u0", pattern.toUpperCase(), "email", "pwd");
        var u1 = new User("u1", pattern.toLowerCase(), "email", "pwd");
        try {
            users.createUser(u0);
            users.createUser(u1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            fail();
        }
        var received = users.searchUsers(pattern.toLowerCase()).value();
        assertEquals(2, received.size());
        assertNotEquals(pattern, received.get(0).getFullName());
        assertNotEquals(pattern, received.get(1).getFullName());
    }

    User createValidUser() {
        var rnd = new java.util.Random();
        var uid = "uid" + rnd.nextInt();
        var name = "name" + rnd.nextInt();
        var email = "email" + rnd.nextInt();
        var pwd = "pwd" + rnd.nextInt();
        var u = new User(uid, name, email, pwd, null);
        var res = users.createUser(u);
        assertTrue(res.isOK());
        return u;
    }

}
