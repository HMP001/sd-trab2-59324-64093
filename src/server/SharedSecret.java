package server;

import java.util.logging.Logger;

public class SharedSecret {

    private static String sharedSecret;

    private static final Logger log = Logger.getLogger(SharedSecret.class.getName());

    public static void setSharedSecret(String secret) {
        sharedSecret = secret;
    }

    public static boolean isValid(String receivedSecret) {
        log.info("Received secret: " + receivedSecret + ", expected: " + sharedSecret);
        return sharedSecret != null && sharedSecret.equals(receivedSecret);
    }

    public static String getSharedSecret() {
        return sharedSecret;
    }
}
