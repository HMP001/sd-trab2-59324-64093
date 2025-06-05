package server;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.util.logging.Logger;

@Provider
public class SecretAuth implements ContainerRequestFilter {

    private static final Logger log = Logger.getLogger(SecretAuth.class.getName());

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String secret = requestContext.getHeaderString("X-Server-Secret");

        // Only validate if the header is present
        if (secret != null) {
            log.info("Received secret: " + secret);
            if (!SharedSecret.isValid(secret)) {
                log.warning("Invalid secret provided: " + secret);
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
            }
        } else {
            log.info("No secret provided, assuming external client - allowing request");
        }
    }
}
