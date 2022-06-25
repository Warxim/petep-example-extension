package proxy.config;

import com.warxim.petep.gui.component.ConfigPane;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Configurator for example proxy.
 */
public class ExampleProxyConfigurator extends ConfigPane<ExampleProxyConfig> {
    @FXML
    private TextField connectionCountInput;

    public ExampleProxyConfigurator() throws IOException {
        super("/fxml/ExampleProxyConfigurator.fxml");
    }

    @Override
    public ExampleProxyConfig getConfig() {
        return new ExampleProxyConfig(Integer.valueOf(connectionCountInput.getText()));
    }

    @Override
    public void setConfig(ExampleProxyConfig config) {
        connectionCountInput.setText(String.valueOf(config.getConnectionCount()));
    }

    @Override
    public boolean isValid() {
        return connectionCountInput.getText().matches("-?\\d+");
    }
}
