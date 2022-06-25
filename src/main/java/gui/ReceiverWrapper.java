package gui;

import com.warxim.petep.extension.receiver.Receiver;
import lombok.Value;

/**
 * Receiver wrapper for displaying receiver in dialog.
 */
@Value
class ReceiverWrapper {
    Receiver receiver;

    @Override
    public String toString() {
    return receiver.getName() + " (" + receiver.getCode() + ")";
}
}