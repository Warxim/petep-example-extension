package pdu;

import com.warxim.petep.core.connection.Connection;
import com.warxim.petep.core.pdu.PDU;
import com.warxim.petep.core.pdu.PduDestination;
import com.warxim.petep.gui.component.PduMetadataPane;
import com.warxim.petep.proxy.worker.Proxy;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.Set;

/**
 * Example metadata pane for configuring metadata of example PDU:
 * <ul>
 *     <li>string param,</li>
 *     <li>integer param.</li>
 * </ul>
 */
public class ExamplePduMetadataPane extends PduMetadataPane {
    @FXML
    private TextField stringInput;
    @FXML
    private TextField integerInput;

    public ExamplePduMetadataPane() throws IOException {
        super("/fxml/ExamplePduMetadataPane.fxml");
    }

    @Override
    public Optional<PDU> createPdu(
            Proxy proxy,
            Connection connection,
            PduDestination destination,
            byte[] buffer,
            int size,
            Charset charset,
            Set<String> tags) {
        return Optional.of(new ExamplePdu(
                proxy,
                connection,
                destination,
                buffer,
                size,
                charset,
                tags,
                stringInput.getText(),
                Integer.parseInt(integerInput.getText())
        ));
    }

    @Override
    public void setPdu(PDU pdu) {
        var examplePdu = (ExamplePdu) pdu;
        stringInput.setText(examplePdu.getStringParam());
        integerInput.setText(String.valueOf(examplePdu.getIntegerParam()));
    }

    @Override
    public void clear() {
        stringInput.clear();
        integerInput.clear();
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
