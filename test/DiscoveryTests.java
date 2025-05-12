import impl.Hibernate;
import network.DiscoveryListener;
import network.ServiceAnnouncer;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DiscoveryTests {

    private static final long TEST_DISCOVERY_PERIOD = 20; //ms

    @After
    public void teardown() {
        Hibernate.teardown();
        DiscoveryListener.teardown();
    }

    @Test
    public void shouldReadOwnAnnouncement() throws Exception {
        var service = "test";
        var port = 8080;
        var uri = String.format("http://localhost:%d", port);
        new ServiceAnnouncer(service, uri, TEST_DISCOVERY_PERIOD);
        var listener = DiscoveryListener.getInstance();
        Thread.sleep(2 * TEST_DISCOVERY_PERIOD);
        var uris = listener.knownUrisOf(service, 1);
        assertEquals(1, uris.length);
        assertEquals(8080, uris[0].getPort());
    }

    @Test
    public void shouldBlockForAnnouncement() {
        var service = "test";
        var port = 8080;
        var uri = String.format("http://localhost:%d", port);
        new Thread(() -> {
            try {
                Thread.sleep(10 * TEST_DISCOVERY_PERIOD);
                new ServiceAnnouncer(service, uri, TEST_DISCOVERY_PERIOD);
            } catch (InterruptedException | IOException e) {
                fail();
            }
        }).start();
        var listener = DiscoveryListener.getInstance();
        var uris = listener.knownUrisOf(service, 1);
        assertEquals(1, uris.length);
        assertEquals(8080, uris[0].getPort());
    }

    @Test
    public void shouldDeliverMoreThanOne() throws Exception {
        var service = "test";
        var port = 8080;
        var uri0 = String.format("http://localhost:%d", port);
        var uri1 = String.format("http://localhost:%d", port + 1);
        new ServiceAnnouncer(service, uri0);
        new ServiceAnnouncer(service, uri1);
        var listener = DiscoveryListener.getInstance();
        var uris = listener.knownUrisOf(service, 2);
        assertEquals(2, uris.length);
    }

    @Test
    public void shouldDeliverMoreThanMinimum() throws Exception {
        var service = "test";
        var port = 8080;
        var uri0 = String.format("http://localhost:%d", port);
        var uri1 = String.format("http://localhost:%d", port + 1);
        new ServiceAnnouncer(service, uri0);
        new ServiceAnnouncer(service, uri1);
        var listener = DiscoveryListener.getInstance();
        Thread.sleep(10 * TEST_DISCOVERY_PERIOD);
        var uris = listener.knownUrisOf(service, 1);
        assertEquals(2, uris.length);
    }

    @Test
    public void shouldNotMixServices() throws Exception {
        var service0 = "test0";
        var service1 = "test1";
        var port = 8080;
        var uri0 = String.format("http://localhost:%d", port);
        var uri1 = String.format("http://localhost:%d", port + 1);
        new ServiceAnnouncer(service0, uri0);
        new ServiceAnnouncer(service1, uri1);
        var listener = DiscoveryListener.getInstance();
        Thread.sleep(2 * TEST_DISCOVERY_PERIOD);
        var uris = listener.knownUrisOf(service0, 1);
        assertEquals(1, uris.length);
    }

}
