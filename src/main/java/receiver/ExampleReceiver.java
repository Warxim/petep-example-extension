package receiver;

import com.warxim.petep.core.pdu.SerializedPdu;
import com.warxim.petep.extension.receiver.Receiver;
import lombok.Data;

import java.util.function.Consumer;

/**
 * Example receiver for receiving serialized PDUs.
 * <p>Passes the PDUs to consumer, which will be in our example controller.</p>
 */
@Data
public class ExampleReceiver implements Receiver {
    private Consumer<SerializedPdu> consumer;

    @Override
    public String getName() {
        return "Example Receiver";
    }

    @Override
    public String getCode() {
        return "example-receiver";
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SerializedPdu.class.equals(clazz);
    }

    @Override
    public void receive(Object object) {
        if (consumer == null) {
            return;
        }

        // Pass the PDU to the consumer
        var serializedPdu = (SerializedPdu) object;
        consumer.accept(serializedPdu);
    }
}
