package proxy;

import java.util.logging.Logger;

import com.warxim.petep.helper.PetepHelper;
import com.warxim.petep.persistence.Configurable;
import com.warxim.petep.proxy.factory.ProxyModuleFactory;
import com.warxim.petep.proxy.module.ProxyModule;
import com.warxim.petep.proxy.worker.Proxy;

import proxy.config.ExampleProxyConfig;

public class ExampleProxyModule extends ProxyModule implements Configurable<ExampleProxyConfig> {
  private ExampleProxyConfig config;

  public ExampleProxyModule(
      ProxyModuleFactory factory, String code, String name, String description, boolean enabled) {
    super(factory, code, name, description, enabled);
  }

  @Override
  public Proxy createProxy(PetepHelper helper) {
    return new ExampleProxy(this, helper, config);
  }

  @Override
  public void loadConfig(ExampleProxyConfig config) {
    Logger.getGlobal().info("Example proxy configuration loaded!");

    this.config = config;
  }

  @Override
  public ExampleProxyConfig saveConfig() {
    Logger.getGlobal().info("Example proxy configuration saved!");

    return config;
  }
}
