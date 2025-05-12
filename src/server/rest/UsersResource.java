package server.rest;

import api.User;
import api.rest.RestUsers;
import client.ContentClient;
import impl.JavaUsers;
import client.ImageClient;

import java.util.List;

import static server.rest.RestServerUtils.wrapResult;

public class UsersResource implements RestUsers {

    private final JavaUsers users = new JavaUsers();

    public UsersResource() {
        users.setContents(ContentClient.getInstance());
        users.setImages(ImageClient.getInstance());
    }

    @Override
    public String createUser(User user) {
        return wrapResult(users.createUser(user));
    }

    @Override
    public User getUser(String userId, String password) {
        return wrapResult(users.getUser(userId, password));
    }

    @Override
    public User updateUser(String userId, String password, User user) {
        return wrapResult(users.updateUser(userId, password, user));
    }

    @Override
    public User deleteUser(String userId, String password) {
        return wrapResult(users.deleteUser(userId, password));
    }

    @Override
    public List<User> searchUsers(String pattern) {
        return wrapResult(users.searchUsers(pattern));
    }
}
