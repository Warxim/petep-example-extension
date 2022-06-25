package proxy;

import com.warxim.petep.core.connection.ConnectionManager;
import com.warxim.petep.core.pdu.PDU;
import com.warxim.petep.helper.PetepHelper;
import com.warxim.petep.proxy.module.ProxyModule;
import com.warxim.petep.proxy.worker.Proxy;
import connection.ExampleConnection;
import connection.ExampleConnectionManager;
import pdu.ExamplePdu;
import proxy.config.ExampleProxyConfig;

import java.util.logging.Logger;

/**
 * Example proxy, which starts configured number of example connections.
 */
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

        connectionManager = new ExampleConnectionManager(helper);

        return true;
    }

    @Override
    public boolean start() {
        Logger.getGlobal().info("Example proxy started!");

        for (var i = 0; i < config.getConnectionCount(); ++i) {
            var code = connectionManager.generateCode();
            var connection = new ExampleConnection(code, this, "ec_" + code);

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

    @Override
    public boolean supports(PDU pdu) {
        return pdu instanceof ExamplePdu;
    }
}
