package network;

import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.Optional;
import java.util.TreeSet;
import java.util.logging.Logger;

import static network.ServiceAnnouncer.*;

public class DiscoveryListener {

    private static final Logger log = Logger.getLogger(DiscoveryListener.class.getName());

    private static final int MAX_DATAGRAM_SIZE = 65536;

    private final MulticastSocket ms;
    private final HashMap<String, TreeSet<URI>> announcements = new HashMap<>();

    private static DiscoveryListener singleton;

    public static DiscoveryListener getInstance() {
        if (singleton == null) {
            synchronized (DiscoveryListener.class) {
                if (singleton == null)
                    singleton = new DiscoveryListener();
            }
        }
        return singleton;
    }

    private DiscoveryListener() {
        try {
            this.ms = new MulticastSocket(DISCOVERY_ADDR.getPort());
            ms.joinGroup(DISCOVERY_ADDR, NetworkInterface.getByInetAddress(InetAddress.getLocalHost()));
            new Thread(this::listenAnnouncements).start();
        } catch (IOException e) {
            log.severe("Unable to compute MulticastSocket");
            throw new RuntimeException(e);
        }
    }

    private void listenAnnouncements() {
        DatagramPacket pkt = new DatagramPacket(new byte[MAX_DATAGRAM_SIZE], MAX_DATAGRAM_SIZE);
        for (;;) {
            try {
                pkt.setLength(MAX_DATAGRAM_SIZE);
                ms.receive(pkt);
                String msg = new String(pkt.getData(), 0, pkt.getLength());
                String[] msgElems = msg.split(DELIMITER);
                if (msgElems.length == 2) {
                    log.fine(String.format("FROM %s (%s) : %s\n", pkt.getAddress().getHostName(),
                            pkt.getAddress().getHostAddress(), msg));
                    storeAnnouncement(msgElems[0], msgElems[1]);
                } else {
                    log.warning(String.format("Received unexpected packet in discovery multicast address: %s", msg));
                }
            } catch (IOException e) {
                log.warning("Unable to receive announcement packet");
                e.printStackTrace();
            }
        }
    }

    private void storeAnnouncement(String service, String uriStr) {
        var uri = convertToUri(uriStr);
        uri.ifPresentOrElse(u -> {
            synchronized (this) {
                TreeSet<URI> uris = this.announcements.computeIfAbsent(service, k -> new TreeSet<>());
                uris.add(u);
                this.notify();
            }
        }, () -> log.warning(String.format("Unable to parse announcement URI: %s", uriStr)));
    }

    private Optional<URI> convertToUri(String announcement) {
        try {
            return Optional.of(new URI(announcement));
        } catch (URISyntaxException e) {
            return Optional.empty();
        }
    }

    /**
     * Returns the known services.
     *
     * @param serviceName the name of the service being discovered
     * @param minReplies  - minimum number of requested URIs. Blocks until the
     *                    number is satisfied.
     * @return an array of URI with the service instances discovered.
     *
     */
    public URI[] knownUrisOf(String serviceName, int minReplies) {
        synchronized (this) {
            while(true) {
                var uris = this.announcements.get(serviceName);
                if(uris != null && uris.size() >= minReplies) {
                    return uris.toArray(new URI[0]);
                }
                try {
                    this.wait();
                } catch (InterruptedException ignored) {}
            }
        }
    }

    //Clears Discovery instance in tests
    public static void teardown() {
        singleton = null;
    }

}

