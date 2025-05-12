package client;

import api.User;
import api.java.Result;
import api.java.Users;
import client.grpc.GrpcUsersClient;
import client.rest.RestUsersClient;

import java.util.List;

public class UsersClient implements Users {

    public static final String SERVICE = "Users";

    private final ClientLauncher launcher = new ClientLauncher();

    private volatile Users inner;

    private static UsersClient singleton;

    public static UsersClient getInstance() {
        if (singleton == null) {
            synchronized (UsersClient.class) {
                if (singleton == null)
                    singleton = new UsersClient();
            }
        }
        return singleton;
    }

    private UsersClient() {}

    @Override
    public Result<String> createUser(User u) {
        if (inner == null)
            materializeChannel();
        return inner.createUser(u);
    }

    @Override
    public Result<User> getUser(String uid, String pwd) {
        if (inner == null)
            materializeChannel();
        return inner.getUser(uid, pwd);
    }

    @Override
    public Result<User> updateUser(String uid, String pwd, User updatedFields) {
        if (inner == null)
            materializeChannel();
        return inner.updateUser(uid, pwd, updatedFields);
    }

    @Override
    public Result<User> deleteUser(String uid, String pwd) {
        if (inner == null)
            materializeChannel();
        return inner.deleteUser(uid, pwd);
    }

    @Override
    public Result<List<User>> searchUsers(String pattern) {
        if (inner == null)
            materializeChannel();
        return inner.searchUsers(pattern);
    }

    private void materializeChannel() {
        synchronized (this) {
            if (inner == null)
                inner = computeInnerChannel();
        }
    }

    private Users computeInnerChannel() {
        return launcher.launch(SERVICE, RestUsersClient::new, GrpcUsersClient::new);
    }
}
