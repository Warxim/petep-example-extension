package pdu;

import com.warxim.petep.core.connection.Connection;
import com.warxim.petep.core.pdu.DefaultPdu;
import com.warxim.petep.core.pdu.PDU;
import com.warxim.petep.core.pdu.PduDestination;
import com.warxim.petep.proxy.worker.Proxy;
import lombok.Getter;
import lombok.Setter;

import java.nio.charset.Charset;
import java.util.Set;

/**
 * Example PDU based on default pdu.
 * <p>Contains the following metadata:</p>
 * <ul>
 *     <li>string param,</li>
 *     <li>integer param.</li>
 * </ul>
 */
@Getter
@Setter
public class ExamplePdu extends DefaultPdu {
    private String stringParam;
    private int integerParam;

    public ExamplePdu(
            Proxy proxy,
            Connection connection,
            PduDestination destination,
            byte[] buffer,
            int size,
            String stringParam,
            int integerParam) {
        super(proxy, connection, destination, buffer, size);

        this.stringParam = stringParam;
        this.integerParam = integerParam;
    }

    public ExamplePdu(
            Proxy proxy,
            Connection connection,
            PduDestination destination,
            byte[] buffer,
            int size,
            Charset charset,
            Set<String> tags,
            String stringParam,
            int integerParam) {
        super(proxy, connection, destination, buffer, size, charset, tags);

        this.stringParam = stringParam;
        this.integerParam = integerParam;
    }

    @Override
    public PDU copy() {
        var pdu = new ExamplePdu(proxy, connection, destination, buffer, size, stringParam, integerParam);
        pdu.setCharset(charset);
        pdu.addTags(tags);
        pdu.setLastInterceptor(pdu.getLastInterceptor());
        return pdu;
    }
}
