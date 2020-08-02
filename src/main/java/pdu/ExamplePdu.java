package pdu;

import java.util.Set;

import com.warxim.petep.core.connection.Connection;
import com.warxim.petep.core.pdu.DefaultPdu;
import com.warxim.petep.core.pdu.PDU;
import com.warxim.petep.core.pdu.PduDestination;
import com.warxim.petep.proxy.worker.Proxy;

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
      Set<String> tags,
      String stringParam,
      int integerParam) {
    super(proxy, connection, destination, buffer, size, tags);

    this.stringParam = stringParam;
    this.integerParam = integerParam;
  }

  public void setStringParam(String param) {
    stringParam = param;
  }

  public void setIntegerParam(int param) {
    integerParam = param;
  }

  public String getStringParam() {
    return stringParam;
  }

  public int getIntegerParam() {
    return integerParam;
  }

  @Override
  public PDU copy() {
    ExamplePdu pdu =
        new ExamplePdu(proxy, connection, destination, buffer, size, stringParam, integerParam);
    pdu.addTags(tags);
    return pdu;
  }
}
