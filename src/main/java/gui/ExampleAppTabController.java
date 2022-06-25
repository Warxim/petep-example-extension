package gui;

import com.warxim.petep.core.pdu.SerializedPdu;
import com.warxim.petep.extension.internal.history.HistoryApi;
import com.warxim.petep.extension.internal.history.gui.view.HistoryView;
import com.warxim.petep.extension.internal.history.model.HistoryFilter;
import com.warxim.petep.extension.receiver.Receiver;
import com.warxim.petep.gui.control.SerializedPduView;
import com.warxim.petep.gui.dialog.Dialogs;
import com.warxim.petep.helper.ExtensionHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Controller for example application tab.
 * <p>Loads history view into the tab content.</p>
 */
@RequiredArgsConstructor
public class ExampleAppTabController implements Initializable {
    private final ExtensionHelper extensionHelper;
    private final HistoryApi historyApi;
    private HistoryView historyView;

    @FXML
    private Pane historyPane;
    @FXML
    private SerializedPduView serializedPduView;
    @FXML
    private Button sendToButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initHistoryView();
        sendToButton.setDisable(true);
    }

    /**
     * Shows serialized PDU in view.
     * @param serializedPdu PDU to be shown
     */
    public void showSerializedPdu(SerializedPdu serializedPdu) {
        sendToButton.setDisable(false);
        serializedPduView.setSerializedPdu(serializedPdu);
    }

    /**
     * Shows dialog for choosing target receiver and then sends serialized PDU to it.
     */
    @FXML
    private void onSendToButtonClick(ActionEvent event) {
        var maybePdu = serializedPduView.getSerializedPdu();
        if (maybePdu.isEmpty()) {
            return;
        }

        // Obtain receivers that support serialized PDU
        var receivers = extensionHelper.getReceivers(SerializedPdu.class).stream()
                .map(ReceiverWrapper::new)
                .collect(Collectors.toList());

        // Let the user pick receiver
        var maybeReceiverWrapper = Dialogs.createChoiceDialog("Send to receiver", "Choose receiver", receivers);
        if (maybeReceiverWrapper.isEmpty()) {
            return;
        }

        // Send the PDU to the receiver
        var receiver = maybeReceiverWrapper.get().getReceiver();
        extensionHelper.sendToReceiver(
                receiver.getCode(),
                maybePdu.get().copy()
        );
    }

    /**
     * Creates and loads history view into the tab.
     */
    private void initHistoryView() {
        if (historyApi == null) {
            return;
        }
        // Create filter for history
        var filter = HistoryFilter.builder().build();

        // Create view to history
        historyView = historyApi.createView(filter);

        // Get JavaFX node of history view
        var historyNode = historyView.getNode();

        AnchorPane.setBottomAnchor(historyNode, 0.0);
        AnchorPane.setTopAnchor(historyNode, 0.0);
        AnchorPane.setLeftAnchor(historyNode, 0.0);
        AnchorPane.setRightAnchor(historyNode, 0.0);

        // Add history node to the history pane
        historyPane.getChildren().add(historyNode);
    }
}
