package connection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import com.warxim.petep.core.connection.Connection;
import com.warxim.petep.core.pdu.PduDestination;
import com.warxim.petep.proxy.worker.Proxy;
import com.warxim.petep.util.PduUtils;

import pdu.ExamplePdu;

public class ExampleConnection extends Connection {
  private String name;
  private ExecutorService executor;
  private boolean running;

  public ExampleConnection(int id, Proxy proxy, String name) {
    super(id, proxy);
    this.name = name;
    executor = Executors.newFixedThreadPool(4);
  }

  @Override
  public boolean start() {
    Logger.getGlobal().info("Example connection '" + name + "' (ID: " + id + ") started!");

    running = true;

    // Pretend reading data from client.
    executor.execute(
        () -> {
          while (running) {
            ExamplePdu pdu =
                new ExamplePdu(
                    proxy,
                    this,
                    PduDestination.SERVER,
                    new byte[] {01, 02, 03, 04, 00, 00, 00, 00},
                    4,
                    "Request from client",
                    1234);

            process(pdu);

            try {
              Thread.sleep(1000);
            } catch (InterruptedException e) {
              break;
            }
          }
        });

    // Pretend reading data from server.
    executor.execute(
        () -> {
          while (running) {
            ExamplePdu pdu =
                new ExamplePdu(
                    proxy,
                    this,
                    PduDestination.CLIENT,
                    new byte[] {04, 03, 02, 01, 00, 00, 00, 00},
                    4,
                    "Request from server",
                    4321);

            process(pdu);

            try {
              Thread.sleep(1000);
            } catch (InterruptedException e) {
              break;
            }
          }
        });

    // Pretend writing data to server.
    executor.execute(
        () -> {
          ExamplePdu pdu;

          try {
            while ((pdu = (ExamplePdu) queueC2S.take()) != null) {
              Logger.getGlobal()
                  .info(
                      "Example connection '"
                          + name
                          + "' (ID: "
                          + id
                          + ") read data: '"
                          + PduUtils.bufferToString(pdu)
                          + "' ['"
                          + pdu.getStringParam()
                          + "', "
                          + pdu.getIntegerParam()
                          + "] from  client");
            }
          } catch (InterruptedException e) {
            // Interrupted.
          }
        });

    // Pretend writing data to server.
    executor.execute(
        () -> {
          ExamplePdu pdu;

          try {
            while ((pdu = (ExamplePdu) queueS2C.take()) != null) {
              Logger.getGlobal()
                  .info(
                      "Example connection '"
                          + name
                          + "' (ID: "
                          + id
                          + ") read data: '"
                          + PduUtils.bufferToString(pdu)
                          + "' ['"
                          + pdu.getStringParam()
                          + "', "
                          + pdu.getIntegerParam()
                          + "] from  server");
            }
          } catch (InterruptedException e) {
            // Interrupted.
          }
        });

    return true;
  }

  @Override
  public void stop() {
    running = false;

    executor.shutdownNow();

    Logger.getGlobal().info("Example connection '" + name + "' (ID: " + id + ") stopped!");
  }

  @Override
  public String toString() {
    return "Example connection '" + name + "' (ID: " + id + ")";
  }
}
