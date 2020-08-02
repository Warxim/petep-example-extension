package pdu;

import java.io.IOException;

import com.warxim.petep.core.connection.Connection;
import com.warxim.petep.core.pdu.PDU;
import com.warxim.petep.core.pdu.PduDestination;
import com.warxim.petep.gui.component.PduMetadataPane;
import com.warxim.petep.proxy.worker.Proxy;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ExamplePduMetadataPane extends PduMetadataPane {
  @FXML private TextField stringInput;
  @FXML private TextField integerInput;

  public ExamplePduMetadataPane() throws IOException {
    super("/fxml/ExamplePduMetadataPane.fxml");
  }

  @Override
  public PDU getPdu(
      Proxy proxy, Connection connection, PduDestination destination, byte[] buffer, int size) {
    return new ExamplePdu(
        proxy,
        connection,
        destination,
        buffer,
        size,
        stringInput.getText(),
        Integer.parseInt(integerInput.getText()));
  }

  @Override
  public void setPdu(PDU pdu) {
    stringInput.setText(((ExamplePdu) pdu).getStringParam());
    integerInput.setText(String.valueOf(((ExamplePdu) pdu).getIntegerParam()));
  }

  @Override
  public void clear() {
    stringInput.clear();
    integerInput.clear();
  }
}
