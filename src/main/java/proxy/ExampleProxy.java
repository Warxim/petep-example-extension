package proxy;

import java.util.logging.Logger;

import com.warxim.petep.core.connection.ConnectionManager;
import com.warxim.petep.helper.PetepHelper;
import com.warxim.petep.proxy.module.ProxyModule;
import com.warxim.petep.proxy.worker.Proxy;

import connection.ExampleConnection;
import connection.ExampleConnectionManager;
import proxy.config.ExampleProxyConfig;

public class ExampleProxy extends Proxy {
  private ExampleProxyConfig config;

  private ExampleConnectionManager connectionManager;

  public ExampleProxy(ProxyModule module, PetepHelper helper, ExampleProxyConfig config) {
    super(module, helper);
    this.config = config;
  }

  @Override
  public ConnectionManager getConnectionManager() {
    return connectionManager;
  }

  @Override
  public boolean prepare() {
    Logger.getGlobal().info("Example proxy prepared!");

    connectionManager = new ExampleConnectionManager();

    return true;
  }

  @Override
  public boolean start() {
    Logger.getGlobal().info("Example proxy started!");

    for (int i = 0; i < config.getConnectionCount(); ++i) {
      int id = connectionManager.nextId();
      ExampleConnection connection = new ExampleConnection(id, this, "EC_" + id);

      if (connection.start()) {
        connectionManager.add(connection);
      }
    }

    return true;
  }

  @Override
  public void stop() {
    connectionManager.stop();

    Logger.getGlobal().info("Example proxy stopped!");
  }
}
