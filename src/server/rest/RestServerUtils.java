package server.rest;

import api.java.Result;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import server.ServerUtils;

import java.net.URI;
import java.net.UnknownHostException;

import static jakarta.ws.rs.core.Response.Status.*;

public class RestServerUtils {

    public static final String COMM_PROTOCOL = "http";

    static String computeServerUri(int port) throws UnknownHostException {
        return ServerUtils.computeServerUri(COMM_PROTOCOL, port, ServerUtils.CommInterface.REST);
    }

    static <T> void launchResource(String uri, Class<T> resourceClass) {
        ResourceConfig config = new ResourceConfig();
        config.register(resourceClass);
        JdkHttpServerFactory.createHttpServer( URI.create(uri), config);
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

}
