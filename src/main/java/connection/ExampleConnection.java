package connection;

import com.warxim.petep.core.connection.ConnectionBase;
import com.warxim.petep.core.pdu.PduDestination;
import com.warxim.petep.proxy.worker.Proxy;
import com.warxim.petep.util.PduUtils;
import pdu.ExamplePdu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * Example connection for example proxy.
 * <p>Simulates messages from client and server and sends them through PETEP.</p>
 */
public class ExampleConnection extends ConnectionBase {
    private final String name;
    private final ExecutorService executor;
    private boolean running;

    public ExampleConnection(String code, Proxy proxy, String name) {
        super(code, proxy);
        this.name = name;
        executor = Executors.newFixedThreadPool(4);
    }

    @Override
    public boolean start() {
        logInfo("started!");

        running = true;

        // Pretend reading data from client.
        executor.execute(this::fakeReadFromClient);
        // Pretend reading data from server.
        executor.execute(this::fakeReadFromServer);

        // Pretend writing data to client.
        executor.execute(this::fakeWriteToClient);
        // Pretend writing data to server.
        executor.execute(this::fakeWriteToServer);

        return true;
    }

    @Override
    public void stop() {
        running = false;

        // Shutdown threads
        executor.shutdownNow();

        // Remove itself from connection manager
        proxy.getConnectionManager().remove(this);

        logInfo("stopped!");
    }

    @Override
    public String toString() {
        return "Example connection '" + name + "' (Code: " + code + ")";
    }

    /**
     * Simulates reading data from client.
     */
    private void fakeReadFromClient() {
        try {
            while (running) {
                var pdu = new ExamplePdu(
                        proxy,
                        this,
                        PduDestination.SERVER,
                        new byte[]{0x01, 0x02, 0x03, 0x04, 0x00, 0x00, 0x00, 0x00},
                        4,
                        "Request from client",
                        1234);

                process(pdu);

                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            // Interrupted.
        }
    }

    /**
     * Simulates reading data from server.
     */
    private void fakeReadFromServer() {
        try {
            while (running) {
                var pdu = new ExamplePdu(
                        proxy,
                        this,
                        PduDestination.CLIENT,
                        new byte[]{0x04, 0x03, 0x02, 0x01, 0x00, 0x00, 0x00, 0x00},
                        4,
                        "Request from server",
                        4321);

                process(pdu);

                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            // Interrupted.
        }
    }

    /**
     * Simulates writing data to server. Instead of doing that, just writes the data to log.
     */
    private void fakeWriteToServer() {
        ExamplePdu pdu;

        try {
            while ((pdu = (ExamplePdu) queueC2S.take()) != null) {
                logInfo(
                        "sent data: '"
                                + PduUtils.bufferToHexString(pdu)
                                + "' ['"
                                + pdu.getStringParam()
                                + "', "
                                + pdu.getIntegerParam()
                                + "] to server");
            }
        } catch (InterruptedException e) {
            // Interrupted.
        }
    }

    /**
     * Simulates writing data to client. Instead of doing that, just writes the data to log.
     */
    private void fakeWriteToClient() {
        ExamplePdu pdu;

        try {
            while ((pdu = (ExamplePdu) queueS2C.take()) != null) {
                logInfo("sent data: '"
                                + PduUtils.bufferToHexString(pdu)
                                + "' ['"
                                + pdu.getStringParam()
                                + "', "
                                + pdu.getIntegerParam()
                                + "] to client");
            }
        } catch (InterruptedException e) {
            // Interrupted.
        }
    }

    private void logInfo(String message) {
        Logger.getGlobal().info(
                () -> "Example connection '"
                        + name
                        + "' (ID: "
                        + code
                        + ") "
                        + message);
    }
}
