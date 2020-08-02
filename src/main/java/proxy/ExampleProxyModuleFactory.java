package proxy;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import com.warxim.petep.core.connection.Connection;
import com.warxim.petep.core.pdu.PDU;
import com.warxim.petep.core.pdu.PduDestination;
import com.warxim.petep.extension.Extension;
import com.warxim.petep.gui.component.ConfigPane;
import com.warxim.petep.gui.component.PduMetadataPane;
import com.warxim.petep.persistence.Configurator;
import com.warxim.petep.proxy.factory.ProxyModuleFactory;
import com.warxim.petep.proxy.module.ProxyModule;
import com.warxim.petep.proxy.serizalization.ProxyDeserializer;
import com.warxim.petep.proxy.serizalization.ProxySerializer;
import com.warxim.petep.proxy.worker.Proxy;

import pdu.ExamplePdu;
import pdu.ExamplePduMetadataPane;
import proxy.config.ExampleProxyConfig;
import proxy.config.ExampleProxyConfigurator;

public class ExampleProxyModuleFactory extends ProxyModuleFactory
    implements Configurator<ExampleProxyConfig>, ProxySerializer, ProxyDeserializer {
  public ExampleProxyModuleFactory(Extension extension) {
    super(extension);
  }

  @Override
  public ProxyModule createModule(String code, String name, String description, boolean enabled) {
    return new ExampleProxyModule(this, code, name, description, enabled);
  }

  @Override
  public String getCode() {
    return "example_proxy";
  }

  @Override
  public String getName() {
    return "Example Proxy";
  }

  @Override
  public ConfigPane<ExampleProxyConfig> createConfigPane() throws IOException {
    return new ExampleProxyConfigurator();
  }

  @Override
  public PduMetadataPane createPduMetadataPane() throws IOException {
    return new ExamplePduMetadataPane();
  }

  @Override
  public ProxySerializer getSerializer() {
    return this;
  }

  @Override
  public ProxyDeserializer getDeserializer() {
    return this;
  }

  @Override
  public PDU deserializePdu(
      Proxy proxy,
      Connection connection,
      PduDestination destination,
      byte[] buffer,
      int size,
      Set<String> tags,
      Map<String, String> metadata) {

    return new ExamplePdu(
        proxy,
        connection,
        destination,
        buffer,
        size,
        tags,
        metadata.get("StringParam"),
        Integer.parseInt(metadata.get("IntegerParam")));
  }

  @Override
  public Map<String, String> serializePduMetadata(PDU pdu) {
    ExamplePdu temp = (ExamplePdu) pdu;
    return Map.of(
        "StringParam",
        temp.getStringParam(),
        "IntegerParam",
        String.valueOf(temp.getIntegerParam()));
  }
}
