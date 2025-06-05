package client.rest;

import api.java.Result;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import server.SharedSecret;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import java.util.function.Supplier;
import java.util.logging.Logger;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static api.java.Result.ErrorCode.*;


public class RestClientUtils {

    private static final Logger log = Logger.getLogger(RestClientUtils.class.getName());

    protected static final int MAX_RETRIES = 10;
    protected static final int RETRY_SLEEP = 5000;

    static final int READ_TIMEOUT = 5000;
    static final int CONNECT_TIMEOUT = 5000;

    static Client computeClient() {
        var config = new ClientConfig();
        config.property( ClientProperties.READ_TIMEOUT, READ_TIMEOUT);
        config.property( ClientProperties.CONNECT_TIMEOUT, CONNECT_TIMEOUT);
        return ClientBuilder.newClient(config);
    }

    static <V> Result<V> runRepeatableRequest(Supplier<Result<V>> mappingFunction) {
        for (int i = 0; i < MAX_RETRIES; i++) {
            try {
                return mappingFunction.get();
            } catch(ProcessingException e) {
                log.info(e.getMessage());
                try {
                    Thread.sleep(RETRY_SLEEP);
                } catch (InterruptedException ignored) {}
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        return Result.error(Result.ErrorCode.TIMEOUT);
    }

    static <T> Result<T> genericGetRequest(WebTarget target, Class<T> outputClass) {
        return runRepeatableRequest(() -> {
            var invocation = target.request().accept(APPLICATION_JSON);
            String secret = SharedSecret.getSharedSecret();
                if (secret != null)
                invocation.header("X-Server-Secret", secret);
                log.info("Using secret: " + secret + " " + outputClass.getName()); 
            try (var response = invocation.get()) {
                return processResponseWithBody(outputClass, response);
            }
        });
    }

    static <T> Result<T> genericDeleteRequest(WebTarget target, Class<T> outputClass) {
        return runRepeatableRequest(() -> {
            var invocation = target.request().accept(APPLICATION_JSON);
            String secret = SharedSecret.getSharedSecret();
                if (secret != null)
                invocation.header("X-Server-Secret", secret);
                log.info("Using secret: " + secret + " " + outputClass.getName());
            try (var response = invocation.delete()) {
                return processResponseWithBody(outputClass, response);
            }
        });
    }

    static Result<Void> deleteNoContentRequest(WebTarget target) {
        return runRepeatableRequest(() -> {
            var invocation = target.request();
            try (var response = invocation.delete()) {
                int status = response.getStatus();
                if (status != Response.Status.NO_CONTENT.getStatusCode())
                    return Result.error(getErrorCodeFrom(status));
                return Result.ok();
            }
        });
    }

    static <T,G> Result<G> genericPostRequest(WebTarget target, T entity, Class<G> outputClass) {
        return runRepeatableRequest(() -> {
            var invocation = target.request().accept(APPLICATION_JSON);
            String secret = SharedSecret.getSharedSecret();
                if (secret != null)
                invocation.header("X-Server-Secret", secret);
                log.info("Using secret: " + secret + " " + outputClass.getName());
            var body = Entity.json(entity);
            try (var response = invocation.post(body)) {
                return processResponseWithBody(outputClass, response);
            }
        });
    }

    static <T,G> Result<G> genericPutRequest(WebTarget target, T entity, Class<G> outputClass) {
        return runRepeatableRequest(() -> {
            var invocation = target.request().accept(APPLICATION_JSON);
            String secret = SharedSecret.getSharedSecret();
                if (secret != null)
                invocation.header("X-Server-Secret", secret);
                log.info("Using secret: " + secret + " " + outputClass.getName());
            var body = Entity.json(entity);
            try (var response = invocation.put(body)) {
                return processResponseWithBody(outputClass, response);
            }
        });
    }

    static <T> Result<T> processResponseWithBody(Class<T> outputClass, Response response) {
        int status = response.getStatus();
        if (status != Response.Status.OK.getStatusCode() || !response.hasEntity())
            return Result.error(getErrorCodeFrom(status));
        return Result.ok(response.readEntity(outputClass));
    }

    static Result.ErrorCode getErrorCodeFrom(int status) {
        return switch (status) {
            case 200, 209 -> OK;
            case 409 -> CONFLICT;
            case 403 -> FORBIDDEN;
            case 404 -> NOT_FOUND;
            case 400 -> BAD_REQUEST;
            case 501 -> NOT_IMPLEMENTED;
            default -> INTERNAL_ERROR;
        };
    }
}
