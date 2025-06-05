package server.rest;

import api.java.Result;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import jakarta.ws.rs.core.Response.Status;
import server.ServerUtils;
import server.SyncPoint;

import java.net.URI;
import java.net.UnknownHostException;

import javax.net.ssl.SSLContext;

import static jakarta.ws.rs.core.Response.Status.*;

public class RestServerUtils {

    public static final String COMM_PROTOCOL = "https";
    private static final String HEADER_VERSION = "X-FCTREDDIT-VERSION";

    static String computeServerUri(int port) throws UnknownHostException {
        return ServerUtils.computeServerUri(COMM_PROTOCOL, port, ServerUtils.CommInterface.REST);
    }

    static <T> void launchResource(String uri, Class<T> resourceClass) {
        ResourceConfig config = new ResourceConfig();
        config.register(resourceClass);
        config.register(server.SecretAuth.class); 

        try{

        JdkHttpServerFactory.createHttpServer( URI.create(uri), config, SSLContext.getDefault());

        } catch (Exception e) {
            throw new RuntimeException("Failed to launch User REST server at " + uri, e);
        }
    }

    static <T> T wrapResult(Result<T> res) {
        if (res.isOK())
            return res.value();
        throw statusCodeToException(res.error());
    }

    static WebApplicationException statusCodeToException(Result.ErrorCode err) {
        Response.Status status = switch (err) {
            case CONFLICT -> CONFLICT;
            case NOT_FOUND -> NOT_FOUND;
            case BAD_REQUEST -> BAD_REQUEST;
            case FORBIDDEN -> FORBIDDEN;
            default -> INTERNAL_SERVER_ERROR;
        };
        return new WebApplicationException(status);
    }

    static <T> Response wrapResponse(Result<T> res) {
        if (!res.isOK()) {
            throw new WebApplicationException(Response.status(errorCodeToStatus(res.error()))
											.header(HEADER_VERSION, SyncPoint.getSyncPoint().getVersion())
											.build());
        }

        return Response.status(Response.Status.OK)
                   .header(HEADER_VERSION, SyncPoint.getSyncPoint().getVersion())
                   .type(MediaType.APPLICATION_JSON)
                   .entity(res.value())
                   .build();
        }

        static Status errorCodeToStatus(Result.ErrorCode err) {
            Status status = switch (err) {
                case CONFLICT -> CONFLICT;
                case NOT_FOUND -> NOT_FOUND;
                case BAD_REQUEST -> BAD_REQUEST;
                case FORBIDDEN -> FORBIDDEN;
                default -> INTERNAL_SERVER_ERROR;
            };
            return status;
    }

}
