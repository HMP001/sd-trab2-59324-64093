package server.grpc;

import impl.JavaImage;
import client.UsersClient;
import com.google.protobuf.ByteString;
import io.grpc.BindableService;
import io.grpc.ServerServiceDefinition;
import io.grpc.stub.StreamObserver;
import jakarta.ws.rs.core.UriBuilder;
import network.grpc.ImageGrpc;

import static network.grpc.ImageProtoBuf.*;
import static server.grpc.GrpcServerUtils.unwrapResult;

public class GrpcImageStub implements ImageGrpc.AsyncService, BindableService {

    private final String baseUri;

    private final JavaImage images;

    public GrpcImageStub(String baseUri) {
        this.baseUri = baseUri;
        images = new JavaImage();
        images.setUsers(UsersClient.getInstance());
    }

    @Override
    public ServerServiceDefinition bindService() {
        return ImageGrpc.bindService(this);
    }

    @Override
    public void createImage(CreateImageArgs req, StreamObserver<CreateImageResult> obs) {
            var content = req.getImageContents().toByteArray();
            var res = images.createImage(req.getUserId(), content, req.getPassword());
            unwrapResult(obs, res, () -> {
                var relativeUri = res.value();
                var uri = UriBuilder.fromUri(baseUri).path(relativeUri).build().toASCIIString();
                obs.onNext(CreateImageResult.newBuilder().setImageId(uri).build());
            });
    }

    @Override
    public void getImage(GetImageArgs req, StreamObserver<GetImageResult> obs) {
        var res = images.getImage(req.getUserId(), req.getImageId());
        unwrapResult(obs, res, () -> {
            var content = res.value();
            var grpcContent = ByteString.copyFrom(content);
            obs.onNext(GetImageResult.newBuilder().setData(grpcContent).build());
        });
    }

    @Override
    public void deleteImage(DeleteImageArgs req, StreamObserver<DeleteImageResult> obs) {
        var res = images.deleteImage(req.getUserId(), req.getImageId(), req.getPassword());
        unwrapResult(obs, res, () -> obs.onNext(DeleteImageResult.getDefaultInstance()));
    }

    @Override
    public void deleteImageUponUserOrPostRemoval(DelUponUsrRemArgs req, StreamObserver<DelUponUsrRemResult> obs) {
        var res = images.deleteImageUponUserOrPostRemoval(req.getUserId(), req.getImageId());
        unwrapResult(obs, res, () -> obs.onNext(DelUponUsrRemResult.getDefaultInstance()));
    }

    @Override
    public void teardown(TeardownArgs req, StreamObserver<TeardownResult> obs) {
        images.teardown();
        obs.onNext(TeardownResult.getDefaultInstance());
        obs.onCompleted();
   }
}
