package client;


import api.java.Image;
import api.java.Result;
import client.grpc.GrpcImageClient;
import client.rest.RestImageClient;

public class ImageClient implements Image {

    public static final String SERVICE = "Image";

    private final ClientLauncher launcher = new ClientLauncher();

    private volatile Image inner;

    private static ImageClient singleton;

    public static ImageClient getInstance() {
        if (singleton == null) {
            synchronized (ImageClient.class) {
                if (singleton == null)
                    singleton = new ImageClient();
            }
        }
        return singleton;
    }

    private ImageClient() {}

    @Override
    public Result<String> createImage(String uid, byte[] content, String pwd) {
        if (inner == null)
            materializeChannel();
        return inner.createImage(uid, content, pwd);
    }

    @Override
    public Result<byte[]> getImage(String uid, String iid) {
        if (inner == null)
            materializeChannel();
        return inner.getImage(uid, iid);
    }

    @Override
    public Result<Void> deleteImage(String uid, String iid, String pwd) {
        if (inner == null)
            materializeChannel();
        return inner.deleteImage(uid, iid, pwd);
    }

    @Override
    public Result<Void> deleteImageUponUserOrPostRemoval(String uid, String iid) {
        if (inner == null)
            materializeChannel();
        return inner.deleteImageUponUserOrPostRemoval(uid, iid);
    }

    @Override
    public Result<Void> teardown() {
        if (inner == null)
            materializeChannel();
        return inner.teardown();
    }

    private void materializeChannel() {
        synchronized (this) {
            if (inner == null)
                inner = computeInnerChannel();
        }
    }

    private Image computeInnerChannel() {
        return launcher.launch(SERVICE, RestImageClient::new, uri -> {
                try {
                    return new GrpcImageClient(uri);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to initialize GrpcContentClient", e);
                }
            }
          );
        }

}
