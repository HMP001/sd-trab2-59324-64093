package client.grpc;

import api.User;
import api.java.Result;
import api.java.Users;
import network.DataModelAdaptor;
import io.grpc.*;
import io.grpc.internal.PickFirstLoadBalancerProvider;
import network.grpc.UsersGrpc;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static api.java.Result.ErrorCode.BAD_REQUEST;
import static api.java.Result.ErrorCode.FORBIDDEN;
import static api.java.Result.error;
import static api.java.Result.ok;
import static client.grpc.GrpcClientUtils.wrapRequest;
import static network.grpc.UsersProtoBuf.*;

public class GrpcUsersClient implements Users {

    static {
        LoadBalancerRegistry.getDefaultRegistry().register(new PickFirstLoadBalancerProvider());
    }

    private final UsersGrpc.UsersBlockingStub stub;

    public GrpcUsersClient(URI serverURI) {
        var channel = ManagedChannelBuilder.forAddress(serverURI.getHost(), serverURI.getPort()).enableRetry().usePlaintext().build();
        this.stub = UsersGrpc.newBlockingStub(channel);
    }

    @Override
    public Result<String> createUser(User u) {
        return wrapRequest(() -> {
            var grpcU = DataModelAdaptor.User_to_GrpcUser(u);
            var args = CreateUserArgs.newBuilder().setUser(grpcU).build();
            return ok(stub.createUser(args).getUserId());
        });
    }

    @Override
    public Result<User> getUser(String uid, String pwd) {
        if (uid == null)
            return error(BAD_REQUEST);
        if (pwd == null)
            return error(FORBIDDEN);
        return wrapRequest(() -> {
            var args = GetUserArgs.newBuilder().setUserId(uid).setPassword(pwd).build();
            var grpcU = stub.getUser(args).getUser();
            return ok(DataModelAdaptor.GrpcUser_to_User(grpcU));
        });
    }

    @Override
    public Result<User> updateUser(String uid, String pwd, User updatedFields) {
        if (uid == null)
            return error(BAD_REQUEST);
        if (pwd == null)
            return error(FORBIDDEN);
        return wrapRequest(() -> {
            var grpcUF = DataModelAdaptor.User_to_GrpcUser(updatedFields);
            var args = UpdateUserArgs.newBuilder().setUserId(uid).setPassword(pwd).setUser(grpcUF).build();
            var grpcUser = stub.updateUser(args).getUser();
            return ok(DataModelAdaptor.GrpcUser_to_User(grpcUser));
        });
    }

    @Override
    public Result<User> deleteUser(String uid, String pwd) {
        if (uid == null)
            return error(BAD_REQUEST);
        if (pwd == null)
            return error(FORBIDDEN);
        return wrapRequest(() -> {
            var args = DeleteUserArgs.newBuilder().setUserId(uid).setPassword(pwd).build();
            var grpcUser = stub.deleteUser(args).getUser();
            return ok(DataModelAdaptor.GrpcUser_to_User(grpcUser));
        });
    }

    @Override
    public Result<List<User>> searchUsers(String pattern) {
        pattern = pattern != null ? pattern : "";
        var args = SearchUserArgs.newBuilder().setPattern(pattern).build();
        return wrapRequest(() -> {
            var userIterator = stub.searchUsers(args);
            List<GrpcUser> grpcUsers = new ArrayList<>();
            userIterator.forEachRemaining(grpcUsers::add);
            return ok(grpcUsers.stream().map(DataModelAdaptor::GrpcUser_to_User).toList());
        });
    }
}
