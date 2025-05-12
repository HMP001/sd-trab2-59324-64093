package server.grpc;

import client.ContentClient;
import impl.JavaUsers;
import client.ImageClient;
import network.DataModelAdaptor;
import io.grpc.BindableService;
import io.grpc.ServerServiceDefinition;
import io.grpc.stub.StreamObserver;
import network.grpc.UsersGrpc;

import static network.grpc.UsersProtoBuf.*;
import static server.grpc.GrpcServerUtils.unwrapResult;


public class GrpcUsersStub implements UsersGrpc.AsyncService, BindableService {

    private final JavaUsers users = new JavaUsers();

    public GrpcUsersStub() {
        var contentService = ContentClient.getInstance();
        var imgService = ImageClient.getInstance();
        users.setContents(contentService);
        users.setImages(imgService);
    }

    @Override
    public ServerServiceDefinition bindService() {
        return UsersGrpc.bindService(this);
    }

    @Override
    public void createUser(CreateUserArgs req, StreamObserver<CreateUserResult> obs) {
        var u = DataModelAdaptor.GrpcUser_to_User(req.getUser());
        var res = users.createUser(u);
        unwrapResult(obs, res, () -> {
            var out = CreateUserResult.newBuilder().setUserId(res.value()).build();
            obs.onNext(out);
        });
    }

    @Override
    public void getUser(GetUserArgs req, StreamObserver<GetUserResult> obs) {
        var res = users.getUser(req.getUserId(), req.getPassword());
        unwrapResult(obs, res, () -> {
            var u = res.value();
            var grpcU = DataModelAdaptor.User_to_GrpcUser(u);
            obs.onNext(GetUserResult.newBuilder().setUser(grpcU).build());
        });
    }

    @Override
    public void updateUser(UpdateUserArgs req, StreamObserver<UpdateUserResult> obs) {
        var updatedFields = DataModelAdaptor.GrpcUser_to_User(req.getUser());
        var res = users.updateUser(req.getUserId(), req.getPassword(), updatedFields);
        unwrapResult(obs, res, () -> {
            var u = res.value();
            var grpcU = DataModelAdaptor.User_to_GrpcUser(u);
            obs.onNext(UpdateUserResult.newBuilder().setUser(grpcU).build());
        });
    }

    @Override
    public void deleteUser(DeleteUserArgs req, StreamObserver<DeleteUserResult> obs) {
        var res = users.deleteUser(req.getUserId(), req.getPassword());
        unwrapResult(obs, res, () -> {
            var u = res.value();
            var grpcU = DataModelAdaptor.User_to_GrpcUser(u);
            obs.onNext(DeleteUserResult.newBuilder().setUser(grpcU).build());
        });
    }

    @Override
    public void searchUsers(SearchUserArgs req, StreamObserver<GrpcUser> obs) {
        var res = users.searchUsers(req.getPattern());
        unwrapResult(obs, res, () -> {
            var us = res.value();
            us.stream().map(DataModelAdaptor::User_to_GrpcUser).forEach(obs::onNext);
        });
    }

}
