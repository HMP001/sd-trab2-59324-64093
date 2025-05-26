package server.grpc;

import api.java.Result;
import io.grpc.*;
import io.grpc.stub.StreamObserver;
import server.ServerUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.KeyStore;
import io.grpc.netty.GrpcSslContexts;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;

import javax.net.ssl.KeyManagerFactory;
import io.grpc.netty.NettyServerBuilder;

import static io.grpc.Status.*;

public class GrpcServerUtils {

    private static final String COMM_PROTOCOL = "grpc";

    static String computeServerUri(int port) throws UnknownHostException {
        return ServerUtils.computeServerUri(COMM_PROTOCOL, port, ServerUtils.CommInterface.GRPC);
    }

    static void launchServer(SslContext context, int port, BindableService stub) throws InterruptedException, IOException {
        Server server = NettyServerBuilder.forPort(port).addService(stub).sslContext(context).build();
        server.start().awaitTermination();
    }

    static <T, V> void unwrapResult(StreamObserver<T> obs, Result<V> res, Runnable r) {
        if (!res.isOK()) {
            obs.onError(errorCodeToStatus(res.error()));
        } else {
            r.run();
            obs.onCompleted();
        }
    }

    static SslContext addSslContext () throws Exception {
        String keyStoreFilename = System.getProperty("javax.net.ssl.keyStore");
		String keyStorePassword = System.getProperty("javax.net.ssl.keyStorePassword");

        if (keyStoreFilename == null || keyStorePassword == null) {
        throw new IllegalStateException("Keystore filename or password not set in system properties.");
        }
		
		KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());

		try(FileInputStream input = new FileInputStream(keyStoreFilename)) {
			keystore.load(input, keyStorePassword.toCharArray());
		}
		
		KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		
        keyManagerFactory.init(keystore, keyStorePassword.toCharArray());
		
		SslContext context = GrpcSslContexts.configure(SslContextBuilder.forServer(keyManagerFactory)).build();

        return context;
    }

    static StatusException errorCodeToStatus(Result.ErrorCode err) {
        Status s = switch (err) {
            case BAD_REQUEST -> INVALID_ARGUMENT;
            case NOT_FOUND -> NOT_FOUND;
            case FORBIDDEN -> PERMISSION_DENIED;
            case CONFLICT -> ALREADY_EXISTS;
            default -> INTERNAL;
        };
        return s.asException();
    }


}
