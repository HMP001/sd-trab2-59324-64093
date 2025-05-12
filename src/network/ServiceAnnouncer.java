package network;

import java.io.IOException;
import java.net.*;
import java.util.logging.Logger;

public class ServiceAnnouncer {

    private static final Logger log = Logger.getLogger(ServiceAnnouncer.class.getName());

    static {
        // addresses some multicast issues on some TCP/IP stacks
        System.setProperty("java.net.preferIPv4Stack", "true");
        // summarizes the logging format
        System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s");
    }

    // The pre-aggreed multicast endpoint assigned to perform discovery.
    // Allowed IP Multicast range: 224.0.0.1 - 239.255.255.255
    static final InetSocketAddress DISCOVERY_ADDR = new InetSocketAddress("226.226.226.226", 2262);
    static final String DELIMITER = "\t";

    private static final int DISCOVERY_ANNOUNCE_PERIOD = 1000;

    // Used separate the two fields that make up a service announcement.

    private final InetSocketAddress addr = DISCOVERY_ADDR;
    private final String service;
    private final String uri;
    private final long period;
    private final MulticastSocket ms;

    public ServiceAnnouncer(String service, String uri) throws IOException {
        this(service, uri, DISCOVERY_ANNOUNCE_PERIOD);
    }

    public ServiceAnnouncer(String service, String uri, long period) throws IOException {
        this.service = service;
        this.uri = uri;
        this.period = period;
        this.ms = new MulticastSocket(addr.getPort());
        new Thread(this::announceService).start();
    }

    private void announceService() {
        log.info(String.format("Starting Discovery announcements on: %s for: %s -> %s", addr, service, uri));
        byte[] announceBytes = String.format("%s%s%s", service, DELIMITER, uri).getBytes();
        DatagramPacket announcePkt = new DatagramPacket(announceBytes, announceBytes.length, addr);
        for (;;) {
            try {
                ms.send(announcePkt);
                Thread.sleep(period);
            } catch (IOException e) {
                log.warning("Unable to multicast announcement packet");
                e.printStackTrace();
            } catch (InterruptedException e) {
                log.warning("Interrupted while waiting between announcements");
                e.printStackTrace();
            }
        }

    }



}
