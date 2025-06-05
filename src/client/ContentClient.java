package client;

import api.Post;
import api.java.Content;
import api.java.Result;
import client.grpc.GrpcContentClient;
import client.rest.RestContentClient;

import java.util.List;

public class ContentClient implements Content {

    public static final String SERVICE = "Content";

    private volatile Content inner;

    private final ClientLauncher launcher = new ClientLauncher();

    private static ContentClient singleton;

    public static ContentClient getInstance() {
        if (singleton == null) {
            synchronized (ContentClient.class) {
                if (singleton == null)
                    singleton = new ContentClient();
            }
        }
        return singleton;
    }

    private ContentClient() {}

    @Override
    public Result<String> createPost(Post p, String pwd) {
        if (inner == null)
            materializeChannel();
        return inner.createPost(p, pwd);
    }

    @Override
    public Result<List<String>> getPosts(long timestamp, String sortOrder) {
        if (inner == null)
            materializeChannel();
        return inner.getPosts(timestamp, sortOrder);
    }

    @Override
    public Result<Post> getPost(String pid) {
        if (inner == null)
            materializeChannel();
        return inner.getPost(pid);
    }

    @Override
    public Result<List<String>> getPostAnswers(String pid, long maxTimeout) {
        if (inner == null)
            materializeChannel();
        return inner.getPostAnswers(pid, maxTimeout);
    }

    @Override
    public Result<Post> updatePost(String pid, String pwd, Post updatedFields) {
        if (inner == null)
            materializeChannel();
        return inner.updatePost(pid, pwd, updatedFields);
    }

    @Override
    public Result<Void> deletePost(String pid, String pwd) {
        if (inner == null)
            materializeChannel();
        return inner.deletePost(pid, pwd);
    }

    @Override
    public Result<Void> upVotePost(String pid, String uid, String pwd) {
        if (inner == null)
            materializeChannel();
        return inner.upVotePost(pid, uid, pwd);
    }

    @Override
    public Result<Void> removeUpVotePost(String pid, String uid, String pwd) {
        if (inner == null)
            materializeChannel();
        return inner.removeUpVotePost(pid, uid, pwd);
    }

    @Override
    public Result<Void> downVotePost(String pid, String uid, String pwd) {
        if (inner == null)
            materializeChannel();
        return inner.downVotePost(pid, uid, pwd);
    }

    @Override
    public Result<Void> removeDownVotePost(String pid, String uid, String pwd) {
        if (inner == null)
            materializeChannel();
        return inner.removeDownVotePost(pid, uid, pwd);
    }

    @Override
    public Result<Integer> getupVotes(String pid) {
        if (inner == null)
            materializeChannel();
        return inner.getupVotes(pid);
    }

    @Override
    public Result<Integer> getDownVotes(String pid) {
        if (inner == null)
            materializeChannel();
        return inner.getDownVotes(pid);
    }

    @Override
    public Result<Void> forgetUser(String uid) {
        if (inner == null)
            materializeChannel();
        return inner.forgetUser(uid);
    }
    
    @Override
    public Result<Boolean> checkImage(String iid) {
        if (inner == null){
            server.SharedSecret.setSharedSecret("secret");
            materializeChannel();
        }
        return inner.checkImage(iid);
    }

    private void materializeChannel() {
        synchronized (this) {
            if (inner == null)
                inner = computeInnerChannel();
        }
    }

    private Content computeInnerChannel() {
        return launcher.launch(SERVICE, RestContentClient::new, uri -> {
                try {
                    return new GrpcContentClient(uri);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to initialize GrpcContentClient", e);
                }
            }
          );
        }

}
