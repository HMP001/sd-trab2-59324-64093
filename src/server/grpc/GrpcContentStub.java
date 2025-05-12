package server.grpc;

import api.Post;
import client.ImageClient;
import client.UsersClient;
import impl.JavaContent;
import io.grpc.BindableService;
import io.grpc.ServerServiceDefinition;
import io.grpc.stub.StreamObserver;
import jakarta.ws.rs.core.UriBuilder;
import network.DataModelAdaptor;
import network.grpc.ContentGrpc;

import network.grpc.ContentProtoBuf.*;

import java.net.URI;

import static network.DataModelAdaptor.incorporateUrlToId;
import static server.grpc.GrpcServerUtils.unwrapResult;


public class GrpcContentStub implements ContentGrpc.AsyncService, BindableService {

    private final URI baseUri;

    private final JavaContent contents = new JavaContent();

    public GrpcContentStub(String baseUri) {
        this.baseUri = UriBuilder.fromUri(baseUri).build();
        contents.setUsers(UsersClient.getInstance());
        contents.setImages(ImageClient.getInstance());
    }

    @Override
    public ServerServiceDefinition bindService() {
        return ContentGrpc.bindService(this);
    }

    @Override
    public void createPost(CreatePostArgs req, StreamObserver<CreatePostResult> obs) {
        var post = DataModelAdaptor.GrpcPost_to_Post(req.getPost());
        hideParentUrl(post);
        var res = contents.createPost(post, req.getPassword());
        unwrapResult(obs, res, () -> {
            var pid = res.value();
            obs.onNext(CreatePostResult.newBuilder().setPostId(pid).build());
        });
    }

    private static void hideParentUrl(Post post) {
        var parentUrl = post.getParentUrl();
        if (parentUrl != null)
            post.setParentUrl(DataModelAdaptor.extractIdFromUrl(parentUrl));
    }

    @Override
    public void getPosts(GetPostsArgs req, StreamObserver<GetPostsResult> obs) {
        var res = contents.getPosts(req.getTimestamp(), req.getSortOrder());
        unwrapResult(obs, res, () -> {
            var pids = res.value();
            var grpcRes = GetPostsResult.newBuilder().addAllPostId(pids).build();
            obs.onNext(grpcRes);
        });
    }

    @Override
    public void getPost(GetPostArgs req, StreamObserver<GrpcPost> obs) {
        var res = contents.getPost(req.getPostId());
        unwrapResult(obs, res, () -> {
            var post = res.value();
            incorporateParentUrl(post);
            obs.onNext(DataModelAdaptor.Post_to_GrpcPost(post));
        });
    }

    private void incorporateParentUrl(Post post) {
        var parentId = post.getParentUrl();
        if (parentId != null) {
            var parentUrl = incorporateUrlToId(baseUri, parentId);
            post.setParentUrl(parentUrl);
        }
    }

    @Override
    public void getPostAnswers(GetPostAnswersArgs req, StreamObserver<GetPostsResult> obs) {
        var res = contents.getPostAnswers(req.getPostId(), req.getTimeout());
        unwrapResult(obs, res, () -> {
            var pids = res.value();
            var grpcRes = GetPostsResult.newBuilder().addAllPostId(pids).build();
            obs.onNext(grpcRes);
        });
    }

    @Override
    public void updatePost(UpdatePostArgs req, StreamObserver<GrpcPost> obs) {
        var updatedFields = DataModelAdaptor.GrpcPost_to_Post(req.getPost());
        var res = contents.updatePost(req.getPostId(), req.getPassword(), updatedFields);
        unwrapResult(obs, res, () -> {
            var p = res.value();
            obs.onNext(DataModelAdaptor.Post_to_GrpcPost(p));
        });
    }

    @Override
    public void deletePost(DeletePostArgs req, StreamObserver<EmptyMessage> obs) {
        var res = contents.deletePost(req.getPostId(), req.getPassword());
        unwrapResult(obs, res, () -> obs.onNext(EmptyMessage.getDefaultInstance()));
    }

    @Override
    public void upVotePost(ChangeVoteArgs req, StreamObserver<EmptyMessage> obs) {
        var res = contents.upVotePost(req.getPostId(), req.getUserId(), req.getPassword());
        unwrapResult(obs, res, () -> obs.onNext(EmptyMessage.getDefaultInstance()));
    }

    @Override
    public void removeUpVotePost(ChangeVoteArgs req, StreamObserver<EmptyMessage> obs) {
        var res = contents.removeUpVotePost(req.getPostId(), req.getUserId(), req.getPassword());
        unwrapResult(obs, res, () -> obs.onNext(EmptyMessage.getDefaultInstance()));
    }

    @Override
    public void downVotePost(ChangeVoteArgs req, StreamObserver<EmptyMessage> obs) {
        var res = contents.downVotePost(req.getPostId(), req.getUserId(), req.getPassword());
        unwrapResult(obs, res, () -> obs.onNext(EmptyMessage.getDefaultInstance()));
    }

    @Override
    public void removeDownVotePost(ChangeVoteArgs req, StreamObserver<EmptyMessage> obs) {
        var res = contents.removeDownVotePost(req.getPostId(), req.getUserId(), req.getPassword());
        unwrapResult(obs, res, () -> obs.onNext(EmptyMessage.getDefaultInstance()));
    }

    @Override
    public void getUpVotes(GetPostArgs req, StreamObserver<VoteCountResult> obs) {
        var res = contents.getupVotes(req.getPostId());
        unwrapResult(obs, res, () -> {
            var upvotes = res.value();
            obs.onNext(VoteCountResult.newBuilder().setCount(upvotes).build());
        });
    }

    @Override
    public void getDownVotes(GetPostArgs req, StreamObserver<VoteCountResult> obs) {
        var res = contents.getDownVotes(req.getPostId());
        unwrapResult(obs, res, () -> {
            var downvotes = res.value();
            obs.onNext(VoteCountResult.newBuilder().setCount(downvotes).build());
        });
    }

    @Override
    public void forgetUser(ForgetUserArgs req, StreamObserver<ForgetUserResult> obs) {
        var res = contents.forgetUser(req.getUserId());
        unwrapResult(obs, res, () -> obs.onNext(ForgetUserResult.getDefaultInstance()));
    }
}
