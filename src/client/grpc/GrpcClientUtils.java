package client.grpc;

import api.java.Result;
import io.grpc.StatusRuntimeException;
import io.netty.handler.ssl.SslContext;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.function.Supplier;

import javax.net.ssl.TrustManagerFactory;
import io.netty.handler.ssl.SslContextBuilder;
import io.grpc.netty.GrpcSslContexts;

import static api.java.Result.ErrorCode.*;

public class GrpcClientUtils {

    static final long READ_TIMEOUT = 50000;

    static <T> Result<T> wrapRequest(Supplier<Result<T>> f) {
        try {
            return f.get();
        } catch (StatusRuntimeException sre) {
            return Result.error(getErrorCodeFrom(sre));
        }
    }


    static SslContext addSslContext () throws Exception {
        String trustStoreFilename = System.getProperty("javax.net.ssl.trustStore");
		String trustStorePassword = System.getProperty("javax.net.ssl.trustStorePassword");
		
		
		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		try(FileInputStream input = new FileInputStream(trustStoreFilename)) {
			trustStore.load(input, trustStorePassword.toCharArray());
		}
		
		TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
				TrustManagerFactory.getDefaultAlgorithm());
		trustManagerFactory.init(trustStore);
		
		SslContext context = GrpcSslContexts.configure(SslContextBuilder.forClient().trustManager(trustManagerFactory)).build();

        return context;

    }

    static Result.ErrorCode getErrorCodeFrom(StatusRuntimeException status) {
        var code = status.getStatus().getCode();
        return switch (code) {
            case ALREADY_EXISTS -> CONFLICT;
            case PERMISSION_DENIED -> FORBIDDEN;
            case NOT_FOUND -> NOT_FOUND;
            case INVALID_ARGUMENT -> BAD_REQUEST;
            case DEADLINE_EXCEEDED -> TIMEOUT;
            default -> INTERNAL_ERROR;
        };
    }

}
